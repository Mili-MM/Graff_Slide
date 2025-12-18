package tabs.undoredo.command_implementation;

import tabs.elements.GraffSlideElement;
import tabs.undoredo.Command;

import java.awt.*;
import java.util.ArrayList;

public class RotateCommand implements Command {

    private ArrayList<GraffSlideElement> elements = new ArrayList<>();
    private ArrayList<Double> oldAngles = new ArrayList<>();
    private ArrayList<Double> newAngles = new ArrayList<>();

    public void addElement(GraffSlideElement element, Double newAngle, Double oldAngle) {
        elements.add(element);
        oldAngles.add(oldAngle);
        newAngles.add(newAngle);
    }

    @Override
    public void execute() {
        for (int i=0; i<elements.size(); i++) {
            elements.get(i).setRotacija(newAngles.get(i));
        }
    }

    @Override
    public void undo() {
        for (int i=0; i<elements.size(); i++) {
            elements.get(i).setRotacija(oldAngles.get(i));
        }
    }
}

