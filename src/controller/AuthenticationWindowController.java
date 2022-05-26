package controller;

import database.DbOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import model.AuthenticationModel;

import java.sql.SQLException;

public class AuthenticationWindowController {
    
    // Register UI
    @FXML TextField firstNameField;
    @FXML TextField lastNameField;
    @FXML TextField emailField;
    @FXML TextField passwordField;
    @FXML TextField confirmPasswordField;
    @FXML TextField phoneField;

    // Login UI
    @FXML TextField loginEmailField;
    @FXML TextField loginPasswordField;

    private AuthenticationModel auth;

    public void login(ActionEvent event){
        auth = new AuthenticationModel(loginEmailField.getText(), loginPasswordField.getText());

        try{
            DbOperations.loginSeller(auth);
        }catch (Exception e){
            errorBox("Failed to login!", e.getMessage());
            return;
        }

        infoBox("Success!", "Logged in successfully!");

    }

    public void register(ActionEvent event){
        firstNameField.setText("Anton");
        lastNameField.setText("Volcok");
        emailField.setText("test@gmail.com");
        phoneField.setText("+87454154");
        passwordField.setText("testavimas");
        confirmPasswordField.setText("testavimas");
        auth = new AuthenticationModel(firstNameField.getText(), lastNameField.getText(), emailField.getText(), passwordField.getText(), phoneField.getText());
        try{
            auth.verifyFirstName();
            auth.verifyLastName();
            auth.verifyEmail();
            auth.verifyPhone();
            auth.verifyPassword();
            if(!passwordField.getText().equals(confirmPasswordField.getText())){
                throw new Exception("Passwords do not match!");
            }
            DbOperations.registerSeller(auth);

        }catch (SQLException e){
            if(e.getMessage().contains("key 'seller.EMAIL'")){
                errorBox("Failed to create account!", "User with that email already exists!");
            }
            e.printStackTrace();
            return;
        }catch (Exception e){
            errorBox("Failed to create account!", e.getMessage());
            return;
        }


        infoBox("Success!", "Account created successfully!");

    }

    private static void errorBox(String title, String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private static void infoBox(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }


}
