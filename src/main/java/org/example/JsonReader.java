package org.example;

import java.nio.file.Paths;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

    public static Island setupIslandFromJson(String path){

        Island island = new Island();

        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            // convert JSON string to Island object
            island = mapper.readValue(Paths.get(path).toFile(), Island.class);

            // print Island
//            System.out.println(island.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return island;
    }

    public static void getEventsFromJson(String path) {

        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            // convert JSON string to Island object
            Island island = mapper.readValue(Paths.get(path).toFile(), Island.class);

            // print Island
            System.out.println(island.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
