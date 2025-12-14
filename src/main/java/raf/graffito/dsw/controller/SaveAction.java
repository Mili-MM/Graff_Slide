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
        if (lastSelected.getType() != GraffNodeType.PROJECT){
            System.out.println("Selektuj Project za cuvanje");
            return;
        }
        SavedProject izabranSavedProject = new SavedProject(lastSelected);


        //Provera da li se otvara opet prozor ako je vec jednom sacuvan
//        for(SavedProject p : MainFrame.getInstance().getSerijalizator().getSacuvani()){
//            if(p.getName().equals(MainFrame.getInstance().getTree().getSelectedNode().getGraffNode().getTitle())){
//                if(false){
//                    System.out.println("!!!NE MOZETE SACUVATI: Nista nije izmenjeno, a projekat je vec sacuvan");
//                    return;
//                }else {
//                    File f = new File(p.getPath());
//                    MainFrame.getInstance().getSerijalizator().serialize(izabranSavedProject, f);
//                    System.out.println("CUVA SE, NE OTVARA SE PROZOR JER VEC IMA PUTANJU");
//                    izabranSavedProject.setPath(p.getPath());
//                    MainFrame.getInstance().getSerijalizator().getSacuvani().remove(p);
//                    MainFrame.getInstance().getSerijalizator().getSacuvani().add(izabranSavedProject);
//                    return;
//                }
//            }
//        }
//
//
//        File startDir = new File("src/main/resources/JSONfiles");
//        JFileChooser fileChooser = new JFileChooser(startDir);
//        fileChooser.setDialogTitle("Sačuvaj projekat kao JSON");
//
//        if (izabranSavedProject != null) {
//            fileChooser.setSelectedFile(new File(izabranSavedProject.getName().replaceAll("\\s+", "_") + ".json"));
//        }
//
//        int userSelection = fileChooser.showSaveDialog(MainFrame.getInstance());
//
//        if (userSelection == JFileChooser.APPROVE_OPTION) {
//            File fileToSave = fileChooser.getSelectedFile();
//            if (!fileToSave.getName().toLowerCase().endsWith(".json")) {
//                fileToSave = new File(fileToSave.getAbsolutePath() + ".json");
//            }
//
//            MainFrame.getInstance().getSerijalizator().serialize(izabranSavedProject, fileToSave);
//            izabranSavedProject.setPath(fileToSave.getPath());
//            MainFrame.getInstance().getSerijalizator().getSacuvani().add(izabranSavedProject);
//            System.out.println(izabranSavedProject.getLista());
//            System.out.println("Status: Projekat je uspešno sačuvan u: " + fileToSave.getAbsolutePath());
//        }

    }
}
