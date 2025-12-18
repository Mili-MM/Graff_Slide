package tabs.mediator.window_modes.window_mode_implementation;

import tabs.mediator.scale.scale_implementation.FullScreenScale;
import tabs.mediator.window_modes.WindowModeAbstraction;

import java.awt.*;

public class FullScreenMode extends WindowModeAbstraction {
    public FullScreenMode() {
        super(new FullScreenScale());
    }

    @Override
    public double getScale(Dimension window, Dimension slide) {
        return scaleImplementor.calculateScale(window, slide);
    }
}

