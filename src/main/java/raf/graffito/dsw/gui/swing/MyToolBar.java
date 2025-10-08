package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.controller.ExitAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);

        ExitAction exitAction = new ExitAction();
        add(exitAction);
    }
}
