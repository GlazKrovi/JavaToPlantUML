package pumlFromJava.diagrams;

import jdk.javadoc.doclet.DocletEnvironment;
import pumlFromJava.translators.pumlMarker.Marker;
import pumlFromJava.translators.pumlObjects.PumlClass;
import pumlFromJava.translators.pumlObjects.PumlEnum;
import pumlFromJava.translators.pumlObjects.PumlInterface;
import pumlFromJava.translators.pumlObjects.PumlPackage;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PumlDCC implements IPumlDiagram {
    public PumlDCC() {
    }

    /**
     * Design a full Puml Conception Class Diagram scheme
     *
     * @param environment a java environment
     * @return Returns string representing a .puml file's content, for a CCD
     */
    public String getScheme(DocletEnvironment environment) {
        Marker marker = new Marker();
        StringBuilder res = new StringBuilder();
        // puml objects
        PumlClass pumlClass = new PumlClass();
        PumlEnum pumlenum = new PumlEnum();
        PumlInterface pumlinterface = new PumlInterface();
        PumlPackage pumlpackage = new PumlPackage();
        // package is open?
        boolean packageOpened = false;
        // start uml
        res.append(marker.umlStart());
        res.append(marker.option_strictUml());
        res.append("\n");
        // translate every "big" elements (class, enum, interface)
        for (Element elm : environment.getIncludedElements()) {
            if (elm.getKind() == ElementKind.PACKAGE) {
                res.append(pumlpackage.getName(elm));
                res.append(pumlpackage.open());
                res.append(pumlpackage.getLineBreaker());
                packageOpened = true;
            } else if (elm.getKind() == ElementKind.CLASS) {
                res.append(pumlClass.getTranslation(elm));
            } else if (elm.getKind() == ElementKind.ENUM) {
                res.append(pumlenum.getTranslation(elm));
            } else if (elm.getKind() == ElementKind.INTERFACE) {
                res.append(pumlinterface.getTranslation(elm));
            }
        }
        // get the "defined use" relations (class stored in global variables)
        for (Element elm : environment.getIncludedElements()) {
            if (elm.getKind() == ElementKind.CLASS) {
                res.append(pumlClass.getUses(elm));
            }
        }
        // close package if any is opened
        if (packageOpened) pumlpackage.close();
        // end uml
        res.append(marker.umlEnd());
        return res.toString();
    }
}
