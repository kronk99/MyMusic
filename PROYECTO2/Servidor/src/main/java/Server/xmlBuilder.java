package Server;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.print.attribute.standard.Media;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;

public class xmlBuilder {

    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private Document doc;
    private Media media;
    private TransformerFactory transformerFactory;
    private Transformer transformer;
    private DOMSource source;
    Result result;
    public xmlBuilder() throws ParserConfigurationException, TransformerConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        transformerFactory = TransformerFactory.newInstance();
        transformer = transformerFactory.newTransformer();
    }
    public void buildXml(String name, String data) throws IOException, SAXException, TransformerException {
        doc = builder.parse(new InputSource(new StringReader(data)));
        source = new DOMSource(doc);
        result = new StreamResult(new File("C:\\Users\\Luis\\Documents\\repositorio gitkraken\\MyMusic\\PROYECTO2\\Servidor\\" + name));
        transformer.transform(source, result);


    }
}
