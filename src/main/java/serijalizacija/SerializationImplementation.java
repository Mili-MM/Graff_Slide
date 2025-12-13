package serijalizacija;

import lombok.Getter;
import lombok.Setter;
import serijalizacija.model.Projekat;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SerializationImplementation {

    private Serializer serializer;
    private List<Projekat> sacuvani = new ArrayList<>();

    public SerializationImplementation() {
        serializer = new JacksonSerializer();
    }

    public void serialize(Projekat p, File f){
        serializer.saveProject(p,f);
    }

    public Projekat deSerialize(File f){
        return serializer.loadProject(f);
    }




}
