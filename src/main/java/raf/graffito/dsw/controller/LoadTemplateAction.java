package raf.graffito.dsw.controller;

import jtree.GraffTreeImplementation;
import jtree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.MainFrame;
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

public class LoadTemplateAction extends AbstractGraffAction{
    public LoadTemplateAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/template.png")); // Postavljanje ikonice
        putValue(NAME, "Load Template"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "Load Template"); // Tooltip
    }


    @Override
    public void actionPerformed(ActionEvent e) {


    }
}
