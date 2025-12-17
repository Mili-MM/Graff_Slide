package tabs.ucitaneslike.control;


import raf.graffito.dsw.gui.swing.MainFrame;
import tabs.graffpanel.GraffPanelView;
import tabs.ucitaneslike.proxy.ImageInterface;
import tabs.ucitaneslike.proxy.ProxyImage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoadImageControl implements ActionListener {

    ImageInterface proxyImg;

    public LoadImageControl(ImageInterface proxyImg) {
        this.proxyImg = proxyImg;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        GraffPanelView panel = (GraffPanelView) MainFrame.getInstance().getTabbedPane().getSelectedComponent();
        panel.getGraffPanelController().getSlideController().addLocalImageAgain((ProxyImage) proxyImg);
    }

}
