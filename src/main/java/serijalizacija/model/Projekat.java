package serijalizacija.model;


import lombok.Getter;
import lombok.Setter;

//klasa koja ce da upakuje elemente koji se cuvaju u JSON formatu
@Getter@Setter
public class Projekat {

    private String name;

    public Projekat() {
    }

    public Projekat(String name) {
        this.name = name;
    }
}
