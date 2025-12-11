package tabs.undoredo.command_implementation;

import tabs.elements.GraffSlideElement;
import tabs.undoredo.Command;

import java.awt.*;
import java.util.ArrayList;

public class ResizeImageCommand implements Command {

    private ArrayList<GraffSlideElement> elements = new ArrayList<>();
    private ArrayList<Dimension> oldDimensions = new ArrayList<>();
    private ArrayList<Dimension> newDimensions = new ArrayList<>();

    public void addElement(GraffSlideElement element, Dimension newDimension, Dimension oldDimension) {
        elements.add(element);
        newDimensions.add(newDimension);
        oldDimensions.add(oldDimension);
    }

    @Override
    public void execute() {
        for (int i=0; i<elements.size(); i++) {
            elements.get(i).setDimension(newDimensions.get(i));
        }
    }

    @Override
    public void undo() {
        for (int i=0; i<elements.size(); i++) {
            elements.get(i).setDimension(oldDimensions.get(i));
        }
    }
}
