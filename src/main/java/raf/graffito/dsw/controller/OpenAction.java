package raf.graffito.dsw.controller;

import com.sun.tools.javac.Main;
import jtree.GraffTreeImplementation;
import jtree.model.GraffTreeItem;
import jtree.view.GraffTreeView;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.GraffRepositoryFactory;
import serijalizacija.model.SavedNode;
import serijalizacija.model.SavedProject;
import tabs.elements.element_implementation.ImageElement;
import tabs.elements.element_implementation.TextElement;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class OpenAction extends AbstractGraffAction{
    public OpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/open.png")); // Postavljanje ikonice
        putValue(NAME, "Open Project"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Open Project"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SavedProject loadedProject = new SavedProject();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Otvori JSON projekat");

        int userSelection = fileChooser.showOpenDialog(MainFrame.getInstance());

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            loadedProject = MainFrame.getInstance().getSerijalizator().deSerialize(fileToLoad);

            if (loadedProject != null) {
                System.out.println("Status: Učitan projekat iz: " + fileToLoad.getAbsolutePath());
                System.out.println(loadedProject.toString());
                GraffTreeItem root = ((GraffTreeImplementation)MainFrame.getInstance().getTree()).getRoot();
                try {
                    build(loadedProject.getRoot(), root);
                    ((GraffTreeImplementation)MainFrame.getInstance().getTree()).updateTree();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            } else {
                System.out.println("Status: GREŠKA prilikom učitavanja projekta!");
            }
        }
    }

    private void build(SavedNode sn, GraffTreeItem parent) throws IOException {
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
            Point location = new Point((Integer) sn.getProps().get("x"), (Integer) sn.getProps().get("y"));
            Dimension dimension = new Dimension((Integer) sn.getProps().get("width"), (Integer) sn.getProps().get("height"));
            String imagePath = sn.getProps().get("imagePath").toString();
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
            node = new ImageElement(parent.getGraffNode(), location, dimension, img);
            ((ImageElement) node).setImagePath(imagePath);
        }
        else if (sn.getType().equals("TEXT")){
            Point location = new Point((Integer) sn.getProps().get("x"), (Integer) sn.getProps().get("y"));
            Dimension dimension = new Dimension((Integer) sn.getProps().get("width"), (Integer) sn.getProps().get("height"));
            String text = sn.getProps().get("text").toString();
            int fontSize = Integer.parseInt(sn.getProps().get("fontSize").toString());
            node = new TextElement(parent.getGraffNode(), location, dimension);
            ((TextElement) node).setText(text);
            ((TextElement) node).setFontSize(fontSize);
        }
        else return;
        GraffTreeItem wrapper = new GraffTreeItem(node);
        parent.add(wrapper);
        ((GraffNodeComposite)parent.getGraffNode()).addChild(node);
        for (SavedNode child : sn.getChildren()){
            build(child, wrapper);
        }


    }
}
