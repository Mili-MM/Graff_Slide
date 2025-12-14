package serijalizacija.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffLeaf;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;
import repository.graff_implementation.Presentation;
import repository.graff_implementation.Project;
import repository.graff_implementation.Slide;
import serijalizacija.JacksonSerializer;
import serijalizacija.SerializationImplementation;
import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.ImageElement;
import tabs.elements.element_implementation.LogoElement;
import tabs.elements.element_implementation.TextElement;

import java.util.ArrayList;
import java.util.List;
@Getter
public class SavedProject {
    private String name;
    private SavedNode root;

    public SavedProject() {
    }

    public SavedProject(GraffNode project) {
        this.name = project.getTitle();
        this.root = ((JacksonSerializer)MainFrame.getInstance().getSerijalizator().getSerializer()).map(project);
    }

}
