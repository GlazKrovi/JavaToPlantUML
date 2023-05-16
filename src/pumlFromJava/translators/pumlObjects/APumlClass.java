package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public abstract class APumlClass extends APumlObject {
    /**
     * Translate a Java class file into his Puml equivalent
     *
     * @param element any java class
     * @return Returns string of type "file-type name" for Uml
     * if and only if the specified element is of the correct type, returns an empty string otherwise
     */
    public String getName(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.CLASS) {
            res = "class " + element.getSimpleName().toString();
        }
        return res;
    }
}
