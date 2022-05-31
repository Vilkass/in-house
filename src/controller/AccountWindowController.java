package controller;

import database.DbOperations;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Property;
import model.Seller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AccountWindowController implements Initializable{

    @FXML TextField firstNameField;
    @FXML TextField lastNameField;
    @FXML TextField emailField;
    @FXML TextField phoneField;

    @FXML VBox vBox;

    private FXMLLoader loader;
    private Parent root;
    private Stage stage;
    private Seller seller;



    public void logOut(ActionEvent event) throws IOException {
        loadWindow("/view/MainWindow.fxml");
    }

    public void createListing(ActionEvent event) throws IOException {
        loader = new FXMLLoader(getClass().getResource("/view/ListingCreation.fxml"));
        root = loader.load();
        ListingCreationController lcc = loader.getController();
        lcc.setSeller(seller);
        stage = (Stage)emailField.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void loadWindow(String path) throws IOException {
        loader = new FXMLLoader(getClass().getResource(path));
        root = loader.load();
        stage = (Stage)emailField.getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void setSeller(Seller seller){
        this.seller = seller;
        firstNameField.setText(seller.getFirstName());
        lastNameField.setText(seller.getLastName());
        emailField.setText(seller.getEmail());
        phoneField.setText(seller.getPhone());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println(DbOperations.getPropertyList(seller).size());
        for(Property property : DbOperations.getPropertyList(seller)){
            System.out.println(property.getName());
        }


        HBox hbox = new HBox();
        loader = new FXMLLoader(getClass().getResource("/view/Listing.fxml"));
        ListingController lc = new ListingController("$100,000,000", "asdasdasad", "Adresas");
        //lc.setValues(null, "$100,000,000", "asdasdasad", "Adresas");
        loader.setController(lc);
        try {
            hbox.getChildren().add((Node)loader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }
        vBox.getChildren().add(hbox);

    }

}
