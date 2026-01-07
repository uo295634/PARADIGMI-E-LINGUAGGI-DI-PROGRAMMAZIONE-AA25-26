package uml;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        try {
            List<UMLClass> classes = XMIParser.parse("modelo.xmi");

            for (UMLClass c : classes) {
                JavaGenerator.generate(c);
            }

            System.out.println("Java code generated");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
