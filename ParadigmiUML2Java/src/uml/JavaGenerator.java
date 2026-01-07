package uml;

import java.io.*;

public class JavaGenerator {

    public static void generate(UMLClass c) throws IOException {
        FileWriter w = new FileWriter(c.name + ".java");

        w.write("public class " + c.name);

        if (c.parent != null) {
            w.write(" extends " + c.parent);
        }

        w.write(" {\n\n");

        for (UMLAttribute a : c.attributes) {
            w.write("    private " + a.type + " " + a.name + ";\n");
        }

        w.write("\n");

        for (UMLMethod m : c.methods) {
            w.write("    public " + m.returnType + " " + m.name + "() {\n");
            w.write("    }\n\n");
        }

        w.write("}");
        w.close();
    }
}

