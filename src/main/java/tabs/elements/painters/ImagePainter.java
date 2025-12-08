package tabs.elements.painters;

import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.ImageElement;

import java.awt.*;

public class ImagePainter extends PrimordialPainter{
    private ImageElement image;

    public ImagePainter(ImageElement image) {
        super(image);
        this.image = image;
    }

    @Override
    public void paint(Graphics2D g) {
        g.drawImage(
                image.getImage(),
                image.getLocation().x,
                image.getLocation().y,
                image.getDimension().width,
                image.getDimension().height,
                null
        );
    }
}
