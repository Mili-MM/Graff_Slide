package tabs.elements;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffLeaf;
import repository.graff_components.GraffNode;
import tabs.elements.element_implementation.Prototype;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

@Getter
@Setter
public abstract class GraffSlideElement extends GraffLeaf implements Prototype {
    private Point location;
    private Dimension dimension;
    private double rotacija = 0;
    private boolean isSelected = false;

    public GraffSlideElement(String title, GraffNode parent, Point lokacija, Dimension dimension) {
        super(title, parent.getAuthor(), parent);
        this.location = lokacija;
        this.dimension = dimension;
    }

    public void translate(int dx, int dy){
        location.translate(dx, dy);
    }

    public void resize(int dx, int dy){
        dimension.setSize(dimension.width - dx, dimension.height - dy);
    }

    public void rotate(double angle){
        rotacija += angle;
    }
}
