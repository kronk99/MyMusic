package Server;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Checker {
    private int value;
    private xmlBuilder xmlBuilder;
    public Checker() throws TransformerConfigurationException, ParserConfigurationException {
        value = 0;
        xmlBuilder = new xmlBuilder();
    }
    public void check(String filename, String data) throws IOException, TransformerException, SAXException {
        switch (filename){
            case "registro.xml":
                xmlBuilder.buildXml(filename, data);
                System.out.println("se creo el xml");
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
            default:
                //aca si no es ninguno de esos nombres debe crear una file y guardarla en una careta

                try{
                    //metodo llega pero no a la carpeta de musica.
                    System.out.println("va a crearse la file");
                    System.out.println(filename);
                    File file = new File("C:\\Users\\Luis\\Documents\\repositorio gitkraken\\MyMusic\\PROYECTO2\\Servidor\\Music" + filename + "1" + ".mp3");
                    System.out.println("se creo la file");
                    file.createNewFile();
                    System.out.println("se guardo la file");
                    FileWriter fileWriter = new FileWriter(file.getAbsolutePath());
                    BufferedWriter bw = new BufferedWriter(fileWriter);
                    bw.write(data); // probar data, 0 , final de donde tiene que escribir
                    bw.close();

                }catch (IOException E){
                    E.printStackTrace();
                }
                break;
        }

    }
}
