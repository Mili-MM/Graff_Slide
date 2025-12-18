package tabs.elements.element_implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;
import tabs.elements.GraffSlideElement;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageElement extends GraffSlideElement {
    @JsonIgnore
    private BufferedImage image;
    @Setter @Getter
    private String imagePath;

    public ImageElement(GraffNode parent, Point lokacija, Dimension dimension, BufferedImage image) {
        super(parent, lokacija, dimension);
        this.image = image;
    }

    @Override
    public GraffSlideElement kopiraj() {
        Point locationCopy = new Point(getLocation().x, getLocation().y);
        locationCopy.translate(5, 5);
        ImageElement copy = new ImageElement(
                getParent(),
                locationCopy,
                getDimension(),
                image);
        return copy;
    }

    public void move(int dx, int dy){
        Point location = super.getLocation();
        location.translate(dx, dy);
        super.setLocation(location);
    }

    public void resize(int dx, int dy) {
        Dimension dimension = getDimension();
        dimension.width = Math.max(10, dimension.width + dx);
        dimension.height = Math.max(10, dimension.height + dy);
    }
    @JsonIgnore
    public BufferedImage getImage() {
        return image;
    }
}
