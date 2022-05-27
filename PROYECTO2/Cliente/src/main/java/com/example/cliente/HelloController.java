package com.example.cliente;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class HelloController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Cliente cliente;
    private xmlBuilder xmlBuilder1;
    @FXML
    TextField txtusuario;
    @FXML
    TextField txtcontra;
    @FXML
    TextField txtnombre;
    @FXML
    TextField txtapellido;
    @FXML
    TextField txtedad;
    @FXML
    TextField txtgeneroF;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cliente = new Cliente();
        try {
            this.xmlBuilder1 = new xmlBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

    public void switchToScene3(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("Prueba1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void switchToScene4(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("App.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void switchToScene1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getClassLoader().getResource("Prueba2.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void sendRegistertoClient() throws IOException, TransformerException {
        String usuario = txtusuario.getText();
        String contra = txtcontra.getText();
        String edad = txtedad.getText();
        String nombre = txtnombre.getText();
        String genero = txtgeneroF.getText();
        System.out.println(edad);
        xmlBuilder1.registerBuilder(usuario, contra, nombre, edad, genero);
        System.out.println("exito al crear el xml");
        cliente.setFile();
        cliente.sendFiletoSv();
//NOTA ***********************
        //PRACTICAMENTE EL ERROR EXISTE AL CAMBIAR DE ESCENA, ES DECIR TODO SE DEBE HACER EN UNA SOLA ESCENA

    }

    public void send() throws IOException {
        cliente.setFile();
        cliente.sendFiletoSv();
    } //

}