package tabs.state.state_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.GraffSlideElement;
import tabs.state.ToolState;
import tabs.state.slide.SlideController;

import java.awt.event.MouseEvent;

public class DeleteState implements ToolState {

    @Override
    public void mousePressed(MouseEvent e, SlideController slideController) {
        for (GraffNode child : ((GraffNodeComposite) slideController.getSlide()).getChildren()){
            GraffSlideElement element = (GraffSlideElement) child;
            if (element.isSelected()){
                ((GraffNodeComposite) slideController.getSlide()).removeChild(element);
            }
        }

        slideController.getSlideView().repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e, SlideController slideController) {}

    @Override
    public void mouseReleased(MouseEvent e, SlideController slideController) {}
}

