package tabs.elements.painters;

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
        if (image.isSelected()) {
            g.setColor(new Color(0, 150, 0));
            g.setStroke(new BasicStroke(3));
            g.drawRect(
                    image.getLocation().x - 2,
                    image.getLocation().y - 2,
                    image.getDimension().width + 4,
                    image.getDimension().height + 4
            );
        }
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
