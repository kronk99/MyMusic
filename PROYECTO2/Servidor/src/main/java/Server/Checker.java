package Server;

import org.farng.mp3.MP3File;
import org.farng.mp3.TagException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.ArrayList;

public class Checker {
    private int value;
    private xmlBuilder xmlBuilder;
    private xmlReader xmlReader1;
    private String music;
    private MP3File mp3File;

    public Checker() throws TransformerConfigurationException, ParserConfigurationException {
        value = 0;
        xmlBuilder = new xmlBuilder();
        xmlReader1 = new xmlReader();
    }
    public void check(String filename, String data , byte[] filebytes) throws IOException, TransformerException, SAXException, ParserConfigurationException, TagException {

        // Se inicializa la lista
        UsersList usersListWrite = new UsersList(); // Instancia solamente para escribir
        UsersList usersListRead = new UsersList(); // Instancia solamente para leer, si se da

        // Se crea el ArrayList para almacenar los datos del usuario
        ArrayList<Users> usersArrayList = new ArrayList<Users>();

        // Instancia para leer y escribir del archivo JSON
        UsersDataManagement usersDataManagement = new UsersDataManagement();

        switch (filename){
            case "registro.xml":
                xmlBuilder.buildXml(filename, data);
                xmlReader1.readRegister(filename);
                System.out.println("se creo el xml");
                String usuario = xmlReader1.getUsuario();
                String contraseña = xmlReader1.getContrase();
                String musicaFav = xmlReader1.getfMusica();
                String nombre = xmlReader1.getNombre();
                String edad = xmlReader1.getEdad();
                System.out.println(usuario + contraseña + musicaFav + nombre + edad);

                // encryptedPassword toma lo que retorna el método getEncryptedPassword
                String encryptedPassword;
                encryptedPassword = Server.getEncryptedPassword(contraseña);

                // Se crea el usuario
                Users user = new Users(usuario, encryptedPassword, nombre, edad, musicaFav);

                usersArrayList.add(user); // Se agrega la información en el ArrayList
                usersListWrite.setUserList(usersArrayList); // Se actualiza la lista con la info del ArrayList
                usersDataManagement.writeAllData(usersListWrite); // Crea (si no lo está) y escribe en archivo JSON
                //aca deberia de leerse el xml y guardarlo en el JSON
                //retorna un 0
                break;
            case "login.xml":
                //aca se debe verificar los datos con el xml
                //returna un 1
            case "eliminar.xml":
                //aca se envia un xml diciendo que quiere eliminar la cancion, hacer funcion para eliminar.
                break;
                //retorna 2
            case "ordenar.xml":
                //debe leer el nombre de la instruccion
                //ejecuta la instruccion (quicksort, insertion sort)
                break;
            case "searchMusic.xml":
                xmlBuilder.buildXml(filename, data);
                xmlReader1.readXml(filename);
                music = xmlReader1.getSong();
                break;
                //leer el xml
                //retornar un 3
            case "modifyMeta.xml":
                xmlBuilder.buildXml(filename, data);
                xmlReader1.readMeta(filename);
                String genero = xmlReader1.getGenero();
                String Artista = xmlReader1.getArtista();
                String Album = xmlReader1.getAlbum();
                String año = xmlReader1.getAño();
                String getletra = xmlReader1.getLetra();
                String mCancion = xmlReader1.getmCancion();
                mCancion = mCancion.replaceAll("\\s", "");
                mp3File = new MP3File("C:\\Users\\Luis\\Documents\\repositorio gitkraken\\MyMusic\\PROYECTO2\\Servidor\\Music\\" + mCancion);
                mp3File.getID3v2Tag().setSongGenre(genero);
                mp3File.getID3v2Tag().setAuthorComposer(Artista);
                mp3File.getID3v2Tag().setAlbumTitle(Album);
                mp3File.getID3v2Tag().setYearReleased(año);
                mp3File.getID3v2Tag().setSongLyric(getletra);
                mp3File.save();
                break;
            default:
                //aca si no es ninguno de esos nombres debe crear una file y guardarla en una careta

                try{
                    System.out.println("Se llego al constructor de canciones");
                    //metodo llega pero no a la carpeta de musica.
                    System.out.println("va a crearse la file");
                    System.out.println(filename);
                    File file = new File("C:\\Users\\Luis\\Documents\\repositorio gitkraken\\MyMusic\\PROYECTO2\\Servidor\\Music\\" + filename );
                    System.out.println("se creo la file");
                    FileOutputStream fileOutputStream = new FileOutputStream(file);
                    fileOutputStream.write(filebytes);
                    fileOutputStream.close();
                    file.createNewFile();
                    System.out.println("se guardo la file");


                    //si no sirve el write antes del crear file , ponerlo abajo

                    //FileWriter fileWriter = new FileWriter(file.getAbsolutePath()); //cambiar por file normal
                    //BufferedWriter bw = new BufferedWriter(fileWriter);
                    //bw.write(data , 0 , content); // probar data, 0 , final de donde tiene que escribir
                   // bw.close();
                    //tal vez escribir desde 0 hasta el filelenght

                }catch (IOException E){
                    E.printStackTrace();
                }
                break;
        }

    }
    public String getMusic(){
        return this.music;
    }
    public void resetMusic(){
        this.music = null;
    }
}
