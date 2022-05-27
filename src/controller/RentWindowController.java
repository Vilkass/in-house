package controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.net.URL;
import java.util.ResourceBundle;

public class RentWindowController implements Initializable {

    private String html = "/resources/googlemap.html";
    @FXML WebView webView;
    private WebEngine webEngine;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        webEngine = webView.getEngine();
        webEngine.load(getClass().getResource(html).toString());
    }

}
