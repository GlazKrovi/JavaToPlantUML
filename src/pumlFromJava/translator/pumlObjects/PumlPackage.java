package pumlFromJava.translator.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PumlPackage implements PumlObject {
    /**
     * Translate java package into his Puml equivalent
     *
     * @param element a package
     * @return Returns string of type "file-type name" for Uml
     */
    @Override
    public String getName(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.PACKAGE) {
            res.append("package " + element.getSimpleName().toString());
        }
        return res.toString();
    }

    /**
     * Translate what is inside the Element (like attributes, constants or method)
     *
     * @param element a class, enum or interface
     * @return Puml equivalents of each attribute, constants or method from specified Element
     */
    @Override
    public String getContent(Element element) {
        return "";
    }
}
