package tabs.elements.painters;

import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.TextElement;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class TextPainter extends PrimordialPainter {

    private TextElement textElement;

    public TextPainter(TextElement element) {
        super(element);
        this.textElement = element;
    }

    @Override
    public void paint(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g.create();

        int x = textElement.getLocation().x;
        int y = textElement.getLocation().y;
        int w = textElement.getDimension().width;
        int h = textElement.getDimension().height;

        // ----- Primeni rotaciju -----
        double rotation = textElement.getRotacija();
        double cx = x + w / 2.0;
        double cy = y + h / 2.0;

        AffineTransform old = g2.getTransform();
        g2.rotate(rotation, cx, cy);

        // ----- Nacrtaj tekst -----
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.PLAIN, 18));

        FontMetrics fm = g2.getFontMetrics();
        int textY = y + fm.getAscent() + 5;

        for (String line : textElement.getText().split("\n")) {
            g2.drawString(line, x + 5, textY);
            textY += fm.getHeight();
        }

        // ----- Okvir ako je selektovan -----
        if (textElement.isSelected()) {
            g2.setColor(new Color(0, 150, 0));
            g2.setStroke(new BasicStroke(2));
            g2.drawRect(x - 2, y - 2, w + 4, h + 4);
        }

        g2.setTransform(old);
        g2.dispose();
    }
}
