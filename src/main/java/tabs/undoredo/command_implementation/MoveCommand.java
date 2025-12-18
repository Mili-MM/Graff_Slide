package tabs.undoredo.command_implementation;

import lombok.Setter;
import tabs.elements.GraffSlideElement;
import tabs.undoredo.Command;

import java.awt.*;
import java.util.ArrayList;

public class MoveCommand implements Command {

    private ArrayList<GraffSlideElement> elements = new ArrayList<>();
    private ArrayList<Point> oldLocations = new ArrayList<>();
    private ArrayList<Point> newLocations = new ArrayList<>();

    public void addElement(GraffSlideElement element, Point newLocation, Point oldLocation) {
        elements.add(element);
        newLocations.add(newLocation);
        oldLocations.add(oldLocation);
    }

    @Override
    public void execute() {
        for (int i=0; i<elements.size(); i++) {
            elements.get(i).setLocation(newLocations.get(i));
        }
    }

    @Override
    public void undo() {
        for (int i=0; i<elements.size(); i++) {
            elements.get(i).setLocation(oldLocations.get(i));
        }
    }
}

