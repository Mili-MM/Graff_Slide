package serijalizacija;

import lombok.Getter;
import lombok.Setter;
import serijalizacija.model.JacksonSerializer;
import serijalizacija.model.Projekat;
import serijalizacija.model.Serializer;

import java.io.File;

@Getter @Setter
public class SerializationImplementation {

    private Serializer serializer;

    public SerializationImplementation() {
        serializer = new JacksonSerializer();
    }

    public void serialize(Projekat p, File f){
        serializer.saveProject(p,f);
    }

    public Projekat deSerialize(File f){
        //ovde da implementiram da otvara fajl u aplikaciji, a moze i u openAction direktno
        return serializer.loadProject(f);
    }




}
