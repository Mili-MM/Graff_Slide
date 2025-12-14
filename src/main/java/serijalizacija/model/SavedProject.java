package serijalizacija.model;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import repository.graff_components.GraffLeaf;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;
import repository.graff_implementation.Presentation;
import repository.graff_implementation.Project;
import repository.graff_implementation.Slide;
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
        this.root = map(project);
    }

    private SavedNode map(GraffNode node) {
        SavedNode sn = new SavedNode();

        sn.setName(node.getTitle());

       if (node instanceof GraffLeaf leaf) {
           GraffSlideElement slide = (GraffSlideElement) leaf;
           if (slide instanceof ImageElement) sn.setType(String.valueOf(SavedNodeType.IMAGE));
           else if (slide instanceof LogoElement) sn.setType(String.valueOf(SavedNodeType.LOGO));
           else if (slide instanceof TextElement) sn.setType(String.valueOf(SavedNodeType.TEXT));
       }
       else if (node instanceof GraffNodeComposite composite) {
           if (composite.getType() == GraffNodeType.PROJECT) sn.setType(String.valueOf(SavedNodeType.PROJECT));
           else if (composite.getType() == GraffNodeType.PRESENTATION) sn.setType(String.valueOf(SavedNodeType.PRESENTATION));
           else if (composite.getType() == GraffNodeType.SLIDE) sn.setType(String.valueOf(SavedNodeType.SLIDE));
       }

        fillProps(node, sn);

        // samo composite imaju decu
        if (node instanceof GraffNodeComposite comp) {
            for (GraffNode child : comp.getChildren()) {
                sn.getChildren().add(map(child));
            }
        }

        return sn;
    }


    private void fillProps(GraffNode node, SavedNode sn) {

        if (node instanceof GraffSlideElement el) {
            sn.getProps().put("x", el.getLocation().x);
            sn.getProps().put("y", el.getLocation().y);
            sn.getProps().put("width", el.getDimension().width);
            sn.getProps().put("height", el.getDimension().height);
        }

        if (node instanceof ImageElement img) {
            sn.getProps().put("imagePath", img.getImagePath());
        }

        if (node instanceof TextElement txt) {
            sn.getProps().put("text", txt.getText());
            sn.getProps().put("fontSize", txt.getFontSize());
        }
    }
}
