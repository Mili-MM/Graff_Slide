package serijalizacija;

import com.fasterxml.jackson.databind.ObjectMapper;
import serijalizacija.model.SavedProject;

import java.io.File;
import java.io.IOException;

public class JacksonSerializer implements Serializer{

    ObjectMapper objectMapper;

    public JacksonSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT);
    }


    @Override
    public SavedProject loadProject(File file) {
        try {
            return objectMapper.readValue(file, SavedProject.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProject(SavedProject savedProject, File file) {
        try {
            objectMapper.writeValue(file, savedProject);
            System.out.println("Projekat je sačuvan u: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
