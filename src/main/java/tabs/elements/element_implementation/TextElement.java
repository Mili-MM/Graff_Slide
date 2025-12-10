package tabs.elements.element_implementation;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;
import tabs.elements.GraffSlideElement;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter @Setter
public class TextElement extends GraffSlideElement {

    private String text;
    private Font font = new Font("Arial", Font.PLAIN, 20);

    public TextElement(String title, GraffNode parent, Point lokacija, Dimension dimension, String text) {
        super(title, parent, lokacija, dimension);
        this.text = text;
    }

    public TextElement(String title, GraffNode parent, Point lokacija, Dimension dimension) {
        super(title, parent, lokacija, dimension);
        this.text = "";
    }

    @Override
    public Prototype kopiraj() {
        return null;
    }

    public int getFontSize(){
        return font.getSize();
    }

    public void setFontSize(int fontSize){
        font = new Font("Arial", Font.PLAIN, fontSize);
    }
}

