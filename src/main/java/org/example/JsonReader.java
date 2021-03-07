package org.example;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
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
            island = mapper.readValue(getFileFromResource(path), Island.class);

            // print Island
//            System.out.println(island.toString());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return island;
    }
    private static File getFileFromResource(String fileName) throws URISyntaxException {

        ClassLoader classLoader = App.class.getClassLoader();
        URL resource = classLoader.getResource(fileName);
        if (resource == null) {
            throw new IllegalArgumentException("file not found! " + fileName);
        } else {

            // failed if files have whitespaces or special characters
            //return new File(resource.getFile());

            return new File(resource.toURI());
        }

    }

}
