package controller;

import database.DbOperations;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Property;
import model.Seller;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ListingCreationController implements Initializable {


    @FXML TextField nameField;
    @FXML TextField descField;
    @FXML TextField priceField;
    @FXML TextField countryField;
    @FXML TextField cityField;
    @FXML TextField addressField;
    @FXML TextField sqrftField;
    @FXML ChoiceBox bedroomsCB;
    @FXML ChoiceBox bathsCB;
    @FXML ChoiceBox<Integer> yearCB;
    @FXML CheckBox floorHeatingCB;
    @FXML CheckBox securityCB;
    @FXML CheckBox highCeilingCB;
    @FXML CheckBox balconyCB;
    @FXML CheckBox parkingCB;
    @FXML CheckBox internetCB;
    @FXML CheckBox cableCB;
    @FXML CheckBox alarmCB;
    @FXML CheckBox fireplaceCB;
    @FXML CheckBox terraceCB;
    @FXML CheckBox camerasCB;
    @FXML CheckBox entranceCB;
    @FXML CheckBox storageCB;
    @FXML CheckBox wardrobeCB;
    @FXML CheckBox dishwasherCB;
    @FXML CheckBox washingMachineCB;
    @FXML CheckBox bathCB;
    @FXML CheckBox conditioningCB;

    @FXML Text priceUnitLabel;
    @FXML RadioButton sellRB;
    @FXML RadioButton rentDB;
    @FXML ChoiceBox<String> typeCB;


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
    @FXML HBox row1;
    @FXML HBox row2;
    @FXML HBox row3;

    ArrayList<ImageView> previewImages = new ArrayList<>();

    private Property property;
    private Seller seller;
    private ArrayList<File> images = new ArrayList<>();

    private FXMLLoader loader;
    private Parent root;
    private Stage stage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        typeCB.getItems().addAll(DbOperations.getPropertyTypes());
        for(int i = 1; i <=9; i++){
            bedroomsCB.getItems().add(i);
            bathsCB.getItems().add(i);
        }
        for(int i = 2022; i >=1990; i--){
            yearCB.getItems().add(i);
        }
        previewImages.add(image1);
        previewImages.add(image2);
        previewImages.add(image3);
        previewImages.add(image4);
        previewImages.add(image5);
        previewImages.add(image6);
        previewImages.add(image7);
        previewImages.add(image8);
        previewImages.add(image9);
        vBox.setVisible(false);
        vBox.setMinHeight(0);
        vBox.setPrefHeight(0);
        row1.setMinHeight(0);
        row1.setPrefHeight(0);
        row2.setMinHeight(0);
        row2.setPrefHeight(0);
        row3.setMinHeight(0);
        row3.setPrefHeight(0);
    }

    public void priceValueChange(ActionEvent event){
        if(sellRB.isSelected()){
            priceUnitLabel.setText("€");
        }else{
            priceUnitLabel.setText("€/month");
        }
    }

    public void createProperty(ActionEvent event) throws Exception {
        String propertyState;
        if(sellRB.isSelected()){
            propertyState = "For sale";
        }else{
            propertyState = "For rent";
        }
        property = new Property(typeCB.getSelectionModel().getSelectedItem(), nameField.getText(), descField.getText(), propertyState, Double.valueOf(priceField.getText()), countryField.getText(),cityField.getText(), addressField.getText(), Double.valueOf(sqrftField.getText()), (byte)1,(byte)1);
        property.setFloorHeating(floorHeatingCB.isSelected());
        property.setSecurity(securityCB.isSelected());
        property.setHighCeilings(highCeilingCB.isSelected());
        property.setBalcony(balconyCB.isSelected());
        property.setParking(parkingCB.isSelected());
        property.setInternet(parkingCB.isSelected());
        property.setCableTV(cableCB.isSelected());
        property.setSecurityAlarm(alarmCB.isSelected());
        property.setFireplace(fireplaceCB.isSelected());
        property.setTerrace(terraceCB.isSelected());
        property.setCameras(camerasCB.isSelected());
        property.setSeparateEntrance(entranceCB.isSelected());
        property.setStorage(storageCB.isSelected());
        property.setWardrobe(wardrobeCB.isSelected());
        property.setDishwasher(dishwasherCB.isSelected());
        property.setWashingMachine(washingMachineCB.isSelected());
        property.setBath(bathCB.isSelected());
        property.setConditioning(conditioningCB.isSelected());
        property.setYearBuild(yearCB.getSelectionModel().getSelectedItem());
        property.setImages(images);

        DbOperations.saveProperty(property, seller);
        infoBox("Success!", "Property listing created successfully!");
        loadWindow("/view/AccountWindow.fxml");
    }

    private static void infoBox(String title, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(message);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    private void loadWindow(String path) throws IOException {
        loader = new FXMLLoader(getClass().getResource(path));
        root = loader.load();
        AccountWindowController awc = loader.getController();
        awc.setSeller(seller);
        stage = (Stage)balconyCB.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void chooseFiles(ActionEvent event){
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("/Users/anton/Desktop"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images only!", "*.png", "*.jpg"));
        List<File> files = fc.showOpenMultipleDialog(null);
        if( files != null) {
            for(File file : files) {
                images.add(file);
            }
        }else {
            System.out.println("Wrong file!");
        }
        displaySelectedImages();
    }

    public void returnBack(ActionEvent event) throws IOException {
        loadWindow("/view/AccountWindow.fxml");
    }



    public void setSeller(Seller seller){
        this.seller = seller;
    }


    private void displaySelectedImages(){
        int size = images.size();
        if(size != 0){
            vBox.setMinHeight(Region.USE_COMPUTED_SIZE);
            vBox.setPrefHeight(Region.USE_COMPUTED_SIZE);
            vBox.setVisible(true);
            row1.setMinHeight(Region.USE_COMPUTED_SIZE);
            row1.setPrefHeight(Region.USE_COMPUTED_SIZE);
            if(size > 3){
                row2.setMinHeight(Region.USE_COMPUTED_SIZE);
                row2.setPrefHeight(Region.USE_COMPUTED_SIZE);
            }
            if(size > 6){
                row3.setMinHeight(Region.USE_COMPUTED_SIZE);
                row3.setPrefHeight(Region.USE_COMPUTED_SIZE);
            }
        }
        int i = 0;
        for(File file : images){
            InputStream is = null;
            try {
                is = new FileInputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            previewImages.get(i).setImage(new Image(is));
            i++;
        }
    }
}
