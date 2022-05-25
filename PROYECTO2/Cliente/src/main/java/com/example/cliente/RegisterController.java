package com.example.cliente;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisterController implements Initializable {
    private Cliente cliente;
    private xmlBuilder xmlBuilder1;
    private Stage stage;
    private Scene scene;
    private Parent root;
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
        cliente.receivemessage();
    }

    public void sendRegistertoClient2() throws IOException, TransformerException {
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


    }
    public void switchToScene2(ActionEvent event) throws IOException {
        root = FXMLLoader.load(HelloApplication.class.getResource("hello-view.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}