package pumlFromJava.diagrams;

import jdk.javadoc.doclet.DocletEnvironment;
import pumlFromJava.translators.pumlMarker.Marker;
import pumlFromJava.translators.pumlEntities.pumlObjects.PumlEnum;
import pumlFromJava.translators.pumlEntities.pumlObjects.inheritableObject.PumlInterface;
import pumlFromJava.translators.pumlEntities.pumlObjects.inheritableObject.pumlClasses.PumlNonVisibleClass;
import pumlFromJava.translators.pumlEntities.pumlObjects.PumlPackage;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PumlDCA implements IPumlDiagram {
    public PumlDCA() {
    }

    /**
     * Design a full Puml Analyze Class Diagram scheme
     *
     * @param environment a java environment
     * @return Returns string representing a .puml file's content, for an ACD
     */
    public String getScheme(DocletEnvironment environment) {
        Marker marker = new Marker();
        StringBuilder res = new StringBuilder();
        // puml objects
        PumlNonVisibleClass pumlNonVisibleClass = new PumlNonVisibleClass();
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
                res.append(pumlNonVisibleClass.getName(elm));
                res.append(pumlNonVisibleClass.getInheritance(elm));
                res.append(pumlNonVisibleClass.open());
                res.append(pumlNonVisibleClass.getLineBreaker());
                res.append(pumlNonVisibleClass.getFields(elm));
                res.append(pumlNonVisibleClass.close());
                res.append(pumlNonVisibleClass.getLineBreaker());
            } else if (elm.getKind() == ElementKind.ENUM) {
                res.append(pumlenum.getTranslation(elm));
            } else if (elm.getKind() == ElementKind.INTERFACE) {
                res.append(pumlinterface.getName(elm));
                res.append(pumlinterface.getInheritance(elm));
                res.append(pumlinterface.getLineBreaker());
            }
        }
        // get the "defined use" relations (class stored in global variables)
        for (Element elm : environment.getIncludedElements()) {
            if (elm.getKind() == ElementKind.CLASS) {
                res.append(pumlNonVisibleClass.getUses(elm));
            }
        }
        // close package if any is opened
        if (packageOpened) pumlpackage.close();
        // end uml
        res.append(marker.umlEnd());
        return res.toString();
    }


}