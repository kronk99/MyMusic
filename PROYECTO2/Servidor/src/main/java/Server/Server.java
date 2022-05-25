package Server;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class Server implements  Runnable{
    public static ArrayList<Server> serverUsers = new ArrayList<>();
    private Socket socket;
    private DataInputStream dataInputStream;
    private DataOutputStream dataOutputStream;
    private xmlBuilder xmlBuilder1;
    private int Usertag;


    public Server(Socket socket) {
        try {
            this.socket = socket;
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.xmlBuilder1 = new xmlBuilder();
            serverUsers.add(this);
            System.out.println("Un nuevo cliente se ha conectado");


            System.out.println("exitooooooo");

        } catch (IOException | ParserConfigurationException | TransformerConfigurationException e) {
            e.printStackTrace();
        }

    } //EL PROBLEMA ESTA ACA , LUEGO DE LA PRIMERA ITERACION DEJA DE SERVIR EL PROGRAMA

    //Posiblemente el error no sea aca, se hizo una ´prueba en el cliente y deja de enviar cosas
    //unicamente cuando cambia de escena.
    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                System.out.println(dataInputStream.available());
                int filelenght = dataInputStream.readInt();
                System.out.println("el numero es : " + filelenght);
                if (filelenght > 0) {
                    byte[] fileNameBytes = new byte[filelenght];
                    System.out.println("SE AGIGNO UN NUEVO FILENAMEBYTE");
                    dataInputStream.readFully(fileNameBytes, 0, filelenght);
                    System.out.println("SE leyo completamente el dataimput");
                    String filename = new String(fileNameBytes);
                    System.out.println("SE AGIGNO UN string filename");
                    int filecontent = dataInputStream.readInt();
                    System.out.println("SE leyo nuevamente el");
                    if (filecontent > 0) {
                        byte[] filecontentbyte = new byte[filecontent];
                        dataInputStream.readFully(filecontentbyte, 0, filecontent);
                        String filecontenido = new String(filecontentbyte);
//aca se llama la instancia de xml builder añade los argumentos de string de aca y crea el xml en el server.
                        System.out.println(filename + filecontenido);
                        xmlBuilder1.buildXml(filename, filecontenido);
                        System.out.println("se creo el xml");
                    }
                }
                sendConfirmation();

            } catch (IOException | TransformerException | SAXException e) {
                e.printStackTrace();
                //closeEverything(socket , bufferedReader , bufferedWriter);
                break;

            }
        }
    }

    // Método para encriptar contraseña
    public static String getEncryptedPassword(String password) {
        final byte[] bytes = password.getBytes();
        try {
            final MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(bytes);
            final byte arrayMessageDigest[] = messageDigest.digest();
            final StringBuffer hexadecimalString = new StringBuffer();
            for (final byte element : arrayMessageDigest) {
                final String hexadecimal = Integer.toHexString(0xFF & element);
                if (hexadecimal.length() == 1) {
                    hexadecimalString.append('0');
                } else {
                    hexadecimalString.append(hexadecimal);
                }
            }
            password = hexadecimalString + "";
        } catch (final NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        }
        return password;
    }
    public void sendConfirmation() throws IOException {
        dataOutputStream.writeInt(8);
    }

    // Método que se encarga de ordenar por álbum usando Bubble sort
    public static void sortByAlbum(String[] array) {

        int i, j, length;
        String aux;
        length = array.length;

        for (i = 0; i < length - 1; i++) {
            for (j = i + 1; j < length; j++) {
                if (array[i].compareTo(array[j]) > 0) {
                    aux = array[i];
                    array[i] = array[j];
                    array[j] = aux;
                }
            }
        }
    }

    // Método que se encarga de ordenar por artista usando Insertion sort
    public static void sortByArtist(String array[]){

        String aux;
        int i, j, length;
        length = array.length;

        for(i = 0; i < length; i++){
            for(j = i + 1; j < length; j++){
                if(array[i].compareTo(array[j]) > 0){
                    aux = array[i];
                    array[i] = array[j];
                    array[j] = aux;
                }
            }
        }

    }

    // Método que se encarga de ordenar por nombre de la canción usando Quick sort
    // sortBySong() recibe el arreglo
    public static void sortBySong(String array[]) {

        String names[];
        int length;
        length = array.length;
        names = array;

        quickSort(names, 0, length - 1);
    }

    // Compara los valores del arreglo
    // Recibe el arreglo que envia sort()
    public static void quickSort(String array[], int lowerIndex, int higherIndex) {
        int i = lowerIndex;
        int j = higherIndex;
        String pivot = array[lowerIndex + (higherIndex - lowerIndex) / 2];

        while (i <= j) {
            while (array[i].compareToIgnoreCase(pivot) < 0) {
                i++;
            }

            while (array[j].compareToIgnoreCase(pivot) > 0) {
                j--;
            }

            if (i <= j) {
                swap(array, i, j);
                i++;
                j--;
            }
        }

        if (lowerIndex < j) {
            quickSort(array, lowerIndex, j);
        }
        if (i < higherIndex) {
            quickSort(array, i, higherIndex);
        }
    }

    // swap() se encarga de intercambiar los valores
    public static void swap(String array[], int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
