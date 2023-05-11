package pumlFromJava.diagrams;

import pumlFromJava.translator.marker.Markor;
import pumlFromJava.translator.pumlObjects.*;
import jdk.javadoc.doclet.DocletEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PumlDiagram {
    /**
     * Translate a specified environment into a very simple Puml scheme
     * including class, enum and interfaces, without any other info
     * @param environment a java environment
     * @return Returns Puml translated String
     */
    public String design_basis(DocletEnvironment environment) {
        Markor markor = new Markor();
        StringBuilder res = new StringBuilder();
        // puml objects
        PumlClass pumlclass = new PumlClass();
        PumlEnum pumlenum = new PumlEnum();
        PumlInterface pumlinterface = new PumlInterface();
        // start uml scheme
        res.append(markor.umlStart());
        // translate every "big" elements (class, enum, interface)
        for (Element elm : environment.getIncludedElements()) {
            res.append(pumlclass.getName(elm));
            res.append(pumlenum.getName(elm));
            res.append(pumlinterface.getName(elm));
            res.append("\n");
        }
        // end uml scheme
        res.append(markor.umlEnd());
        return res.toString();
    }

    /**
     * Design a full Puml DCA scheme
     * @param environment a java environment
     * @return Returns string representing a .puml file's content, for a DCA
     */
    public String design_simpleDCA(DocletEnvironment environment) {
        Markor markor = new Markor();
        StringBuilder res = new StringBuilder();
        // puml objects
        PumlNonVisibleClass pumlNonVisibleClass = new PumlNonVisibleClass();
        PumlEnum pumlenum = new PumlEnum();
        PumlInterface pumlinterface = new PumlInterface();
        PumlPackage pumlpackage = new PumlPackage();
        // package is open?
        boolean packageOpened = false;
        // start uml
        res.append(markor.umlStart());
        res.append(markor.option_strictUml());
        res.append("\n");
        // translate every "big" elements (class, enum, interface)
        for (Element elm : environment.getIncludedElements()) {
            if (elm.getKind() == ElementKind.PACKAGE){
                    res.append(pumlpackage.getName(elm));
                    res.append(pumlpackage.open());
                    res.append(pumlpackage.getLineBreaker());
                packageOpened = true;
            }
            else if (elm.getKind() == ElementKind.CLASS){
                res.append(pumlNonVisibleClass.getName(elm));
                res.append(pumlNonVisibleClass.open());
                res.append(pumlpackage.getLineBreaker());
                res.append(pumlNonVisibleClass.getFields(elm));
                res.append(pumlNonVisibleClass.close());
                res.append(pumlpackage.getLineBreaker());
            }
            else if (elm.getKind() == ElementKind.ENUM){
                res.append(pumlenum.getTranslation(elm));
            }
            else if (elm.getKind() == ElementKind.INTERFACE){
                res.append(pumlinterface.getName(elm));
                res.append(pumlpackage.getLineBreaker());
            }
        }
        // close package if any is opened
        if (packageOpened) pumlpackage.close();
        // end uml
        res.append(markor.umlEnd());
        return res.toString();
    }
}