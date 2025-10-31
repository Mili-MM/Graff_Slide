package raf.graffito.dsw.gui.swing;

import raf.graffito.dsw.controller.AboutUsAction;
import raf.graffito.dsw.controller.ActionManager;
import raf.graffito.dsw.controller.ExitAction;
import raf.graffito.dsw.controller.NewChildAction;

import javax.swing.*;

public class MyToolBar extends JToolBar {

    private ActionManager ac;

    public MyToolBar() {
        super(HORIZONTAL);
        ac = MainFrame.getInstance().getActionManager();
        setFloatable(false);
        ExitAction exitAction = ac.getExitAction();
        AboutUsAction aboutUsAction = ac.getAboutUsAction();
        NewChildAction newChildAction = ac.getNewChildAction();
        add(exitAction);
        add(aboutUsAction);
        add(newChildAction);
    }
}
