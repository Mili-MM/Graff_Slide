package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;
import repository.graff_implementation.Project;
import repository.graff_node_decorator.GraffNodeDecorator;
import serijalizacija.model.SavedProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

public class SaveAction extends AbstractGraffAction{
    public SaveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/save.png")); // Postavljanje ikonice
        putValue(NAME, "Save"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Save"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffNode lastSelected = MainFrame.getInstance().getTree().getSelectedNode().getGraffNode();
        File lastSelectedPath = MainFrame.getInstance().getSerijalizator().getLastSelectedPath();
        if (lastSelected.getType() != GraffNodeType.PROJECT){
            System.out.println("Selektuj Project za cuvanje");
            return;
        }
        if (!((Project) lastSelected).isModified()) {
            System.out.println("Fajlovi nisu modifikovani");
            return;
        }
        if (lastSelectedPath == null){
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Sačuvaj projekat kao JSON");
            SavedProject izabranSavedProject = new SavedProject(lastSelected);
            fileChooser.setSelectedFile(new File(izabranSavedProject.getName().replaceAll("\\s+", "_") + ".json"));

            int userSelection = fileChooser.showSaveDialog(MainFrame.getInstance());

            if (userSelection == JFileChooser.APPROVE_OPTION) {
                File fileToSave = fileChooser.getSelectedFile();
                if (!fileToSave.getName().toLowerCase().endsWith(".json")) {
                    fileToSave = new File(fileToSave.getAbsolutePath() + ".json");
                }

                MainFrame.getInstance().getSerijalizator().serialize(izabranSavedProject, fileToSave);
                System.out.println("Status: Projekat je uspešno sačuvan u: " + fileToSave.getAbsolutePath());
            }
        }
        else{
            SavedProject izabranSavedProject = new SavedProject(lastSelected);
            MainFrame.getInstance().getSerijalizator().serialize(izabranSavedProject, lastSelectedPath);
            System.out.println("Status: Projekat je uspešno sačuvan u: " + lastSelectedPath.getAbsolutePath());
        }

    }
}
