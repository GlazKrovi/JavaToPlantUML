package pumlFromJava.diagrams;

import jdk.javadoc.doclet.DocletEnvironment;
import pumlFromJava.translators.elements.rawObjects.PumlEnum;
import pumlFromJava.translators.elements.rawObjects.PumlInterface;
import pumlFromJava.translators.elements.rawObjects.pumlClasses.PumlClass;
import pumlFromJava.translators.marker.Marker;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PumlCCD implements PumlDiagram {
    /**
     * Design a full Puml Conception Class Diagram scheme
     *
     * @param environment a java environment
     * @return Returns string representing a .puml file's content, for a CCD
     */
    public String translateToScheme(DocletEnvironment environment) {
        // tools and return
        Marker marker = new Marker();
        StringBuilder res = new StringBuilder();
        // puml objects
        PumlClass pumlClass = new PumlClass();
        PumlEnum pumlenum = new PumlEnum();
        PumlInterface pumlinterface = new PumlInterface();

        // start uml
        res.append(marker.umlStart());
        res.append("\n");
        // translate every "big" elements (class, enum, interface)
        for (Element elm : environment.getIncludedElements()) {
            if (elm.getKind() == ElementKind.CLASS) {
                res.append(pumlClass.selfTranslate(elm));
            } else if (elm.getKind() == ElementKind.ENUM) {
                res.append(pumlenum.selfTranslate(elm));
            } else if (elm.getKind() == ElementKind.INTERFACE) {
                res.append(pumlinterface.selfTranslate(elm));
            }
        }
        // get the "defined use" relations (class stored in global variables)
        for (Element elm : environment.getIncludedElements()) {
            if (elm.getKind() == ElementKind.CLASS) {
                res.append(pumlClass.relationsTranslate(elm));
            }
        }
        // end uml
        res.append(marker.umlEnd());
        return res.toString();
    }
}
