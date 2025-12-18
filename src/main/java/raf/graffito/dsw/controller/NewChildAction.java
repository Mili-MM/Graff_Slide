package raf.graffito.dsw.controller;

import jtree.GraffTreeImplementation;
import jtree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNodeType;
import serijalizacija.JacksonSerializer;
import serijalizacija.model.SavedProject;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

public class NewChildAction extends AbstractGraffAction{
    public NewChildAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/add-new-child.png")); // Postavljanje ikonice
        putValue(NAME, "New Child"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "New Child"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeItem selected = MainFrame.getInstance().getTree().getSelectedNode();
        if (selected.getGraffNode().getType() == GraffNodeType.WORKSPACE) {

            URL url = getClass().getClassLoader().getResource("templates");
            if (url == null) {
                JOptionPane.showMessageDialog(
                        MainFrame.getInstance(),
                        "Templates folder nije pronađen",
                        "Greška",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            File templatesDir;
            try {
                templatesDir = new File(url.toURI());
            } catch (URISyntaxException err) {
                err.printStackTrace();
                return;
            }

            JFileChooser chooser = new JFileChooser(templatesDir);
            chooser.setDialogTitle("Izaberite šablon");
            chooser.setFileFilter(
                    new FileNameExtensionFilter("JSON template", "json")
            );

            int result = chooser.showOpenDialog(MainFrame.getInstance());
            if (result == JFileChooser.APPROVE_OPTION) {
                File fileToLoad = chooser.getSelectedFile();
                SavedProject loadedProject = MainFrame.getInstance().getSerijalizator().deSerialize(fileToLoad);

                if (loadedProject != null) {
                    System.out.println("Status: Učitan projekat iz: " + fileToLoad.getAbsolutePath());
                    System.out.println(loadedProject.toString());
                    try {
                        JacksonSerializer serializer = (JacksonSerializer) MainFrame.getInstance().getSerijalizator().getSerializer();
                        serializer.build(loadedProject.getRoot(), selected);
                        ((GraffTreeImplementation) MainFrame.getInstance().getTree()).updateTree();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }


                } else {
                    System.out.println("Status: GREŠKA prilikom učitavanja projekta!");
                }
            }
            else{
                MainFrame.getInstance().getTree().addChild(selected);
            }
        }
        else{
            MainFrame.getInstance().getTree().addChild(selected);
        }
    }
}
