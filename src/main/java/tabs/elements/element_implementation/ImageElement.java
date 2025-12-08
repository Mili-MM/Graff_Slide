package tabs.elements.element_implementation;

import lombok.Getter;
import repository.graff_components.GraffNode;
import tabs.elements.GraffSlideElement;

import java.awt.*;
import java.awt.image.BufferedImage;
@Getter
public class ImageElement extends GraffSlideElement {
    private BufferedImage image;

    public ImageElement(String title, GraffNode parent, Point lokacija, Dimension dimension, BufferedImage image) {
        super(title, parent, lokacija, dimension);
        this.image = image;
    }

    @Override
    public Prototype kopiraj() {
        Point locationCopy = new Point(getLocation().x, getLocation().y);
        locationCopy.translate(5, 5);
        ImageElement copy = new ImageElement(
                this.getTitle() + " COPY",
                getParent(),
                locationCopy,
                getDimension(),
                image);
        return copy;
    }
}
