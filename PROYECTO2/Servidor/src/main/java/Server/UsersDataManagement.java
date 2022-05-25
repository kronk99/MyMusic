package Server;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class UsersDataManagement {

    // Método que se encarga de leer el archivo JSON
    public UsersList readAllData(){

        UsersList usersList = new UsersList();

        try {
            usersList = new ObjectMapper().readerFor(UsersList.class).readValue(new File("users.json"));
        }catch (IOException e){
            e.printStackTrace();
        }

        return usersList;
    }

    public void writeAllData(UsersList theList) {

        ObjectMapper objectMapper = new ObjectMapper();

        try {

            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("users.json"), theList);

        }catch (JsonGenerationException e){
            e.printStackTrace();
        }catch (JsonMappingException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
