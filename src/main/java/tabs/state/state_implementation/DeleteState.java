package tabs.state.state_implementation;

import jtree.GraffTree;
import jtree.GraffTreeImplementation;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.GraffSlideElement;
import tabs.state.ToolState;
import tabs.state.slide.SlideController;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class DeleteState implements ToolState {

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {
        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()) {
            GraffSlideElement element = (GraffSlideElement) child;
            AffineTransform currentTransform = slideController.getSlideView().getCurrentTransform();
            Point transformedPoint = transformPoint(e.getPoint(), currentTransform);

            if (element.getLocation().getX() <= transformedPoint.x && transformedPoint.x <= element.getLocation().getX()
                    + element.getDimension().getWidth() &&
                    element.getLocation().getY() <= transformedPoint.y && transformedPoint.y <= element.getLocation().getY()
                    + element.getDimension().getHeight()){

                ((GraffNodeComposite) slideController.getSlide()).removeChild(element);
                ((GraffTreeImplementation)MainFrame.getInstance().getTree()).removeChild(slideController.getSlide(), element);
                break;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e, SlideController slideController) {}

    @Override
    public void mouseReleased(MouseEvent e, SlideController slideController) {}

    @Override
    public void mouseWheelMoved(MouseWheelEvent e, SlideController slideController) {}
}

