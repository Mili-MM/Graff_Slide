package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeType;
import serijalizacija.model.SavedProject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class SaveTemplateAction extends AbstractGraffAction{
    public SaveTemplateAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/save-template.png")); // Postavljanje ikonice
        putValue(NAME, "Save Template"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Save Template"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffNode lastSelected = MainFrame.getInstance().getTree().getSelectedNode().getGraffNode();
        if (lastSelected.getType() != GraffNodeType.PROJECT){
            System.out.println("Moras da selektujes project");
            return;
        }
        SavedProject izabranSavedProject = new SavedProject(lastSelected);
        File templatesDir = new File("dsw-projekat-2025-tim_mmilosavljevic_veljkomladenovic/src/main/resources/templates");

        if (!templatesDir.exists()) {
            templatesDir.mkdirs();
        }

        File file = new File(
                templatesDir,
                izabranSavedProject.getName().replaceAll("\\s+", "_") + ".json"
        );

        MainFrame.getInstance()
                .getSerijalizator()
                .serialize(izabranSavedProject, file);

        System.out.println("Sačuvano u: " + file.getAbsolutePath());



    }
}
