package serijalizacija;

import com.fasterxml.jackson.databind.ObjectMapper;
import jtree.model.GraffTreeItem;
import repository.graff_components.GraffLeaf;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;
import repository.graff_implementation.GraffRepositoryFactory;
import serijalizacija.model.SavedNode;
import serijalizacija.model.SavedNodeType;
import serijalizacija.model.SavedProject;
import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.ImageElement;
import tabs.elements.element_implementation.LogoElement;
import tabs.elements.element_implementation.TextElement;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class JacksonSerializer implements Serializer{

    ObjectMapper objectMapper;

    public JacksonSerializer() {
        this.objectMapper = new ObjectMapper();
        this.objectMapper.enable(com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT);
    }


    @Override
    public SavedProject loadProject(File file) {
        try {
            return objectMapper.readValue(file, SavedProject.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProject(SavedProject savedProject, File file) {
        try {
            objectMapper.writeValue(file, savedProject);
            System.out.println("Projekat je sačuvan u: " + file.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void build(SavedNode sn, GraffTreeItem parent) throws IOException {
        GraffRepositoryFactory graffFactory = new GraffRepositoryFactory();
        GraffNode node;
        if (sn.getType().equals("PROJECT")) {
            node = graffFactory.createProject(sn.getName(), "", parent.getGraffNode());
        }
        else if (sn.getType().equals("PRESENTATION")) {
            node = graffFactory.createPresentation(sn.getName(), "", parent.getGraffNode());
        }
        else if (sn.getType().equals("SLIDE")) {
            node = graffFactory.createSlide(sn.getName(), "", parent.getGraffNode());
        }
        else if (sn.getType().equals("IMAGE")) {
            double rotation = (Double) sn.getProps().get("rotation");
            Point location = new Point((Integer) sn.getProps().get("x"), (Integer) sn.getProps().get("y"));
            Dimension dimension = new Dimension((Integer) sn.getProps().get("width"), (Integer) sn.getProps().get("height"));
            String imagePath = sn.getProps().get("imagePath").toString();
            File f = new File(imagePath);
            System.out.println(f.getAbsolutePath());
            BufferedImage img = ImageIO.read(f.getAbsoluteFile());
            node = new ImageElement(parent.getGraffNode(), location, dimension, img);
            ((ImageElement) node).setRotacija(rotation);
            ((ImageElement) node).setImagePath(imagePath);
        }
        else if (sn.getType().equals("TEXT")){
            double rotation = (Double) sn.getProps().get("rotation");
            Point location = new Point((Integer) sn.getProps().get("x"), (Integer) sn.getProps().get("y"));
            Dimension dimension = new Dimension((Integer) sn.getProps().get("width"), (Integer) sn.getProps().get("height"));
            String text = sn.getProps().get("text").toString();
            int fontSize = Integer.parseInt(sn.getProps().get("fontSize").toString());
            node = new TextElement(parent.getGraffNode(), location, dimension);
            ((TextElement) node).setText(text);
            ((TextElement) node).setFontSize(fontSize);
            ((TextElement) node).setRotacija(rotation);
        }
        else if (sn.getType().equals("LOGO")){
            double rotation = (Double) sn.getProps().get("rotation");
            Point location = new Point((Integer) sn.getProps().get("x"), (Integer) sn.getProps().get("y"));
            Dimension dimension = new Dimension((Integer) sn.getProps().get("width"), (Integer) sn.getProps().get("height"));
            node = new LogoElement(parent.getGraffNode(), location, dimension);
            ((LogoElement) node).setRotacija(rotation);
        }
        else return;
        GraffTreeItem wrapper = new GraffTreeItem(node);
        parent.add(wrapper);
        ((GraffNodeComposite)parent.getGraffNode()).addChild(node);
        for (SavedNode child : sn.getChildren()){
            build(child, wrapper);
        }


    }

    public SavedNode map(GraffNode node) {
        SavedNode sn = new SavedNode();

        sn.setName(node.getTitle());

        if (node instanceof GraffLeaf leaf) {
            GraffSlideElement slide = (GraffSlideElement) leaf;
            if (slide instanceof ImageElement) sn.setType(String.valueOf(SavedNodeType.IMAGE));
            else if (slide instanceof LogoElement) sn.setType(String.valueOf(SavedNodeType.LOGO));
            else if (slide instanceof TextElement) sn.setType(String.valueOf(SavedNodeType.TEXT));
        }
        else if (node instanceof GraffNodeComposite composite) {
            if (composite.getType() == GraffNodeType.PROJECT) sn.setType(String.valueOf(SavedNodeType.PROJECT));
            else if (composite.getType() == GraffNodeType.PRESENTATION) sn.setType(String.valueOf(SavedNodeType.PRESENTATION));
            else if (composite.getType() == GraffNodeType.SLIDE) sn.setType(String.valueOf(SavedNodeType.SLIDE));
        }

        fillProps(node, sn);

        if (node instanceof GraffNodeComposite comp) {
            for (GraffNode child : comp.getChildren()) {
                sn.getChildren().add(map(child));
            }
        }

        return sn;
    }


    private void fillProps(GraffNode node, SavedNode sn) {

        if (node instanceof GraffSlideElement el) {
            sn.getProps().put("x", el.getLocation().x);
            sn.getProps().put("y", el.getLocation().y);
            sn.getProps().put("width", el.getDimension().width);
            sn.getProps().put("height", el.getDimension().height);
            sn.getProps().put("rotation", el.getRotacija());
        }

        if (node instanceof ImageElement img) {
            sn.getProps().put("imagePath", img.getImagePath());
        }

        if (node instanceof TextElement txt) {
            sn.getProps().put("text", txt.getText());
            sn.getProps().put("fontSize", txt.getFontSize());
        }
    }
}
