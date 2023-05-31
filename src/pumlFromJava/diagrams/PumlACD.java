package pumlFromJava.diagrams;

import jdk.javadoc.doclet.DocletEnvironment;
import pumlFromJava.translators.elements.rawObjects.PumlEnum;
import pumlFromJava.translators.elements.rawObjects.PumlInterface;
import pumlFromJava.translators.elements.rawObjects.pumlClasses.PumlLiteClass;
import pumlFromJava.translators.marker.Marker;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PumlACD implements PumlDiagram {
    /**
     * Design a full Puml Analyze Class Diagram scheme
     *
     * @param environment a java environment
     * @return Returns string representing a .puml file's content, for an ACD
     */
    public String translateToScheme(DocletEnvironment environment) {
        // tools and return
        Marker marker = new Marker();
        StringBuilder res = new StringBuilder();
        // puml objects
        PumlLiteClass pumlLiteClass = new PumlLiteClass();
        PumlEnum pumlenum = new PumlEnum();
        PumlInterface pumlinterface = new PumlInterface();

        // start uml
        res.append(marker.umlStart());
        res.append("\n");
        // translate every "big" elements (class, enum, interface)
        for (Element curentElement : environment.getIncludedElements()) {
            if (curentElement.getKind() == ElementKind.CLASS) {
                res.append(pumlLiteClass.selfTranslate(curentElement));
            } else if (curentElement.getKind() == ElementKind.ENUM) {
                res.append(pumlenum.selfTranslate(curentElement));
            } else if (curentElement.getKind() == ElementKind.INTERFACE) {
                res.append("interface ");
                res.append(pumlinterface.getFullName(curentElement));
                res.append(pumlinterface.inheritanceTranslate(curentElement));
                res.append("\n");
            }
        }
        // get the "defined use" relations (class stored in global variables)
        for (Element elm : environment.getIncludedElements()) {
            if (elm.getKind() == ElementKind.CLASS) {
                res.append(pumlLiteClass.relationsTranslate(elm));
            }
        }
        // end uml
        res.append(marker.umlEnd());
        return res.toString();
    }
}