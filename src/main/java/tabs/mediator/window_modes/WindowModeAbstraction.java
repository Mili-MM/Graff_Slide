package tabs.mediator.window_modes;

import tabs.mediator.scale.ScaleImplementor;

import java.awt.*;

public abstract class WindowModeAbstraction {

    protected ScaleImplementor scaleImplementor;

    public WindowModeAbstraction(ScaleImplementor scaleImplementor) {
        this.scaleImplementor = scaleImplementor;
    }

    public abstract double getScale(Dimension window, Dimension slide);
}

