package serijalizacija;

import lombok.Getter;
import lombok.Setter;
import serijalizacija.model.SavedProject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Getter @Setter
public class SerializationImplementation {

    private Serializer serializer;
    private List<SavedProject> sacuvani = new ArrayList<>();

    public SerializationImplementation() {
        serializer = new JacksonSerializer();
    }

    public void serialize(SavedProject p, File f){
        serializer.saveProject(p,f);
    }

    public SavedProject deSerialize(File f){
        return serializer.loadProject(f);
    }




}
