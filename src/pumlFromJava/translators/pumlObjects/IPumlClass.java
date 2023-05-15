package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public interface IPumlClass extends IPumlObject {
    /**
     * Translate a Java class file into his Puml equivalent
     *
     * @param element any java class
     * @return Returns string of type "file-type name" for Uml
     * if and only if the specified element is of the correct type, returns an empty string otherwise
     */
    default String getName(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.CLASS) {
            res = "class " + element.getSimpleName().toString();
        }
        return res;
    }

    /**
     * Translate what is inside the Element (like attributes, constants or method)
     *
     * @param element an enclosed element from class, enum or interface
     * @return Puml equivalents of each attribute, constants or method from specified Element
     */
    String getContent(Element element);

}
