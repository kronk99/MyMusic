package com.example.cliente;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {
    private Cliente cliente;
    private xmlBuilder xmlBuilder1;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FileChooser filechoser;
    @FXML
    private ListView<String> musicListview;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cliente = new Cliente();
        try {
            this.xmlBuilder1 = new xmlBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        cliente.receivemessage();
        filechoser = new FileChooser();
    }
    public void adddata(){
        File file = filechoser.showOpenDialog(new Stage());
        System.out.println(file);
        cliente.setMusicTosend(file);
        cliente.sendMusic();
        //musicListview.getItems().add("hola sebas");
        //esto de abajo es el codigo para enviar el xml y decirle
        // String selectedItem = musicListview.getSelectionModel().getSelectedItem();
        //System.out.println(selectedItem);
        //para eliminar
        //int valor = musicListview.getSelectionModel().getSelectedIndex();
        //musicListview.getItems().remove(valor);
    }

}
