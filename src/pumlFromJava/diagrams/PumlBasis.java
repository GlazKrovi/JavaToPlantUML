package pumlFromJava.diagrams;

import jdk.javadoc.doclet.DocletEnvironment;
import pumlFromJava.translators.marker.Marker;
import pumlFromJava.translators.elements.rawObjects.PumlEnum;
import pumlFromJava.translators.elements.rawObjects.PumlInterface;
import pumlFromJava.translators.elements.rawObjects.pumlClasses.PumlClass;

import javax.lang.model.element.Element;

public class PumlBasis implements PumlDiagram {


    /**
     * Translate a specified environment into a very simple Puml scheme
     * including class, enum and interfaces, without any other info
     *
     * @param environment a java environment
     * @return Returns Puml translated String
     */
    public String translateToScheme(DocletEnvironment environment) {
        Marker marker = new Marker();
        StringBuilder res = new StringBuilder();
        // puml objects
        PumlClass pumlclass = new PumlClass();
        PumlEnum pumlenum = new PumlEnum();
        PumlInterface pumlinterface = new PumlInterface();
        // start uml scheme
        res.append(marker.umlStart());
        // translate every "big" elements (class, enum, interface)
        for (Element elm : environment.getIncludedElements()) {
            res.append(pumlclass.getFullName(elm));
            res.append(pumlenum.getFullName(elm));
            res.append(pumlinterface.getFullName(elm));
            res.append("\n");
        }
        // end uml scheme
        res.append(marker.umlEnd());
        return res.toString();
    }
}
