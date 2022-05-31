package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;


public class ListingController implements Initializable {

    @FXML ImageView imageView;
    @FXML Label priceLabel;
    @FXML Label parametersLabel;
    @FXML Label addressLabel;

    private String price;
    private String parameters;
    private String address;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        priceLabel.setText(price);
        parametersLabel.setText(parameters);
        addressLabel.setText(address);
    }

    public ListingController(String price, String parameters, String address){
        this.price = price;
        this.parameters = parameters;
        this.address = address;
    }

    public void setValues(BufferedImage image, String price, String parameters, String address){
        this.price = price;
        this.parameters = parameters;
        this.address = address;
    }
}
