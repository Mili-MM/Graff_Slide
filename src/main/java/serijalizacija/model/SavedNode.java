package serijalizacija.model;

import lombok.Getter;
import lombok.Setter;

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
