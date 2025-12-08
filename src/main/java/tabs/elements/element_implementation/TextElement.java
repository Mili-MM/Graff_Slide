package tabs.elements.element_implementation;

import repository.graff_components.GraffNode;
import tabs.elements.GraffSlideElement;

import java.awt.*;

public class TextElement extends GraffSlideElement {
    private String text;

    public TextElement(String title, GraffNode parent, Point lokacija, Dimension dimension, String text) {
        super(title, parent, lokacija, dimension);
        this.text = text;
    }

    @Override
    public Prototype kopiraj() {
        Point locationCopy = new Point(getLocation().x, getLocation().y);
        locationCopy.translate(5, 5);
        TextElement copy = new TextElement(
                this.getTitle() + " COPY",
                getParent(),
                locationCopy,
                getDimension(),
                text);
        return copy;
    }
}
