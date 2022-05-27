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
    public xmlReader() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
    }
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
    }

    public String getSong() {
        return song;
    }
}
