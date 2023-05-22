package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

/**
 * Represents a Puml translator for java enumeration
 */
public class PumlEnum extends PumlObject {

    public PumlEnum() {
    }

    public String getName(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.ENUM) {
            res = "enum " + element.getSimpleName().toString() + " <<enumeration>>";
        }
        return res;
    }

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
