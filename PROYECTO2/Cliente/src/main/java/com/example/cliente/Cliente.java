package com.example.cliente;

import org.w3c.dom.Document;

import java.io.*;
import java.net.Socket;;

public class Cliente {
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private File filetosend;
    private File musicTosend;
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
            try {
                fileInputStream = new FileInputStream(musicTosend.getAbsolutePath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String filename = musicTosend.getName();
            byte[] fileNameByte = filename.getBytes();
            System.out.println(fileNameByte);
//creo qu eel error esta aca, puesto que el
            byte[] fileContentByte = new byte[(int)musicTosend.length()];
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
        musicTosend = null;
    }
    public void receivemessage(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (socket.isConnected()){
                    try {
                        int data = dataInputStream.readInt();
                        System.out.println("se recibe el numero: " + data);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }



}
