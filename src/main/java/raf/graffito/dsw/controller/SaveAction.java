package raf.graffito.dsw.controller;

import jtree.model.GraffTreeItem;
import raf.graffito.dsw.core.ApplicationFramework;
import raf.graffito.dsw.core.GraffRepository;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.Workspace;
import serijalizacija.model.Projekat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
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
        List<GraffNode> listaElemenata = ((GraffNodeComposite)MainFrame.getInstance().getTree().getSelectedNode().getGraffNode()).getChildren();

        Projekat izabranProjekat = new Projekat(((GraffNodeComposite)MainFrame.getInstance().getTree().getSelectedNode().getGraffNode()).getTitle());
        for(GraffNode gf: listaElemenata) {
            izabranProjekat.addChild(gf);
        }

        //Provera da li se otvara opet prozor ako je vec jednom sacuvan
        for(Projekat p : MainFrame.getInstance().getSerijalizator().getSacuvani()){
            if(p.getName().equals(MainFrame.getInstance().getTree().getSelectedNode().getGraffNode().getTitle())){
                if(p.getLista().equals(izabranProjekat.getLista())){
                    System.out.println("!!!NE MOZETE SACUVATI: Nista nije izmenjeno, a projekat je vec sacuvan");
                    return;
                }else {
                    File f = new File(p.getPath());
                    MainFrame.getInstance().getSerijalizator().serialize(izabranProjekat, f);
                    System.out.println("CUVA SE, NE OTVARA SE PROZOR JER VEC IMA PUTANJU");
                    izabranProjekat.setPath(p.getPath());
                    MainFrame.getInstance().getSerijalizator().getSacuvani().remove(p);
                    MainFrame.getInstance().getSerijalizator().getSacuvani().add(izabranProjekat);
                    return;
                }
            }
        }


        File startDir = new File("src/main/resources/JSONfiles");
        JFileChooser fileChooser = new JFileChooser(startDir);
        fileChooser.setDialogTitle("Sačuvaj projekat kao JSON");

        if (izabranProjekat != null) {
            fileChooser.setSelectedFile(new File(izabranProjekat.getName().replaceAll("\\s+", "_") + ".json"));
        }

        int userSelection = fileChooser.showSaveDialog(MainFrame.getInstance());

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToSave = fileChooser.getSelectedFile();
            if (!fileToSave.getName().toLowerCase().endsWith(".json")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".json");
            }

            MainFrame.getInstance().getSerijalizator().serialize(izabranProjekat, fileToSave);
            izabranProjekat.setPath(fileToSave.getPath());
            MainFrame.getInstance().getSerijalizator().getSacuvani().add(izabranProjekat);
            System.out.println(izabranProjekat.getLista());
            System.out.println("Status: Projekat je uspešno sačuvan u: " + fileToSave.getAbsolutePath());
        }

    }
}
