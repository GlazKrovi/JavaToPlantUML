package pumlFromJava.translators.elements.objects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

/**
 * A puml equivalent for any type of java enumeration
 */
public class PumlEnum extends PumlObject {

    @Override
    public String selfTranslate(Element element) {
        // security
        if (element == null || element.getKind() != ElementKind.ENUM) {
            throw new IllegalArgumentException();
        }

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
        // security
        if (element == null || element.getKind() != ElementKind.ENUM) {
            throw new IllegalArgumentException();
        }

        StringBuilder res = new StringBuilder();
        for (Element enclosedElement : element.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.ENUM_CONSTANT) {
                res.append(enclosedElement.getSimpleName());
                res.append("\n");
            }
        }
        return res.toString();
    }
}
