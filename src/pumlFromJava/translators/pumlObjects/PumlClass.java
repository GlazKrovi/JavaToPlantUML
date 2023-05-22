package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

/**
 * Represents a Puml translator for java class
 */
public class PumlClass extends PumlClasses {
    private final VisibilityViewer visibilityViewer = new VisibilityViewer();

    public PumlClass() {
    }

    public String getContent(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.CLASS) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.METHOD) {
                    res.append(translate_methods(enclosedElement));
                    res.append(getLineBreaker());
                } else if (enclosedElement.getKind() == ElementKind.FIELD
                        && isPrimitiveType(enclosedElement.asType())) {
                    res.append(translate_fields(enclosedElement));
                    res.append(getLineBreaker());
                }
            }
        }
        return res.toString();
    }

    protected String translate_methods(Element enclosedElement) {
        StringBuilder res = new StringBuilder();
        if (enclosedElement.getKind() == ElementKind.METHOD) {
            for (AnnotationMirror annotation : enclosedElement.getAnnotationMirrors()) {
                res.append(visibilityViewer.getVisibility(annotation));
            }
            res.append(enclosedElement.getSimpleName());
            res.append("()");
        }
        return res.toString();
    }

    /**
     * Translate a class' field in his Puml equivalent
     *
     * @param enclosedElement an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    protected String translate_fields(Element enclosedElement) {
        StringBuilder res = new StringBuilder();
        if (enclosedElement.getKind() == ElementKind.FIELD)
            for (AnnotationMirror annotation : enclosedElement.getAnnotationMirrors()) {
                res.append(visibilityViewer.getVisibility(annotation));
            }
        res.append(enclosedElement.getSimpleName());
        return res.toString();
    }
}
