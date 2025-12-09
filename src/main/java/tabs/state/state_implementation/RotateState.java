package tabs.state.state_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.Slide;
import tabs.elements.GraffSlideElement;
import tabs.state.ToolState;
import tabs.state.slide.SlideController;

import java.awt.*;
import java.awt.event.MouseEvent;

public class RotateState implements ToolState {

    private Point last;

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {
        last = e.getPoint();
    }

    @Override
    public void mouseDragged(MouseEvent e, SlideController slideController) {
        Point now = e.getPoint();

        double angle = Math.toRadians(90);

        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()){
            GraffSlideElement element = (GraffSlideElement) child;
            if (element.isSelected()){
                element.rotate(angle);
            }
        }

        last = now;
        slideController.getSlideView().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e, SlideController slideController) {}
}

