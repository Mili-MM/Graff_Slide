package tabs.state.slide;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;
import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.ImageElement;
import tabs.elements.element_implementation.LogoElement;
import tabs.elements.painters.ImagePainter;
import tabs.elements.painters.LogoPainter;
import tabs.elements.painters.Painter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SlideView extends JPanel {
    private static final Dimension size = new Dimension(650, 450);
    @Setter
    private ArrayList<GraffNode> components = new ArrayList<>();

    public SlideView() {

        // Obavezno postavljamo fiksne dimenzije
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Crtanje svih elemenata
        for (GraffNode child : components) {
            GraffSlideElement element = (GraffSlideElement) child;

            Painter painter = null;

            if (element instanceof ImageElement) {
                painter = new ImagePainter((ImageElement) element);
            } else if (element instanceof LogoElement) {
                painter = new LogoPainter((LogoElement) element);
            }
            // Dodaj ostale tipove elemenata po potrebi

            if (painter != null) {
                painter.paint(g2d);
            }
        }
    }
}
