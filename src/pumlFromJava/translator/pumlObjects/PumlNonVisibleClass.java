package pumlFromJava.translator.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PumlNonVisibleClass implements IPumlClass {
    /**
     * Translate what is inside the Element (like attributes, constants or method),
     * but without his visibility
     *
     * @param element an enclosed element from class, enum or interface
     * @return Puml equivalents of each attribute, constants or method from specified Element
     */
    public String getContent(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.CLASS) {
            // search inside
            res.append(getFields(element));
            res.append(getMethods(element));
        }
        return res.toString();
    }

    /**
     * Translate all class' field in his Puml equivalent,
     * without his visibility
     *
     * @param element an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    public String getFields(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.CLASS) {
            // search inside
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.FIELD
                        && is_primitive(enclosedElement.asType())) {
                    res.append(translate_field(enclosedElement));
                    res.append(getLineBreaker());
                }
            }
        }
        return res.toString();
    }

    /**
     * Translate all class' method in his Puml equivalent,
     * without his visibility
     *
     * @param element an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    public String getMethods(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.CLASS) {
            // search inside
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.METHOD) {
                    res.append(translate_method(enclosedElement));
                    res.append(getLineBreaker());
                }
            }
        }
        return res.toString();
    }

    /**
     * Translate a class' field in his Puml equivalent,
     * without his visibility
     *
     * @param enclosedElement an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    private String translate_field(Element enclosedElement) {
        return enclosedElement.getSimpleName().toString();
    }

    /**
     * Translate a class' method in his Puml equivalent,
     * without his visibility
     *
     * @param enclosedElement an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    private String translate_method(Element enclosedElement) {
        return enclosedElement.getSimpleName() + "()";
    }
}
