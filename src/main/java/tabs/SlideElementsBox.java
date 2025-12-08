package tabs;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Objects;

public class SlideElementsBox extends JPanel {
    ArrayList<JButton> buttons = new ArrayList<>();

    public SlideElementsBox() {
        setLayout(new GridLayout(5, 1, 0, 10));
        setPreferredSize(new Dimension(80, 400));

        createButton("/images/sundjerbob.png"); //img1
        createButton("/images/exit.png"); //img2
        createButton( "/images/patrik.png"); //img3
        createButton("/images/slide.png"); //logo
        createButton("/images/project.png"); //text


        setBorder(BorderFactory.createLineBorder(Color.black));
        setOpaque(true);
    }

    private void createButton(String iconPath) {
        ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getResource(iconPath)));
        // Resize na željenu širinu i visinu
        Image scaledImage = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        // Napravi novi ImageIcon sa skaliranom slikom
        icon.setImage(scaledImage);

        JButton btn = new JButton(icon);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setContentAreaFilled(false);
        buttons.add(btn);
        add(btn);
    }

    // Controller pristupa dugmićima kroz View
    public JButton getButton(String type) {
        if (type.equals("img1")) return buttons.get(0);
        if (type.equals("img2")) return buttons.get(1);
        if (type.equals("img3")) return buttons.get(2);
        if (type.equals("logo")) return buttons.get(3);
        if (type.equals("text")) return buttons.get(4);
        return null;
    }
}