package pumlFromJava.translators.pumlObjects;

import pumlFromJava.translators.visibilityViewer.VisibilityViewer;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

/**
 * Represents a Puml translator for java enumeration
 */
public class PumlEnum implements IPumlObject {

    public PumlEnum() {
    }

    /**
     * Translate Java enum file into his Puml equivalent
     *
     * @param element a package, class, enum or interface
     * @return Returns string of type "file-type name" for Uml
     * if and only if the specified element is of the correct type, returns an empty string otherwise
     */
    public String getName(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.ENUM) {
            res = "enum " + element.getSimpleName().toString() + " <<enumeration>>";
        }
        return res;
    }

    /**
     * Translate what is inside the Element (like attributes, constants or method)
     *
     * @param element a class, enum or interface
     * @return Puml equivalents of each attribute, constants or method from specified Element
     */
    public String getContent(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.ENUM) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.ENUM_CONSTANT) {
                    res.append(enclosedElement.getSimpleName());
                    res.append(getLineBreaker());
                }
            }
        }
        return res.toString();
    }
}
