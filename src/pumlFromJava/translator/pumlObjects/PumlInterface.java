package pumlFromJava.translator.pumlObjects;

import pumlFromJava.translator.visibilityViewer.VisibilityViewer;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.TypeMirror;

public class PumlInterface implements PumlObject {

    public PumlInterface() {}

    /**
     * Translate interface Java file into his Puml equivalent,
     * @param element an interface
     * @return Returns string of type "file-type name" for Uml
     * if and only if the specified element is of the correct type, returns an empty string otherwise
     */
    @Override
    public String getName(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.INTERFACE){
            res = "interface " + element.getSimpleName().toString() + " <<interface>>";
        }
        return res;
    }

    /**
     * Translate what is inside the Element (like attributes, constants or method)
     * @param element an enclosed element from class, enum or interface
     * @return Puml equivalents of each attribute, constants or method from specified Element
     */
    @Override
    public String getContent(Element element) { // koll manque saut ligne et {
        StringBuilder res = new StringBuilder();
        VisibilityViewer visibilityViewer = new VisibilityViewer();
        if (element.getKind() == ElementKind.INTERFACE) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.METHOD) {
                    for (AnnotationMirror annotation : enclosedElement.getAnnotationMirrors()) {
                        res.append(visibilityViewer.translate_visibility(annotation));
                    }
                    res.append(enclosedElement.getSimpleName() + "()");
                    res.append(this.getLineBreaker());
                }
            }
        }
        return res.toString();
    }
}
