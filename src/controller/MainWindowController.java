package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class MainWindowController {
    @FXML Button btn;
    private FXMLLoader loader;
    private Parent root;
    private Stage stage;

    public void authenticationWindow(ActionEvent event) throws Exception {
        loadWindow("/view/AuthenticationWindow.fxml");
    }

    public void rentWindow(ActionEvent event) throws IOException {
        loadWindow("/view/RentWindow.fxml");

    }

    public void buyWindow(ActionEvent event){

    }


    private void loadWindow(String path) throws IOException {
        loader = new FXMLLoader(getClass().getResource(path));
        root = loader.load();
        stage = (Stage)btn.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
