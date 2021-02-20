package org.example;

import java.nio.file.Paths;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

    public static void getIslandFromJson(){

        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            // convert JSON string to Book object
            Island island = mapper.readValue(Paths.get("C:\\Users\\Theo\\Documents\\ESGI\\COURS\\Outils et techniques de développement\\El_Presidente-main\\src\\main\\java\\org\\example\\attackOnTitans.json").toFile(), Island.class);

            // print book
            System.out.println(island.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
