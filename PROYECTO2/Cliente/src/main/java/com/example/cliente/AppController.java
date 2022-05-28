package com.example.cliente;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.util.Duration;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
//CONTROLADOR DE LA ESCENA APP
public class AppController implements Initializable {
    private Cliente cliente;
    private xmlBuilder xmlBuilder1;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private FileChooser filechoser;
    @FXML
    private ListView<String> musicListview;
    @FXML
    private Slider bar_Music;
    @FXML
    private Slider bar_Volume;
    private Media media;
    private MediaPlayer mediaPlayer;
    @FXML
    private TextField txtArtista;
    @FXML
    private TextField txtGenero;
    @FXML
    private TextField txtAlbum;
    @FXML
    private TextField txtAño;
    @FXML
    private TextField txtLetra;



//METODO INITIALICE QUE REALIZA TODO LO QUE EL HILO DE LA GUI NO PUEDE HACER
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.cliente = new Cliente();
        try {
            this.xmlBuilder1 = new xmlBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        filechoser = new FileChooser();
        bar_Music.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number t1) {
                seekMusic(bar_Music.getValue());
            }
        });
    }
//METODO QUE AÑADE CANCIONES Y LAS ENVIA AL SERVIDOR
    public void adddata() {
        File file = filechoser.showOpenDialog(new Stage());
        System.out.println(file);
        cliente.setMusicTosend(file);
        cliente.sendMusic();
        musicListview.getItems().add(file.getName());
        //esto de abajo es el codigo para enviar el xml y decirle
        // String selectedItem = musicListview.getSelectionModel().getSelectedItem();
        //System.out.println(selectedItem);
        //para eliminar
        //int valor = musicListview.getSelectionModel().getSelectedIndex();
        //musicListview.getItems().remove(valor);
    }
//METODO QUE CREA EL CONSTRUCTOR del reproductor de musica
    public void setMusic() {
        cliente.receivemessage1();
        media = new Media(cliente.getMusic().toURI().toString());
        //System.out.println( (String) media.getMetadata().get("artist"));
        //System.out.println( (String) media.getMetadata().get("title"));
        //System.out.println( (String) media.getMetadata().get("album"));
        mediaPlayer = new MediaPlayer(media);

    }

    public void startMusic() {
        mediaPlayer.play();
    }

    public void pauseMusic() {
        mediaPlayer.pause();
        mediaPlayer.seek(Duration.seconds(0));

    }

    public void seekMusic(double value) {
        mediaPlayer.seek(Duration.seconds(value));
    }
    public void askMusic() throws TransformerException {
        String selectedItem = musicListview.getSelectionModel().getSelectedItem();
        System.out.println(selectedItem);
        xmlBuilder1.xmlSearchMusic(selectedItem);
        File file = new File("searchMusic.xml");
        cliente.setMusicTosend(file);
        cliente.sendMusic();

    }
    public void modifyData() throws TransformerException {
        String mNombre =  musicListview.getSelectionModel().getSelectedItem();
        String Album = txtAlbum.getText();
        String Artista = txtArtista.getText();
        String año = txtAño.getText();
        String genero = txtGenero.getText();
        String letra = txtLetra.getText();
        xmlBuilder1.mData(Artista, Album , genero ,año ,letra ,mNombre);
        File file = new File("modifyMeta.xml");
        cliente.setMusicTosend(file);
        cliente.sendMusic();

    }

}
