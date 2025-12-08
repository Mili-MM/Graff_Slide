package tabs.elements;

import lombok.Getter;
import repository.graff_components.GraffLeaf;
import repository.graff_components.GraffNode;
import tabs.elements.element_implementation.Prototype;

import java.awt.*;

@Getter
public abstract class GraffSlideElement extends GraffLeaf implements Prototype {
    private Point location;
    private Dimension dimension;
    private int rotacija = 0;

    public GraffSlideElement(String title, GraffNode parent, Point lokacija, Dimension dimension) {
        super(title, parent.getAuthor(), parent);
        this.location = lokacija;
        this.dimension = dimension;
    }
}
