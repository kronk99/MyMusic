package com.example.cliente;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class xmlBuilder {
    private DocumentBuilderFactory factory;
    private DocumentBuilder builder;
    private DOMImplementation implementation;
    public xmlBuilder() throws ParserConfigurationException {
        factory = DocumentBuilderFactory.newInstance();
        builder = factory.newDocumentBuilder();
        implementation = builder.getDOMImplementation();

    }
    public void registerBuilder(String username , String password , String name , String age , String music) throws TransformerException {
        Document document = implementation.createDocument(null , "registro" ,null );
        document.setXmlVersion("1.0");
        Element usuario = document.createElement("usuario");
        Element nombre = document.createElement("nombre");
        Element musica = document.createElement("musica");
        Element contraseña = document.createElement("contraseña");
        Element edad = document.createElement("edad");
        Text textusuario = document.createTextNode(username);
        Text nombreU = document.createTextNode(name);
        Text edadU = document.createTextNode(age);
        Text textcontra = document.createTextNode(password);
        Text textmusic = document.createTextNode(music);
        usuario.appendChild(textusuario);
        contraseña.appendChild(textcontra);
        musica.appendChild(textmusic);
        nombre.appendChild(nombreU);
        edad.appendChild(edadU);
        //estos codigos de abajo pueden ser utiles para acceder a los elementos y sus valores
        document.getDocumentElement().appendChild(usuario);
        document.getDocumentElement().appendChild(contraseña);
        document.getDocumentElement().appendChild(musica);
        document.getDocumentElement().appendChild(nombre);
        document.getDocumentElement().appendChild(edad);
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("registro.xml"));
        Transformer transformer = new TransformerFactory() {
            @Override
            public Transformer newTransformer(Source source) throws TransformerConfigurationException {
                return null;
            }

            @Override
            public Transformer newTransformer() throws TransformerConfigurationException {
                return null;
            }

            @Override
            public Templates newTemplates(Source source) throws TransformerConfigurationException {
                return null;
            }

            @Override
            public Source getAssociatedStylesheet(Source source, String media, String title, String charset) throws TransformerConfigurationException {
                return null;
            }

            @Override
            public void setURIResolver(URIResolver resolver) {

            }

            @Override
            public URIResolver getURIResolver() {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws TransformerConfigurationException {

            }

            @Override
            public boolean getFeature(String name) {
                return false;
            }

            @Override
            public void setAttribute(String name, Object value) {

            }

            @Override
            public Object getAttribute(String name) {
                return null;
            }

            @Override
            public void setErrorListener(ErrorListener listener) {

            }

            @Override
            public ErrorListener getErrorListener() {
                return null;
            }
        }.newInstance().newTransformer();
        transformer.transform(source , result); ///
        //File file = new File("registro.xml"); //bien si sirvo mi teoria, si crea el codigo como crei
        //System.out.println(file.getAbsolutePath());




    }
    public void xmlSearchMusic(String musicname) throws TransformerException {
        Document document = implementation.createDocument(null , "searchMusic" ,null );
        document.setXmlVersion("1.0");
        Element Musica = document.createElement("buscarMusica");
        Text musicToListen = document.createTextNode(musicname);
        Musica.appendChild(musicToListen);
        //estos codigos de abajo pueden ser utiles para acceder a los elementos y sus valores
        document.getDocumentElement().appendChild(Musica);
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("searchMusic.xml"));
        Transformer transformer = new TransformerFactory() {
            @Override
            public Transformer newTransformer(Source source) throws TransformerConfigurationException {
                return null;
            }

            @Override
            public Transformer newTransformer() throws TransformerConfigurationException {
                return null;
            }

            @Override
            public Templates newTemplates(Source source) throws TransformerConfigurationException {
                return null;
            }

            @Override
            public Source getAssociatedStylesheet(Source source, String media, String title, String charset) throws TransformerConfigurationException {
                return null;
            }

            @Override
            public void setURIResolver(URIResolver resolver) {

            }

            @Override
            public URIResolver getURIResolver() {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws TransformerConfigurationException {

            }

            @Override
            public boolean getFeature(String name) {
                return false;
            }

            @Override
            public void setAttribute(String name, Object value) {

            }

            @Override
            public Object getAttribute(String name) {
                return null;
            }

            @Override
            public void setErrorListener(ErrorListener listener) {

            }

            @Override
            public ErrorListener getErrorListener() {
                return null;
            }
        }.newInstance().newTransformer();
        transformer.transform(source , result);

    }
    public void mData(String Artista , String Album , String genero , String año , String letra ,String cancion) throws TransformerException {
        Document document = implementation.createDocument(null , "modifyMeta" ,null );
        document.setXmlVersion("1.0");
        Element Cancion1 = document.createElement("Cancion1");
        Element Artista1 = document.createElement("Artista1");
        Element Album1 = document.createElement("Album1");
        Element Genero1 = document.createElement("Genero1");
        Element Año1 = document.createElement("Año1");
        Element Letra1 = document.createElement("Letra1");
        Text textusuario2 = document.createTextNode(cancion);
        Text textusuario = document.createTextNode(Artista);
        Text nombreU = document.createTextNode(Album);
        Text edadU = document.createTextNode(genero );
        Text textcontra = document.createTextNode(año);
        Text textmusic = document.createTextNode(letra);
        Artista1.appendChild(textusuario);
        Año1.appendChild(textcontra);
        Letra1.appendChild(textmusic);
        Album1.appendChild(nombreU);
        Genero1.appendChild(edadU);
        Cancion1.appendChild(textusuario2);
        //estos codigos de abajo pueden ser utiles para acceder a los elementos y sus valores
        document.getDocumentElement().appendChild(Artista1);
        document.getDocumentElement().appendChild(Año1);
        document.getDocumentElement().appendChild(Letra1);
        document.getDocumentElement().appendChild(Album1);
        document.getDocumentElement().appendChild(Genero1);
        document.getDocumentElement().appendChild(Cancion1);
        Source source = new DOMSource(document);
        Result result = new StreamResult(new File("modifyMeta.xml"));
        Transformer transformer = new TransformerFactory() {
            @Override
            public Transformer newTransformer(Source source) throws TransformerConfigurationException {
                return null;
            }

            @Override
            public Transformer newTransformer() throws TransformerConfigurationException {
                return null;
            }

            @Override
            public Templates newTemplates(Source source) throws TransformerConfigurationException {
                return null;
            }

            @Override
            public Source getAssociatedStylesheet(Source source, String media, String title, String charset) throws TransformerConfigurationException {
                return null;
            }

            @Override
            public void setURIResolver(URIResolver resolver) {

            }

            @Override
            public URIResolver getURIResolver() {
                return null;
            }

            @Override
            public void setFeature(String name, boolean value) throws TransformerConfigurationException {

            }

            @Override
            public boolean getFeature(String name) {
                return false;
            }

            @Override
            public void setAttribute(String name, Object value) {

            }

            @Override
            public Object getAttribute(String name) {
                return null;
            }

            @Override
            public void setErrorListener(ErrorListener listener) {

            }

            @Override
            public ErrorListener getErrorListener() {
                return null;
            }
        }.newInstance().newTransformer();
        transformer.transform(source , result); ///
        //File file = new File("registro.xml"); //bien si sirvo mi teoria, si crea el codigo como crei
        //System.out.println(file.getAbsolutePath());




    }



}
