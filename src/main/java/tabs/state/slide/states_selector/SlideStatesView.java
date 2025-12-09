package tabs.state.slide.states_selector;

import lombok.Getter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.net.http.WebSocket;

public class SlideStatesView extends JPanel {

    private JButton btnSelect;
    private JButton btnMove;
    private JButton btnResize;
    private JButton btnDelete;
    private JButton btnRotate;
    private JButton btnZoom;

    public SlideStatesView(ActionListener listener) {
        // Horizontalni raspored
        setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));

        btnSelect = new JButton("Select");
        btnMove = new JButton("Move");
        btnResize = new JButton("Resize");
        btnDelete = new JButton("Delete");
        btnRotate = new JButton("Rotate");
        btnZoom = new JButton("Zoom");

        // Dodavanje listener-a
        btnSelect.addActionListener(listener);
        btnSelect.setActionCommand("select");
        btnMove.addActionListener(listener);
        btnMove.setActionCommand("move");
        btnResize.addActionListener(listener);
        btnResize.setActionCommand("resize");
        btnDelete.addActionListener(listener);
        btnDelete.setActionCommand("delete");
        btnRotate.addActionListener(listener);
        btnRotate.setActionCommand("rotate");
        btnZoom.addActionListener(listener);
        btnZoom.setActionCommand("zoom");

        // Dodavanje dugmadi u panel
        add(btnSelect);
        add(btnMove);
        add(btnResize);
        add(btnDelete);
        add(btnRotate);
        add(btnZoom);
    }

}
