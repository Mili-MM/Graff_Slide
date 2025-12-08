package tabs;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

@Getter
@Setter
//ova klasa bi vljd trebala da bude PresentationView
public class GraffPanel extends JPanel {
    private GraffNode node; //ovo je presentation za koji je vezan
    private Color color;
    private Label label1;
    private Label label2;
    private Label label3;
    private SlideView slideView;
    private JPanel centerPanel;

    public GraffPanel(GraffNode node) {
        super();
        this.node = node;

        setLayout(new BorderLayout());

        label1 = new Label("Presentation: " + node.getTitle()+ " ");
        label2 = new Label("Project: " + node.getParent().getTitle());
        label3 = new Label("Author: " + node.getAuthor());
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        northPanel.add(label1);
        northPanel.add(label2);
        northPanel.add(label3);

        add(northPanel, BorderLayout.NORTH);

        SlideElementsBox slideElementsBox = new SlideElementsBox();
        add(slideElementsBox, BorderLayout.EAST);

    }

    public void setSlideView(SlideView slideView) {
        // Ukloni prethodni panel
        if (centerPanel != null) {
            remove(centerPanel);
        }

        this.slideView = slideView;

        // Novi panel sa fiksnom veličinom i centriranjem
        centerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        centerPanel.add(slideView);

        add(centerPanel, BorderLayout.CENTER);

        revalidate(); // da layout ažurira
        repaint();
    }

    @Override
    public String toString() {
        return node.toString();
    }

    public void update(GraffNode node) {
        this.node = node;
        label1.setText("Presentation: " + node.getTitle() + " ");
        label2.setText("Project: " + node.getParent().getTitle());
        label3.setText("Author: " + node.getAuthor());
        revalidate();
        repaint();
    }

    public void update(String project){
        label2.setText("Project: " + project);
        revalidate();
        repaint();
    }
}
