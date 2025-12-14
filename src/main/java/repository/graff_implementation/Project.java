package repository.graff_implementation;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;

import java.awt.*;

@Setter @Getter
public class Project extends GraffNodeComposite {
    private int number;
    private boolean isModified = false;
    private Color color;

    public Project(String title, String author, GraffNode parent) {
        super(title, author, parent);
        number = 1;
        super.setType(GraffNodeType.PROJECT);
    }


}
