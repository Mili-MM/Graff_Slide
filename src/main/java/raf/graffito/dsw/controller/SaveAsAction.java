package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_components.GraffNodeType;
import serijalizacija.model.SavedProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.List;

public class SaveAsAction extends AbstractGraffAction{
    public SaveAsAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/saveAs.png")); // Postavljanje ikonice
        putValue(NAME, "SaveAs"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "SaveAs"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Sačuvaj projekat kao JSON");

        GraffNode lastSelected = MainFrame.getInstance().getTree().getSelectedNode().getGraffNode();
        if (lastSelected.getType() != GraffNodeType.PROJECT){
            System.out.println("Selektuj Project za cuvanje");
            return;
        }
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
}
