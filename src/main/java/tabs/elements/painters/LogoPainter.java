package tabs.elements.painters;

import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.LogoElement;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.awt.geom.Rectangle2D;

public class LogoPainter extends PrimordialPainter{
    LogoElement logoElement;

    public LogoPainter(LogoElement logoElement) {
        super(logoElement);
        this.logoElement = logoElement;
        Point p = logoElement.getLocation();
        Dimension d = logoElement.getDimension();

        this.oblik = new Rectangle2D.Double(p.x, p.y, d.width, d.height);
    }

    @Override
    public void paint(Graphics2D g) {
        Path2D logo = new Path2D.Double();
        logo.moveTo(0, -40);
        logo.lineTo(30, -10);
        logo.lineTo(20, 35);
        logo.lineTo(-20, 35);
        logo.lineTo(-30, -10);
        logo.closePath();

        g.setColor(Color.MAGENTA);
        g.fill(logo);

        g.setColor(Color.BLACK);
        g.draw(logo);
    }
}
