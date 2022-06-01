package controller;

import database.DbOperations;
import javafx.event.ActionEvent;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class CreateListingController {

    public void select(ActionEvent event) throws Exception {
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("/Users/anton/Desktop"));
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Images only!", "*.png", "*.jpg"));
        List<File> files = fc.showOpenMultipleDialog(null);
        if( files != null) {
            for(File file : files) {
                //DbOperations.savePropertyImage(file);
            }
        }else {
            System.out.println("Wrong file!");
        }
    }

    public void upload(ActionEvent event){

    }



}
