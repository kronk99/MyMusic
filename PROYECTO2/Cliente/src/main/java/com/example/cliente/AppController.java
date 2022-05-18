package com.example.cliente;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    private Cliente cliente;
    private xmlBuilder xmlBuilder1;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cliente = new Cliente();
        try {
            this.xmlBuilder1 = new xmlBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        cliente.receivemessage();
    }

}
