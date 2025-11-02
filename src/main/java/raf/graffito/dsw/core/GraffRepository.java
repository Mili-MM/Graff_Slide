package raf.graffito.dsw.core;

import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.Workspace;

public interface GraffRepository {
    Workspace getWorkspace();
    void addChild(GraffNodeComposite parent, GraffNode child);
}
