package raf.graffito.dsw.controller;

import raf.graffito.dsw.gui.swing.MainFrame;
import serijalizacija.model.Projekat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;

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

        Projekat izabranProjekat = new Projekat("randomIme");

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
            System.out.println("Status: Projekat je uspešno sačuvan u: " + fileToSave.getAbsolutePath());
        }

    }
}
