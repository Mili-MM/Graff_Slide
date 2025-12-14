package serijalizacija.model;

import lombok.Getter;
import lombok.Setter;
import repository.graff_components.GraffNode;
import repository.graff_components.GraffNodeComposite;
import repository.graff_implementation.Presentation;
import repository.graff_implementation.Project;
import repository.graff_implementation.Slide;
import tabs.elements.GraffSlideElement;
import tabs.elements.element_implementation.ImageElement;
import tabs.elements.element_implementation.LogoElement;
import tabs.elements.element_implementation.TextElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Getter @Setter
public class SavedNode {
    private String type;        // PROJECT, SLIDE, IMAGE, TEXT...
    private String name;        // title / label
    private Map<String, Object> props = new HashMap<>();
    private List<SavedNode> children = new ArrayList<>();


}
