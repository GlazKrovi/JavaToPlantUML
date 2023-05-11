package pumlFromJava.diagrams;

import jdk.javadoc.doclet.DocletEnvironment;

public interface IPumlDiagram {
    /**
     * Design a full Puml Analyze Class Diagram scheme
     *
     * @param environment a java environment
     * @return Returns string representing a .puml file's content, for a ACD
     */
    String getACD(DocletEnvironment environment);

    /**
     * Design a full Puml Conception Class Diagram scheme
     *
     * @param environment a java environment
     * @return Returns string representing a .puml file's content, for a CCD
     */
    String getCCD(DocletEnvironment environment);
}
