package tabs.undoredo.command_implementation;

import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.TextElement;
import tabs.undoredo.Command;

import java.awt.*;
import java.util.ArrayList;

public class ResizeTextCommand implements Command {

    private ArrayList<GraffSlideElement> elements = new ArrayList<>();
    private ArrayList<Integer> oldFontSizes = new ArrayList<>();
    private ArrayList<Integer> newFontSizes = new ArrayList<>();

    public void addElement(GraffSlideElement element, int newFonttSize, int oldFontSize) {
        elements.add(element);
        newFontSizes.add(newFonttSize);
        oldFontSizes.add(oldFontSize);
    }

    @Override
    public void execute() {
        for (int i=0; i<elements.size(); i++) {
            ((TextElement) elements.get(i)).setFontSize(newFontSizes.get(i));
        }
    }

    @Override
    public void undo() {
        for (int i=0; i<elements.size(); i++) {
            ((TextElement) elements.get(i)).setFontSize(oldFontSizes.get(i));
        }
    }
}

