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

            // convert JSON string to Island object
            Island island = mapper.readValue(Paths.get("src/main/java/org/jsonFile/attackOnTitans.json").toFile(), Island.class);

            // print Island
            System.out.println(island.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static void getEventsFromJson() {

        try {
            // create object mapper instance
            ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

            // convert JSON string to Island object
            Island island = mapper.readValue(Paths.get("src/main/java/org/jsonFile/attackOnTitans.json").toFile(), Island.class);

            // print Island
            System.out.println(island.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
