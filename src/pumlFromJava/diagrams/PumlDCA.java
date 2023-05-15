package pumlFromJava.diagrams;

import jdk.javadoc.doclet.DocletEnvironment;
import pumlFromJava.translators.pumlMarker.Marker;
import pumlFromJava.translators.pumlObjects.*;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PumlDCA implements IPumlDiagram {
    /**
     * Design a full Puml Analyze Class Diagram scheme
     *
     * @param environment a java environment
     * @return Returns string representing a .puml file's content, for a ACD
     */
    @Override
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
                res.append(pumlNonVisibleClass.open());
                res.append(pumlpackage.getLineBreaker());
                res.append(pumlNonVisibleClass.getFields(elm));
                res.append(pumlNonVisibleClass.close());
                res.append(pumlpackage.getLineBreaker());
            } else if (elm.getKind() == ElementKind.ENUM) {
                res.append(pumlenum.getTranslation(elm));
            } else if (elm.getKind() == ElementKind.INTERFACE) {
                res.append(pumlinterface.getName(elm));
                res.append(pumlpackage.getLineBreaker());
            }
        }
        // close package if any is opened
        if (packageOpened) pumlpackage.close();
        // end uml
        res.append(marker.umlEnd());
        return res.toString();
    }


}