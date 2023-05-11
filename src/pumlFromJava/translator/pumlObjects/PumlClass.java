package pumlFromJava.translator.pumlObjects;

import pumlFromJava.translator.visibilityViewer.VisibilityViewer;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

/**
 * Represents a Puml translator for java class
 */
public class PumlClass implements PumlObject {

    VisibilityViewer visibilityViewer = new VisibilityViewer();

    public PumlClass() {}

    /**
     * Translate a Java class file into his Puml equivalent
     * @param element any java class
     * @return Returns string of type "file-type name" for Uml
     * if and only if the specified element is of the correct type, returns an empty string otherwise
     */
    @Override
    public String getName(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.CLASS){
            res = "class " + element.getSimpleName().toString();
        }
        return res;
    }

    /**
     * Translate what is inside the Element (like attributes, constants or method)
     * @param element an enclosed element from class, enum or interface
     * @return Puml equivalents of each attribute, constants or method from specified Element
     */
    @Override
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
                res.append(visibilityViewer.translate_visibility(annotation));
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
            res.append(visibilityViewer.translate_visibility(annotation));
        }
        res.append(enclosedElement.getSimpleName());
        return res.toString();
    }
}
