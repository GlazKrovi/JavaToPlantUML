package pumlFromJava.translators.diagrams;

import jdk.javadoc.doclet.DocletEnvironment;
import pumlFromJava.translators.pumlObjects.pumlClasses.PumlEnum;
import pumlFromJava.translators.pumlObjects.pumlClasses.PumlInterface;
import pumlFromJava.translators.pumlObjects.pumlClasses.PumlClass;
import pumlFromJava.translators.pumlMarker.Marker;

import javax.lang.model.element.Element;

public class PumlBasis implements IPumlDiagram {

    public PumlBasis() {
    }

    /**
     * Translate a specified environment into a very simple Puml scheme
     * including class, enum and interfaces, without any other info
     *
     * @param environment a java environment
     * @return Returns Puml translated String
     */
    public String getScheme(DocletEnvironment environment) {
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
