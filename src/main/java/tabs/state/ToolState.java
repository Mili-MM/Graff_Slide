package tabs.state;

import tabs.state.slide.SlideController;
import tabs.state.slide.SlideView;

import java.awt.event.MouseEvent;

public interface ToolState {
    void mousePressed(MouseEvent e, SlideController slideController);
    void mouseDragged(MouseEvent e, SlideController slideController);
    void mouseReleased(MouseEvent e, SlideController slideController);

}
