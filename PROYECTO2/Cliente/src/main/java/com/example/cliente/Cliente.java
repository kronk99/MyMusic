package com.example.cliente;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.transform.TransformerException;
import java.io.*;
import java.net.Socket;;

public class Cliente {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private File filetosend;
    private File musicTosend;
    private File music;
    private Document register;
    private FileOutputStream fileOutputStream;
    private FileInputStream fileInputStream;
    private int value;
    public Cliente(){
        try{
            this.socket = new Socket("localhost", 9999);
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
        }catch (IOException E){
            E.printStackTrace();
        }
    }
    public void setFile()
    {//C:\Users\Luis\Documents\repositorio gitkraken\Proyecto_2_BD1\PROYECTO2\Cliente\registro.xml
        this.filetosend = new File("registro.xml");
        System.out.println("la file a enviar es: " + this.filetosend);
        System.out.println("la file a enviar es: " + this.filetosend.getAbsolutePath());

    }
    public void setMusicTosend(File file){
        this.musicTosend = file;
    }

    public void sendFiletoSv(){
        if (filetosend != null){
            try {
                fileInputStream = new FileInputStream(filetosend.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String filename = filetosend.getName();
            byte[] fileNameByte = filename.getBytes();
            System.out.println(fileNameByte);
//creo qu eel error esta aca, puesto que el
            byte[] fileContentByte = new byte[(int)filetosend.length()];
            try {
                fileInputStream.read(fileContentByte);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dataOutputStream.writeInt(fileNameByte.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(fileNameByte.length);
            System.out.println("Se envio el filename.int");
            try {
                dataOutputStream.write(fileNameByte);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("Se envio el filename.bytet");
            System.out.println(fileContentByte);

            try {
                dataOutputStream.writeInt(fileContentByte.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dataOutputStream.write(fileContentByte);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("se envio todo");

        }
        filetosend = null;
    }
    public void sendMusic(){
        if (musicTosend != null){
            try { //probar cambiar el nombre del fileimputstream
                fileInputStream = new FileInputStream(musicTosend.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String filename = musicTosend.getName(); //NOMBRE DEL ARCHIVO
            byte[] fileNameByte = filename.getBytes();
            System.out.println(fileNameByte); //NUMERO DE BYTES DEL NOMBRE
//creo qu eel error esta aca, puesto que el
            byte[] fileContentByte = new byte[(int)musicTosend.length()]; //CONTENIDO DEL ARCHIVO
            try {
                fileInputStream.read(fileContentByte); //lee los contenidos d elos bytes
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dataOutputStream.writeInt(fileNameByte.length); //envia el largo del nombre
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(fileNameByte.length); //IMPRIME EL LARGO DE LOS BYTES
            System.out.println("Se envio el filename.int");
            try {
                dataOutputStream.write(fileNameByte); //ENVIA LOS BYTES DEL NOMBRE

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(fileContentByte);
            System.out.println("Se envio el filename.bytet");

            try {
                dataOutputStream.writeInt(fileContentByte.length); //ENVIA EL LARGO DEL CONTENIDO
                System.out.println("el largo de los fylecontentbytes es:" + fileContentByte.length);
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                dataOutputStream.write(fileContentByte); //envia los filecontent bytes
                System.out.println("los bytes de fylecontentbytes son:" + fileContentByte);
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("se envio todo");
        }
        musicTosend = null;
    }
    //PROBAR IMPRIMIR LO QUE ESTARIA ENVIANDO A V3ER SI LLEGA CORRUPTO
    public void receivemessage1() {
        try {
            System.out.println(dataInputStream.available());
            int filelenght = dataInputStream.readInt();
            System.out.println("el largo filename es : " + filelenght);
            if (filelenght > 0) {
                byte[] fileNameBytes = new byte[filelenght];

                dataInputStream.readFully(fileNameBytes, 0, filelenght);
                System.out.println(fileNameBytes);

                String filename = new String(fileNameBytes);
                System.out.println("el filename es: + " + filename);
                int filecontent = dataInputStream.readInt();
                System.out.println("SE leyo el largo del file: " + filecontent);
                if (filecontent > 0) {
                    byte[] filecontentbyte = new byte[filecontent];//deberia de leer a partir del 2 byte?
                    dataInputStream.readFully(filecontentbyte, 0, filecontent);
                    System.out.println(filecontentbyte);
                    String filecontenido = new String(filecontentbyte); //EL ERROR ESTA ACA
                    System.out.println(filecontenido);
                    System.out.println("va a crearse la file");
                    System.out.println(filename);
                    music = new File(filename);
                    System.out.println("se creo la file");

                    FileOutputStream fileOutputStream = new FileOutputStream(music);
                    fileOutputStream.write(filecontentbyte);
                    //fileOutputStream.close();
                    //EN la GUI un boton va a agarrar music para reproducirlo.
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            //closeEverything(socket , bufferedReader , bufferedWriter);
        }
    }
    public File getMusic(){
        return this.music;
    }

}
