package Server;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class SongsDataManagement {

    // Método para leer el archivo JSON
    public SongsList readAllData(){

        SongsList songsList = new SongsList();

        try {
            songsList= new ObjectMapper().readerFor(SongsList.class).readValue(new File("songs.json"));
        }catch (IOException e){
            e.printStackTrace();
        }

        return songsList;
    }

    // Método para escribir en el archivo JSON
    public void writeAllData(SongsList theList) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("songs.json"), theList);

        }catch (JsonGenerationException e){
            e.printStackTrace();
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
