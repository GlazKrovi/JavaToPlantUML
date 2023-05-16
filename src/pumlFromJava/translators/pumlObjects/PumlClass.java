package pumlFromJava.translators.pumlObjects;

import pumlFromJava.translators.visibilityViewer.VisibilityViewer;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

/**
 * Represents a Puml translator for java class
 */
public class PumlClass extends APumlClass {
    private final VisibilityViewer visibilityViewer = new VisibilityViewer();

    public PumlClass() {}

    public String getContent(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.CLASS) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.METHOD) {
                    res.append(translate_method(enclosedElement));
                    res.append(getLineBreaker());
                }
                else if (enclosedElement.getKind() == ElementKind.FIELD
                        && is_primitive(enclosedElement.asType())) {
                    res.append(translate_field(enclosedElement));
                    res.append(getLineBreaker());
                }
            }
        }
        return res.toString();
    }

    /* Subsidiary functions */
    /**
     * Translate a class' method in his Puml equivalent
     * @param enclosedElement an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    private String translate_method(Element enclosedElement){
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
     * @param enclosedElement an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    private String translate_field(Element enclosedElement){
        StringBuilder res = new StringBuilder();
        if (enclosedElement.getKind() == ElementKind.FIELD)
            for (AnnotationMirror annotation : enclosedElement.getAnnotationMirrors()) {
            res.append(visibilityViewer.getVisibility(annotation));
        }
        res.append(enclosedElement.getSimpleName());
        return res.toString();
    }
}
