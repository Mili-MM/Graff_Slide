package serijalizacija;

import serijalizacija.model.Projekat;

import java.io.File;

public interface Serializer {
    Projekat loadProject(File file);
    void saveProject(Projekat project, File file);
}
