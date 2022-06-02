package controller;

import database.DbOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import model.Property;
import utils.PropertyListingLoader;
import utils.WebViewLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class RentWindowController implements Initializable {

    @FXML WebView webView;
    @FXML VBox vBox;
    private FXMLLoader loader;
    private Parent root;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        WebViewLoader.loadWebEngine(webView.getEngine());
        setAllProperties();
    }

    private void setAllProperties(){
        ArrayList<Property> properties = DbOperations.getAllProperties();
        PropertyListingLoader.loadProperties(vBox, properties);
    }

    public void backToMenu(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
        root = loader.load();
        stage = (Stage)vBox.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
