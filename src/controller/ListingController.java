package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        parametersLabel.setText(parameters);
        addressLabel.setText(address);
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

    public ListingController(String price, String parameters, String address, File file){
        this.price = price;
        this.parameters = parameters;
        this.address = address;
        this.file = file;
    }

}
