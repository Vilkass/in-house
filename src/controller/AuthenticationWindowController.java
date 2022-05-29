package controller;

import database.DbOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AuthenticationModel;
import model.Seller;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
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
    private BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
    private Seller seller;
    private FXMLLoader loader;
    private Parent root;
    private Stage stage;

    public void login(ActionEvent event){
        auth = new AuthenticationModel(loginEmailField.getText(), loginPasswordField.getText());
        try{
            seller = auth.login();
        }catch (Exception e){
            errorBox("Failed to login!", e.getMessage());
            return;
        }

        infoBox("Success!", "Logged in successfully!");
        loginEmailField.setText("");
        loginPasswordField.setText("");

    }

    public void register(ActionEvent event){

        auth = new AuthenticationModel(firstNameField.getText(), lastNameField.getText(), emailField.getText(), passEncoder.encode(passwordField.getText()), phoneField.getText());
        try{
            auth.verifyFirstName();
            auth.verifyLastName();
            auth.verifyEmail();
            auth.verifyPhone();
            auth.verifyPassword();
            if(!passwordField.getText().equals(confirmPasswordField.getText())){
                throw new Exception("Passwords do not match!");
            }
            auth.register();
        }catch (SQLException e){
            if(e.getMessage().contains("key 'seller.EMAIL'")){
                errorBox("Failed to create account!", "User with that email already exists!");
            }
            return;
        }catch (Exception e){
            errorBox("Failed to create account!", e.getMessage());
            return;
        }

        infoBox("Success!", "Account created successfully!");
        firstNameField.setText("");
        lastNameField.setText("");
        emailField.setText("");
        phoneField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }

    public void returnToMenu(ActionEvent event) throws IOException {

        loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        root = loader.load();
        stage = (Stage)emailField.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

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
