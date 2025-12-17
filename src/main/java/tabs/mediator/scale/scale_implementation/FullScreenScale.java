package tabs.mediator.scale.scale_implementation;

import tabs.mediator.scale.ScaleImplementor;

import java.awt.*;

public class FullScreenScale implements ScaleImplementor {
    @Override
    public double calculateScale(Dimension window, Dimension slide) {
        double sx = window.getWidth() / slide.getWidth();
        double sy = window.getHeight() / slide.getHeight();
        return Math.min(sx, sy);
    }
}

