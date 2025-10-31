package raf.graffito.dsw.controller;

import jtree.model.GraffTreeItem;
import raf.graffito.dsw.gui.swing.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewChildAction extends AbstractGraffAction{
    public NewChildAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK)); // Ovo je prečica za izlaz
        putValue(SMALL_ICON, super.loadIcon("/images/add-new-child.png")); // Postavljanje ikonice
        putValue(NAME, "New Child"); // Ime akcije
        putValue(SHORT_DESCRIPTION, "New Child"); // Tooltip
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffTreeItem selected = (GraffTreeItem) MainFrame.getInstance().getTree().getSelectedNode();
        MainFrame.getInstance().getTree().addChild(selected);
    }
}
