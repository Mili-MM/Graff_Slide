package tabs.elements.painters;

import java.awt.*;

public interface Painter {
    void paint(Graphics2D g);
    boolean elementAt(Point p);
}
