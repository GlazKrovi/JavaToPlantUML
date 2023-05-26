package pumlFromJava.translators.diagrams;

import jdk.javadoc.doclet.DocletEnvironment;

public interface PumlDiagram {
    /**
     * Design a full Puml Class Diagram scheme
     *
     * @param environment a java environment
     * @return Returns string representing a .puml file's content, for a ACD
     */
    String translateToScheme(DocletEnvironment environment);
}
