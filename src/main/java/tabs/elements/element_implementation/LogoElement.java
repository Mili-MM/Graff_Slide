package tabs.elements.element_implementation;

import repository.graff_components.GraffNode;
import tabs.elements.GraffSlideElement;

import java.awt.*;

public class LogoElement extends GraffSlideElement {

    public LogoElement(String title, GraffNode parent, Point lokacija, Dimension dimension) {
        super(title, parent, lokacija, dimension);
    }

    @Override
    public Prototype kopiraj() {
        Point locationCopy = new Point(getLocation().x, getLocation().y);
        locationCopy.translate(5, 5);
        LogoElement copy = new LogoElement(
                this.getTitle() + " COPY",
                getParent(),
                locationCopy,
                getDimension());
        return copy;
    }
}
