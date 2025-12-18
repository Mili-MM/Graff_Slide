package tabs.mediator;

import raf.graffito.dsw.gui.swing.MainFrame;
import tabs.mediator.window_modes.WindowMode;
import tabs.mediator.window_modes.WindowModeAbstraction;
import tabs.mediator.window_modes.window_mode_implementation.FullScreenMode;
import tabs.mediator.window_modes.window_mode_implementation.NormalMode;
import tabs.mediator.window_modes.window_mode_implementation.SmallMode;
import tabs.state.slide.SlideView;

public class WindowMediatorImplementation implements WindowMediator {

    private WindowModeAbstraction currentMode;
    private SlideView slideView;

    public WindowMediatorImplementation(SlideView slideView) {
        this.slideView = slideView;
        this.currentMode = new NormalMode(); // default
    }

    @Override
    public void changeMode(WindowMode mode) {
        MainFrame mainFrame = MainFrame.getInstance();
        if (mode == WindowMode.NORMAL) currentMode = new NormalMode();
        else if (mode == WindowMode.SMALL) currentMode = new SmallMode();
        else if (mode == WindowMode.FULLSCREEN) {
            currentMode = new FullScreenMode();
            mainFrame.enterFullScreen();
        }

        slideView.updateScale(currentMode);
        mainFrame.updateSize(slideView.getScaleFactor());
    }
}

