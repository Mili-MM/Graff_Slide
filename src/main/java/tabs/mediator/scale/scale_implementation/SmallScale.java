package tabs.mediator.scale.scale_implementation;

import tabs.mediator.scale.ScaleImplementor;

import java.awt.*;

public class SmallScale implements ScaleImplementor {
    @Override
    public double calculateScale(Dimension window, Dimension slide) {
        return 0.5;
    }
}

