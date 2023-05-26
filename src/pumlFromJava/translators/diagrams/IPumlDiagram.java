package pumlFromJava.translators.diagrams;

import jdk.javadoc.doclet.DocletEnvironment;

public interface IPumlDiagram {
    /**
     * Design a full Puml Class Diagram scheme
     *
     * @param environment a java environment
     * @return Returns string representing a .puml file's content, for a ACD
     */
    String getScheme(DocletEnvironment environment);
}
