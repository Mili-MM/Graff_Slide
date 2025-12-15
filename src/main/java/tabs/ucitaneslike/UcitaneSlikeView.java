package tabs.ucitaneslike;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class UcitaneSlikeView extends JPanel {

    private List<ImageIcon> thumbnails; // lista umanjenih slika
    private int thumbnailWidth = 50;   // širina umanjene slike
    private int thumbnailHeight = 50;  // visina umanjene slike

    public UcitaneSlikeView() {
        this.thumbnails = new ArrayList<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS)); // vertikalno dodavanje
    }

    // Dodaj novu sliku u listu i repaint
    public void addImage(BufferedImage image) {
        Image scaled = image.getScaledInstance(thumbnailWidth, thumbnailHeight, Image.SCALE_SMOOTH);
        thumbnails.add(new ImageIcon(scaled));
        JLabel label = new JLabel(thumbnails.get(thumbnails.size() - 1));
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));
        add(label);
        revalidate();
        repaint();
    }
}

