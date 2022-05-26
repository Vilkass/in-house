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
        loader = new FXMLLoader(getClass().getResource("/view/AuthenticationWindow.fxml"));
        root = loader.load();
        stage = (Stage)btn.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void rentWindow(ActionEvent event){


    }

    public void buyWindow(ActionEvent event){

    }


}
