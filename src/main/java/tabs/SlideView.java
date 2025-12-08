package tabs;

import javax.swing.*;
import java.awt.*;

public class SlideView extends JPanel {
    private String text;
    private static final Dimension size = new Dimension(650, 450);

    public SlideView(String text) {
        this.text = text;

        // Obavezno postavljamo fiksne dimenzije
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
        setBackground(Color.RED);
        setBorder(BorderFactory.createLineBorder(Color.black));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(text, 40, 40);
    }
}
