package tabs.state.state_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.GraffSlideElement;
import tabs.state.ToolState;
import tabs.state.slide.SlideController;
import tabs.state.slide.SlideView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class SelectState implements ToolState {
    private AffineTransform currentTransform = new AffineTransform();

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {
        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()) {
            GraffSlideElement element = (GraffSlideElement) child;
            Point transformedPoint = transformPoint(e.getPoint());

            if (element.getLocation().getX() <= transformedPoint.x && transformedPoint.x <= element.getLocation().getX()
                    + element.getDimension().getWidth() &&
                    element.getLocation().getY() <= transformedPoint.y && transformedPoint.y <= element.getLocation().getY()
                    + element.getDimension().getHeight()){

                element.setSelected(!element.isSelected());
                break;
            }
        }
        slideController.getSlideView().repaint();
    }


    @Override
    public void mouseDragged(MouseEvent e, SlideController slide) {}

    @Override
    public void mouseReleased(MouseEvent e, SlideController slide) {}

    private Point transformPoint(Point p) {
        try {
            AffineTransform inverseTransform = currentTransform.createInverse();
            Point2D.Double src = new Point2D.Double(p.x, p.y);
            Point2D.Double dest = new Point2D.Double();
            inverseTransform.transform(src, dest);
            return new Point((int) dest.x, (int) dest.y);
        } catch (Exception e) {
            return p;
        }
    }
}

