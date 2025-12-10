package tabs.state.slide;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;
import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.ImageElement;
import tabs.elements.element_implementation.LogoElement;
import tabs.elements.element_implementation.TextElement;
import tabs.elements.painters.ImagePainter;
import tabs.elements.painters.LogoPainter;
import tabs.elements.painters.Painter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class SlideView extends JPanel {
    private static final Dimension size = new Dimension(650, 450);
    @Getter
    private AffineTransform currentTransform = new AffineTransform();
    @Setter
    private ArrayList<GraffNode> components = new ArrayList<>();
    private JTextField textEditor = null;

    public SlideView() {
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setTransform(currentTransform);

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

    public void setScaleFactor(double scaleFactor) {
        currentTransform = new AffineTransform();
        currentTransform.scale(scaleFactor, scaleFactor);
    }


    public void showTextEditor(TextElement element) {
        // Ako već postoji editor, ukloni ga pre kreiranja novog
        if (textEditor != null) {
            remove(textEditor);
            textEditor = null;
        }

        textEditor = new JTextField();
        textEditor.setText(element.getText());
        textEditor.setBounds(
                element.getLocation().x,
                element.getLocation().y,
                element.getDimension().width,
                element.getDimension().height
        );

        add(textEditor);
        textEditor.requestFocus();

        // Kada korisnik pritisne Enter
        textEditor.addActionListener(e -> saveTextAndRemoveEditor(element));

        // Kada polje izgubi fokus
        textEditor.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                saveTextAndRemoveEditor(element);
            }
        });

        revalidate();
        repaint();
    }

    // --- Metod za čuvanje i uklanjanje editor-a ---
    private void saveTextAndRemoveEditor(TextElement element) {
        if (textEditor != null) {  // <- sigurna provera
            element.setText(textEditor.getText());
            remove(textEditor);
            textEditor = null;
            revalidate();
            repaint();
        }
    }


}
