package uml;

import java.io.*;



public class JavaGenerator {

    private static final String OUTPUT_DIR = "src/generated/";

    public static void generate(UMLClass c) throws IOException {

        File dir = new File(OUTPUT_DIR);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        FileWriter w = new FileWriter(OUTPUT_DIR + c.name + ".java");

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