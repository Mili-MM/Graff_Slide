package tabs.mediator.window_modes.window_mode_implementation;

import tabs.mediator.scale.scale_implementation.NormalScale;
import tabs.mediator.window_modes.WindowModeAbstraction;

import java.awt.*;

public class NormalMode extends WindowModeAbstraction {
    public NormalMode() {
        super(new NormalScale());
    }

    @Override
    public double getScale(Dimension window, Dimension slide) {
        return scaleImplementor.calculateScale(window, slide);
    }
}
