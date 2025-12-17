package tabs.mediator;

import tabs.mediator.window_modes.WindowMode;
import tabs.mediator.window_modes.WindowModeAbstraction;

public interface WindowMediator {
    void changeMode(WindowMode mode);
}
