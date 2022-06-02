package controller;

import database.DbOperations;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import model.Property;
import model.Seller;
import utils.PropertyListingLoader;
import utils.WebViewLoader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class InformationWindowController implements Initializable {

    private Property property;
    @FXML Text priceText;
    @FXML Text bedsText;
    @FXML Text bathsText;
    @FXML Text sqrftText;
    @FXML Text addressText;
    @FXML Text nameText;
    @FXML Text houseType;
    @FXML Text buildInText;
    @FXML TextArea descTextArea;
    @FXML ImageView image1;
    @FXML ImageView image2;
    @FXML ImageView image3;
    @FXML ImageView image4;
    @FXML ImageView image5;
    @FXML ImageView image6;
    @FXML ImageView image7;
    @FXML ImageView image8;
    @FXML ImageView image9;
    @FXML VBox vBox;
    @FXML WebView webView;

    @FXML Text floorText;
    @FXML Text securityText;
    @FXML Text bathText;
    @FXML Text internetText;
    @FXML Text balconyText;
    @FXML Text tvText;
    @FXML Text parkingText;
    @FXML Text alarmText;
    @FXML Text fireplaceText;
    @FXML Text camerasText;
    @FXML Text terraceText;
    @FXML Text entranceText;
    @FXML Text storageText;
    @FXML Text dishwasherText;
    @FXML Text wardrobeText;
    @FXML Text machineText;
    @FXML Text ceilingText;
    @FXML Text conditioningText;
    @FXML MenuItem nameMI;
    @FXML MenuItem emailMI;
    @FXML MenuItem phoneMI;

    private Seller seller;
    ArrayList<ImageView> imageViews = new ArrayList<>();



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String price = property.getPrice() + " €";
        if(property.getProperties().contains("For rent")){
            price += "/month";
        }
        priceText.setText(price);
        bathsText.setText(String.valueOf(property.getBathrooms()));
        bedsText.setText(String.valueOf(property.getBedrooms()));
        sqrftText.setText(property.getSqrft() + " m2");
        addressText.setText(property.getCountry() + ", " + property.getCity() + ", " + property.getAddress());
        nameText.setText("Name: " + property.getName());
        houseType.setText("Property type: " + property.getPropertyType());
        buildInText.setText("Build in: " + property.getYearBuild());
        descTextArea.setText(property.getDesc());
        //PropertyListingLoader.loadImages(vBox, property.getImages(), image1);
        imageViews.add(image1);
        imageViews.add(image2);
        imageViews.add(image3);
        imageViews.add(image4);
        imageViews.add(image5);
        imageViews.add(image6);
        imageViews.add(image7);
        imageViews.add(image8);
        imageViews.add(image9);

        for(int i = 0; i < property.getImages().size(); i++){
            try {
                imageViews.get(i).setImage(new Image(new FileInputStream(property.getImages().get(i))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        seller = DbOperations.getPropertySeller(property);
        nameMI.setText("Name: " + seller.getFirstName() + " " +seller.getLastName());
        emailMI.setText("Email: " + seller.getEmail());
        phoneMI.setText("Phone number: " + seller.getPhone());
        WebViewLoader.loadWebEngine(webView.getEngine());
        floorText.setStrikethrough(!property.isFloorHeating());
        securityText.setStrikethrough(!property.isSecurity());
        bathText.setStrikethrough(!property.isBath());
        internetText.setStrikethrough(!property.isInternet());
        balconyText.setStrikethrough(!property.isBalcony());
        tvText.setStrikethrough(!property.isCableTV());
        parkingText.setStrikethrough(!property.isParking());
        alarmText.setStrikethrough(!property.isSecurityAlarm());
        fireplaceText.setStrikethrough(!property.isFireplace());
        camerasText.setStrikethrough(!property.isCameras());
        terraceText.setStrikethrough(!property.isTerrace());
        entranceText.setStrikethrough(!property.isSeparateEntrance());
        storageText.setStrikethrough(!property.isStorage());
        dishwasherText.setStrikethrough(!property.isDishwasher());
        wardrobeText.setStrikethrough(!property.isWardrobe());
        machineText.setStrikethrough(!property.isWashingMachine());
        ceilingText.setStrikethrough(!property.isHighCeilings());
        conditioningText.setStrikethrough(!property.isConditioning());

    }

    public InformationWindowController(Property property){
        this.property = property;
    }

}
