package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_implementation.Project;
import serijalizacija.model.Projekat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

public class OpenAction extends AbstractGraffAction{
    public OpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/open.png")); // Postavljanje ikonice
        putValue(NAME, "Open Project"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Open Project"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Projekat loadedProject = new Projekat();
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Otvori JSON projekat");

        int userSelection = fileChooser.showOpenDialog(MainFrame.getInstance());

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            loadedProject = MainFrame.getInstance().getSerijalizator().deSerialize(fileToLoad);

            if (loadedProject != null) {
                System.out.println("Status: Učitan projekat iz: " + fileToLoad.getAbsolutePath());
            } else {
                System.out.println("Status: GREŠKA prilikom učitavanja projekta!");
            }
        }
    }
}
