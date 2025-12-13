package serijalizacija.model;


import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;

import java.util.ArrayList;
import java.util.List;

//klasa koja ce da upakuje elemente koji se cuvaju u JSON formatu
@Getter@Setter
public class Projekat {

    private String name;
    private String path;
    private List<String> lista = new ArrayList<>();

    public Projekat() {
    }

    public Projekat(String name) {
        this.name = name;
    }

    public void addChild(GraffNode gf){
        String s = new String("{" + gf.getTitle() + " | " + gf.getType() + "}");
        lista.add(s);
    }

    @Override
    public String toString() {
        return lista.toString();
    }
}
