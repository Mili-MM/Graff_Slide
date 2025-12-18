package tabs.mediator.window_modes.window_mode_implementation;

import tabs.mediator.scale.scale_implementation.SmallScale;
import tabs.mediator.window_modes.WindowModeAbstraction;

import java.awt.*;

public class SmallMode extends WindowModeAbstraction {
    public SmallMode() {
        super(new SmallScale());
    }

    @Override
    public double getScale(Dimension window, Dimension slide) {
        return scaleImplementor.calculateScale(window, slide);
    }
}

