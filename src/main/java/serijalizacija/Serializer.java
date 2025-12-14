package serijalizacija;

import serijalizacija.model.SavedProject;

import java.io.File;

public interface Serializer {
    SavedProject loadProject(File file);
    void saveProject(SavedProject project, File file);
}
