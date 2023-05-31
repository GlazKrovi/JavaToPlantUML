package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

/**
 * Represents a Puml translator for java enumeration
 */
public class PumlEnum extends PumlObject {

    @Override
    public String selfTranslate(Element element) {
        return "enum " +
                getFullName(element) +
                open() + // {
                "\n" +
                contentTranslate(element) + // -field, +method
                close() + // }
                "\n";
    }

    @Override
    public String contentTranslate(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.ENUM) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.ENUM_CONSTANT) {
                    res.append(enclosedElement.getSimpleName());
                    res.append("\n");
                }
            }
        }
        return res.toString();
    }
}
