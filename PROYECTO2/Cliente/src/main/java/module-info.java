module com.example.cliente {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.xml;
    requires javafx.media;


    opens com.example.cliente to javafx.fxml;
    exports com.example.cliente;
}