package serijalizacija.model;

import lombok.Getter;
import raf.graffito.dsw.gui.swing.MainFrame;
import repository.graff_components.GraffNode;
import serijalizacija.JacksonSerializer;
@Getter
public class SavedProject {
    private String name;
    private SavedNode root;

    public SavedProject() {
    }

    public SavedProject(GraffNode project) {
        this.name = project.getTitle();
        this.root = ((JacksonSerializer)MainFrame.getInstance().getSerijalizator().getSerializer()).map(project);
    }

}
