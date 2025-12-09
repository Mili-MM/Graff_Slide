package tabs.state.state_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.GraffSlideElement;
import tabs.state.ToolState;
import tabs.state.slide.SlideController;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveState implements ToolState {

    private Point lastPoint;

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {
        lastPoint = e.getPoint();
        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()){
            GraffSlideElement element = (GraffSlideElement) child;
            System.out.println(element.getTitle());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e, SlideController slideController) {
        Point now = e.getPoint();
        int dx = now.x - lastPoint.x;
        int dy = now.y - lastPoint.y;

        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()){
            GraffSlideElement element = (GraffSlideElement) child;
            System.out.println(element.getTitle());
            if (element.isSelected()){
                element.translate(dx, dy);
            }
        }

        lastPoint = now;
        slideController.getSlideView().repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e, SlideController slideController) {
        lastPoint = null;
    }
}

