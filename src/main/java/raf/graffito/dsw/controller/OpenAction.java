package raf.graffito.dsw.controller;

import com.sun.tools.javac.Main;
import jtree.GraffTreeImplementation;
import jtree.model.GraffTreeItem;
import jtree.view.GraffTreeView;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.GraffRepositoryFactory;
import serijalizacija.JacksonSerializer;
import serijalizacija.SerializationImplementation;
import serijalizacija.Serializer;
import serijalizacija.model.SavedNode;
import serijalizacija.model.SavedProject;
import tabs.elements.element_implementation.ImageElement;
import tabs.elements.element_implementation.LogoElement;
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
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Otvori JSON projekat");

        int userSelection = fileChooser.showOpenDialog(MainFrame.getInstance());

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            SavedProject loadedProject = MainFrame.getInstance().getSerijalizator().deSerialize(fileToLoad);

            if (loadedProject != null) {
                System.out.println("Status: Učitan projekat iz: " + fileToLoad.getAbsolutePath());
                System.out.println(loadedProject.toString());
                GraffTreeItem root = ((GraffTreeImplementation)MainFrame.getInstance().getTree()).getRoot();
                try {
                    JacksonSerializer serializer = (JacksonSerializer) MainFrame.getInstance().getSerijalizator().getSerializer();
                    serializer.build(loadedProject.getRoot(), root);
                    ((GraffTreeImplementation)MainFrame.getInstance().getTree()).updateTree();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }


            } else {
                System.out.println("Status: GREŠKA prilikom učitavanja projekta!");
            }
        }
    }

}
