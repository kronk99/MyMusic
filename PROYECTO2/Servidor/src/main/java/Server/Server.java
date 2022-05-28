package Server;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
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
    private Checker chekcer;
    private FileInputStream fileInputStream;


    public Server(Socket socket) {
        try {
            this.socket = socket;
            this.dataOutputStream = new DataOutputStream(socket.getOutputStream());
            this.dataInputStream = new DataInputStream(socket.getInputStream());
            this.xmlBuilder1 = new xmlBuilder();
            serverUsers.add(this);
            System.out.println("Un nuevo cliente se ha conectado");
            chekcer= new Checker();


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
                        //System.out.println(filecontenido);
//aca se llama la instancia de xml builder añade los argumentos de string de aca y crea el xml en el server.

                        //xmlBuilder1.buildXml(filename, filecontenido);
                        chekcer.check(filename , filecontenido ,filecontentbyte);
                        String music = chekcer.getMusic();
                        if (music != null){
                            music = music.replaceAll("\\s", "");//ELIMINA ESPACIOS EN BLANCO
                            System.out.println("la musica a enviar es :" + music);
                            sendMusic(music);
                            chekcer.resetMusic();
                        }
                    }
                }

            } catch (IOException | TransformerException | SAXException e) {
                e.printStackTrace();
                //closeEverything(socket , bufferedReader , bufferedWriter);
                break;

            } catch (ParserConfigurationException e) {
                e.printStackTrace();
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
        //MODIFICAR LO DE ABAJO CUANDO ESTE LISTO.....
    }//voy a editar el metodo de abajo, cuando esten listos los xml, poner argumentos file
    public void sendMusic(String name) throws IOException {
        File file = new File("C:\\Users\\Luis\\Documents\\repositorio gitkraken\\MyMusic\\PROYECTO2\\Servidor\\Music\\" + name);
        if (file != null) {
            System.out.println("se va a enviar la cancion: " +file.getName());
            try { //probar cambiar el nombre del fileimputstream
                fileInputStream = new FileInputStream(file);//ACA QUITE EL .GETABSOLUTEPATH
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            String filename = file.getName(); //NOMBRE DEL ARCHIVO
            byte[] fileNameByte = filename.getBytes();
            System.out.println(fileNameByte); //NUMERO DE BYTES DEL NOMBRE
//creo qu eel error esta aca, puesto que el
            byte[] fileContentByte = new byte[(int) file.length()]; //CONTENIDO DEL ARCHIVO
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
    }

    // Método que se encarga de tomar el album de cada canción
    public void takeAlbum(ArrayList<Songs> arrayList){

        int i, max;
        max = arrayList.size();
        String[] albums = new String[max];
        String album = "";

        for (i = 0; i < max; i++){
            album = arrayList.get(i).getAlbum().toString();
            albums[i] = album;
        }

        sortByAlbum(albums, arrayList);
    }

    public void takeArtist(ArrayList<Songs> arrayList){

        int i, max;
        max = arrayList.size();
        String[] artists = new String[max];
        String artist = "";

        for (i = 0; i < max; i++){
            artist = arrayList.get(i).getArtist().toString();
            artists[i] = artist;
        }

        sortByArtist(artists, arrayList);
    }
    public void takeSong(ArrayList<Songs> arrayList){

        int i, max;
        max = arrayList.size();
        String[] songs = new String[max];
        String song = "";

        for (i = 0; i < max; i++){
            song = arrayList.get(i).getSong().toString();
            songs[i] = song;
        }

        sortBySong(songs, arrayList);
    }

    // Método que se encarga de ordenar por álbum usando Bubble sort
    public static void sortByAlbum(String[] array, ArrayList<Songs> arrayList) {

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
        showSortByAlbum(array, arrayList);
    }

    // Método que se encarga de ordenar por artista usando Insertion sort
    public static void sortByArtist(String array[], ArrayList<Songs> arrayList){

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
        showSortByArtist(array, arrayList);
    }

    // Método que se encarga de ordenar por nombre de la canción usando Quick sort
    // sortBySong() recibe el arreglo
    public static void sortBySong(String array[], ArrayList<Songs> arrayList) {

        String names[];
        int length;
        length = array.length;
        names = array;

        quickSort(names, 0, length - 1, arrayList);
    }

    // Compara los valores del arreglo
    // Recibe el arreglo que envia sort()
    public static void quickSort(String array[], int lowerIndex, int higherIndex, ArrayList<Songs> arrayList) {
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
            quickSort(array, lowerIndex, j, arrayList);
        }
        if (i < higherIndex) {
            quickSort(array, i, higherIndex, arrayList);
        }

        showSortBySongs(array, arrayList);
    }

    // swap() se encarga de intercambiar los valores
    public static void swap(String array[], int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    // Método que se encarga de mostrar la biblioteca ordenada por álbum
    public static void showSortByAlbum(String[] array, ArrayList<Songs> arrayList){
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < arrayList.size(); j++){
                if (array[i] == arrayList.get(j).getAlbum().toString()){
                    System.out.println(arrayList.get(j));
                }
            }
        }
    }
    // Método que se encarga de mostrar la biblioteca ordenada por artista
    public static void showSortByArtist(String[] array, ArrayList<Songs> arrayList){
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < arrayList.size(); j++){
                if (array[i] == arrayList.get(j).getArtist().toString()){
                    System.out.println(arrayList.get(j));
                }
            }
        }
    }
    // Método que se encarga de mostrar la biblioteca ordenada por canción
    public static void showSortBySongs(String[] array, ArrayList<Songs> arrayList){
        for (int i = 0; i < array.length; i++){
            for (int j = 0; j < arrayList.size(); j++){
                if (array[i] == arrayList.get(j).getSong().toString()){
                    System.out.println(arrayList.get(j));
                }
            }
        }
    }
}
