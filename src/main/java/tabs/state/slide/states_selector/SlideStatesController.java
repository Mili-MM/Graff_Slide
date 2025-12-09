package tabs.state.slide.states_selector;

import lombok.Getter;
import tabs.state.StateManager;
import tabs.state.state_implementation.SelectState;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SlideStatesController implements ActionListener {
    @Getter
    private SlideStatesView view = new SlideStatesView(this);
    private StateManager stateManager;

    public SlideStatesController(StateManager stateManager) {
        this.stateManager = stateManager;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        switch (cmd) {
            case "select":
                stateManager.setSelectState();
                break;
            case "move":
                stateManager.setMoveState();
                break;
            case "resize":
                stateManager.setResizeState();
                break;
            case "delete":
                stateManager.setDeleteState();
                break;
            case "rotate":
                stateManager.setRotateState();
                break;
            case "zoom":

                break;
        }

        view.repaint();
    }
}
