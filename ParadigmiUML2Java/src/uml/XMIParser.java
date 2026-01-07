package uml;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import java.util.*;

public class XMIParser {

    public static List<UMLClass> parse(String xmiFile) throws Exception {
        List<UMLClass> classes = new ArrayList<>();

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        factory.setNamespaceAware(true);

        DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document doc = builder.parse(new File(xmiFile));
        if (doc==null) {
        	System.out.println("Taa mal");
        }

        NodeList classNodes = doc.getElementsByTagNameNS("*", "Class");

     // Si sigue dando 0, intenta buscar los elementos empaquetados que son tipo Clase
     if (classNodes.getLength() == 0) {
         classNodes = doc.getElementsByTagNameNS("*", "packagedElement");
     }
        for (int i = 0; i < classNodes.getLength(); i++) {
            Element classElement = (Element) classNodes.item(i);
            
            UMLClass umlClass = new UMLClass();
            umlClass.name = classElement.getAttribute("name");

            // Atributos
            NodeList attributes = classElement.getElementsByTagNameNS("*", "ownedAttribute");
            for (int j = 0; j < attributes.getLength(); j++) {
                Element attr = (Element) attributes.item(j);
                UMLAttribute a = new UMLAttribute();
                a.name = attr.getAttribute("name");
                a.type = attr.getAttribute("type");
                if (a.type.isEmpty()) a.type = "Object";
                umlClass.attributes.add(a);
            }
           

            // MÃ©todos
            NodeList operations = classElement.getElementsByTagNameNS("*", "ownedOperation");
            for (int j = 0; j < operations.getLength(); j++) {
                Element op = (Element) operations.item(j);
                UMLMethod m = new UMLMethod();
                m.name = op.getAttribute("name");
                umlClass.methods.add(m);
            }

            // Herencia
            NodeList generals = classElement.getElementsByTagNameNS("*", "generalization");
            if (generals.getLength() > 0) {
                Element gen = (Element) generals.item(0);
                umlClass.parent = gen.getAttribute("general");
            }

            classes.add(umlClass);
        }
        return classes;
    }
}