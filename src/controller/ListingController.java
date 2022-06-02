package controller;

import database.DbOperations;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;


public class ListingController implements Initializable {

    @FXML ImageView imageView;
    @FXML Label priceLabel;
    @FXML Label parametersLabel;
    @FXML Label addressLabel;

    private String price;
    private File file;
    private String parameters;
    private String address;
    private String country;
    private String city;
    private FXMLLoader loader;
    private Parent root;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        parametersLabel.setText(parameters);
        addressLabel.setText(country + ", " +city + ", " + address);
        String priceText = price + " €";
        if(parameters.contains("For rent")){
            priceText += " / month";
        }
        priceLabel.setText(priceText);
        InputStream is = null;
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        imageView.setImage(new Image(is));
    }

    public ListingController(String price, String parameters, String country, String city, String address, File file){
        this.price = price;
        this.parameters = parameters;
        this.address = address;
        this.country = country;
        this.city = city;
        this.file = file;
    }


    public void showListingInfo(MouseEvent event) throws IOException {

        loader = new FXMLLoader(getClass().getResource("/view/InformationWindow.fxml"));
        InformationWindowController iwc = new InformationWindowController(DbOperations.getPropertyByAddress(address));
        loader.setController(iwc);
        root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

}
