package Server;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class xmlReader {
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document doc;
    private String song;
    private String usuario;
    private String contrase;
    private String nombre;
    private String fMusica;
    private String edad;
    private String genero;
    private String artista;
    private String album;
    private String año;
    private String letra;
    private String mCancion;
//Constructor de la clase reader
    public xmlReader() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
    }//metodo que lee archivos xml "normales"
    public void readXml(String name) throws IOException, SAXException, ParserConfigurationException {
        System.out.println("el nombre del archivo es: " + name);
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        doc =builder.parse(new File(name));
        NodeList lista = doc.getElementsByTagName("buscarMusica");
        Node nodo = lista.item(0);
        Element e =(Element) nodo;
        Node hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        song = hijo.getTextContent();
    }//metodo que lee archivos xml con el nombre de registro.xml
    public void readRegister(String name) throws IOException, SAXException, ParserConfigurationException {
        System.out.println("el nombre del archivo es: " + name);
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        doc =builder.parse(new File(name));
        NodeList listanodo = doc.getElementsByTagName("usuario");
        Node nodo = listanodo.item(0);
        Element e =(Element) nodo;
        Node hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        usuario = hijo.getTextContent();

        listanodo=doc.getElementsByTagName("contraseña");
        nodo = listanodo.item(0);
        e =(Element) nodo;
        hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        contrase=hijo.getTextContent();

        listanodo=doc.getElementsByTagName("musica");
        nodo = listanodo.item(0);
        e =(Element) nodo;
        hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        fMusica=hijo.getTextContent();

        listanodo=doc.getElementsByTagName("nombre");
        nodo = listanodo.item(0);
        e =(Element) nodo;
        hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        nombre=hijo.getTextContent();

        listanodo=doc.getElementsByTagName("edad");
        nodo = listanodo.item(0);
        e =(Element) nodo;
        hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        edad=hijo.getTextContent();
    }//metodo que lee archivos xml de tipo metadata
    public void readMeta(String name) throws ParserConfigurationException, IOException, SAXException {
        System.out.println("el nombre del archivo es: " + name);
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        doc =builder.parse(new File(name));
        NodeList listanodo = doc.getElementsByTagName("Genero1");
        Node nodo = listanodo.item(0);
        Element e =(Element) nodo;
        Node hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        genero = hijo.getTextContent();

        listanodo=doc.getElementsByTagName("Cancion1");
        nodo = listanodo.item(0);
        e =(Element) nodo;
        hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        mCancion=hijo.getTextContent();

        listanodo=doc.getElementsByTagName("Artista1");
        nodo = listanodo.item(0);
        e =(Element) nodo;
        hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        artista=hijo.getTextContent();

        listanodo=doc.getElementsByTagName("Album1");
        nodo = listanodo.item(0);
        e =(Element) nodo;
        hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        album=hijo.getTextContent();

        listanodo=doc.getElementsByTagName("Año1");
        nodo = listanodo.item(0);
        e =(Element) nodo;
        hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        año=hijo.getTextContent();

        listanodo=doc.getElementsByTagName("Letra1");
        nodo = listanodo.item(0);
        e =(Element) nodo;
        hijo = e.getFirstChild();
        System.out.println("el contenido es: " + hijo.getTextContent());
        letra=hijo.getTextContent();

    }
//GETTERS Y SETTERS DE ATRIBUTOS
    //para 2 de los metodos de arriba se ocupan guardar variables y pasarlas de vuelta a
    //la clase checklist
    public String getSong() {
        return song;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContrase() {
        return contrase;
    }

    public String getEdad() {
        return edad;
    }

    public String getfMusica() {
        return fMusica;
    }

    public String getAlbum() {
        return album;
    }

    public String getLetra() {
        return letra;
    }

    public String getAño() {
        return año;
    }

    public String getNombre() {
        return nombre;
    }

    public String getArtista() {
        return artista;
    }

    public String getGenero() {
        return genero;
    }

    public String getmCancion() {
        return mCancion;
    }
}
