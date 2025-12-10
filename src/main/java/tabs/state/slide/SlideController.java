package tabs.state.slide;

import com.sun.tools.javac.Main;
import jtree.GraffTree;
import jtree.GraffTreeImplementation;
import lombok.Setter;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import tabs.elements.element_implementation.ImageElement;
import lombok.Getter;
import tabs.elements.element_implementation.LogoElement;
import tabs.elements.element_implementation.TextElement;
import tabs.elements.painters.LogoPainter;
import tabs.state.StateManager;
import tabs.state.state_implementation.SelectState;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;

@Getter
public class SlideController implements MouseListener, MouseMotionListener, ActionListener, MouseWheelListener {
    private GraffNode slide; //konkretan slide za koji je vezan (model)
    private SlideView slideView; //view
    private StateManager stateManager;
    @Setter
    private double scaleFactor;

    public SlideController(GraffNode slide, SlideView slideView, StateManager stateManager) {
        this.slide = slide;
        this.slideView = slideView;
        this.stateManager = stateManager;

        slideView.setComponents(
                new ArrayList<>(((GraffNodeComposite) slide).getChildren())
        );

        slideView.addMouseListener(this);
        slideView.addMouseMotionListener(this);
        slideView.addMouseWheelListener(this);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        stateManager.getCurrentState().mousePressed(e, this);
        slideView.setComponents(
                new ArrayList<>(((GraffNodeComposite) slide).getChildren())
        );
        slideView.repaint();
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        stateManager.getCurrentState().mouseReleased(e, this);
        slideView.repaint();
    }
    @Override
    public void mouseDragged(MouseEvent e) {
        stateManager.getCurrentState().mouseDragged(e, this);
        slideView.repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        stateManager.getCurrentState().mouseWheelMoved(e, this);
        slideView.repaint();
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {

    }
    @Override
    public void mouseExited(MouseEvent e) {

    }
    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "img1":
                addImage("sundjerbob.png");
                break;
            case "img2":
                addImage("exit.png");
                break;
            case "img3":
                addImage("patrik.png");
                break;
            case "logo":
                addLogo();
                break;
            case "text":
                addText();
                break;
        }

        slideView.repaint();
    }

    private void addImage(String fileName) {
        try {
            BufferedImage img = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/" + fileName)));
            GraffNode el = new ImageElement("img", slide, new Point(50, 50), new Dimension(100, 100), img);
            ((GraffTreeImplementation)MainFrame.getInstance().getTree()).addChild(slide, el);
            ((GraffNodeComposite) slide).addChild(el);
            slideView.setComponents(
                    new ArrayList<>(((GraffNodeComposite) slide).getChildren())
            );

            slideView.validate();
            slideView.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addLogo() {
        try {
            GraffNode logoElement = new LogoElement("logo", slide, new Point(100, 100), new Dimension(100, 100));
            ((GraffTreeImplementation)MainFrame.getInstance().getTree()).addChild(slide, logoElement);
            ((GraffNodeComposite) slide).addChild(logoElement);
            slideView.setComponents(
                    new ArrayList<>(((GraffNodeComposite) slide).getChildren())
            );

            slideView.validate();
            slideView.repaint();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addText(){
        System.out.println("text");
        // 1. Napravi model elementa
        TextElement element = new TextElement("logo", slide, new Point(100, 100), new Dimension(100, 100), "");

        ((GraffTreeImplementation)MainFrame.getInstance().getTree()).addChild(slide, element);
        ((GraffNodeComposite) slide).addChild(element);
        slideView.setComponents(
                new ArrayList<>(((GraffNodeComposite) slide).getChildren())
        );

        // 2. Prikaz editor polja na slajdu
        slideView.showTextEditor(element);

        slideView.repaint();
    }

}
