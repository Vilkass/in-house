package utils;

import javafx.scene.web.WebEngine;

public class WebViewLoader {

    private static String html = "/resources/googlemap.html";


    public static void loadWebEngine(WebEngine engine){
        engine.load(WebViewLoader.class.getResource(html).toString());
    }

}
