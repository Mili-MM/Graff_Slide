package repository.graff_implementation;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;

public class Workspace extends GraffNodeComposite {
    public Workspace(String title, String author, GraffNode parent) {
        super(title, author, parent);
    }
    public Workspace(String title, GraffNode parent) {
        super(title, "", parent);
    }
}
