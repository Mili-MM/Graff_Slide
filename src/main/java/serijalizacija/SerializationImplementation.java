package serijalizacija;

import jtree.model.GraffTreeItem;
import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffLeaf;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;
import repository.graff_implementation.GraffRepositoryFactory;
import serijalizacija.model.SavedNode;
import serijalizacija.model.SavedNodeType;
import serijalizacija.model.SavedProject;
import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.ImageElement;
import tabs.elements.element_implementation.LogoElement;
import tabs.elements.element_implementation.TextElement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter @Setter
public class SerializationImplementation {
    private Serializer serializer;
    private File lastSelectedPath = null;

    public SerializationImplementation() {
        serializer = new JacksonSerializer();
    }

    public void serialize(SavedProject p, File f){
        if (!isInResources(f)) lastSelectedPath = f;
        serializer.saveProject(p,f);
    }

    public SavedProject deSerialize(File f){
        if (!isInResources(f)) lastSelectedPath = f;
        return serializer.loadProject(f);
    }

    private boolean isInResources(File f) {
        try {
            Path filePath = f.toPath().toRealPath();

            Path classesRoot = Paths.get(
                    getClass()
                            .getProtectionDomain()
                            .getCodeSource()
                            .getLocation()
                            .toURI()
            ).toRealPath();

            return filePath.startsWith(classesRoot);
        } catch (Exception e) {
            return false;
        }
    }



}
