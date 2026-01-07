package uml;

import java.util.*;

public class UMLClass {
    public String name;
    public String parent;
    public List<UMLAttribute> attributes = new ArrayList<>();
    public List<UMLMethod> methods = new ArrayList<>();
}
