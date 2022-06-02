package utils;

import controller.ListingController;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.Property;
import model.Seller;

import java.io.*;
import java.util.ArrayList;

public class PropertyListingLoader {

    private static FXMLLoader loader;

    public static void loadProperties(VBox vBox, ArrayList<Property> properties){
        HBox hbox = new HBox();
        int i = 1;
        System.out.println(properties.size());
        for(Property property : properties){
            if(i % 2 == 1) {
                hbox = new HBox();
            }
            loader = new FXMLLoader(PropertyListingLoader.class.getResource("/view/Listing.fxml"));
            ListingController lc = new ListingController(String.valueOf(property.getPrice()), property.getProperties(), property.getCountry() ,property.getCity(), property.getAddress(), property.getImages().get(0));
            loader.setController(lc);
            try {
                hbox.getChildren().add((Node)loader.load());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(i % 2 == 0 || properties.size() == i){
                vBox.getChildren().add(hbox);
            }
            i++;
        }


    }

    public static void loadImages(VBox vBox, ArrayList<File> images, ImageView mainImage) {
        HBox hbox = null;
        try {
            mainImage.setImage(new Image(new FileInputStream(images.get(0))));
        }catch (Exception e){
            e.printStackTrace();
        }

        for(int i = 1; i <images.size(); i++){
            if(i % 2 == 1) {
                hbox = new HBox();
                hbox.setPadding(new Insets(10));
            }
            try {
                ImageView imageView = new ImageView(new Image(new FileInputStream(images.get(i))));
                imageView.setFitWidth(360);

                hbox.getChildren().add(imageView);
                if(i % 2 == 0 || images.size() == i){
                    vBox.getChildren().add(hbox);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
