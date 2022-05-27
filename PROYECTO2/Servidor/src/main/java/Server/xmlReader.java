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
    public xmlReader() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
    }
    public void readXml(String name) throws IOException, SAXException {
        doc =builder.parse(new File(name));
        NodeList lista = doc.getElementsByTagName("buscarMusica");
        Node nodo = lista.item(0);
        
    }
}
