package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

public class PumlInterface extends PumlObject implements InheritableObject {

    public PumlInterface() {
    }

    public String getName(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.INTERFACE) {
            res = "interface " + element.getSimpleName().toString() + " <<interface>>";
        }
        return res;
    }

    public String getContent(Element element) {
        StringBuilder res = new StringBuilder();
        VisibilityViewer visibilityViewer = new VisibilityViewer();
        if (element.getKind() == ElementKind.INTERFACE) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.METHOD) {
                    for (AnnotationMirror annotation : enclosedElement.getAnnotationMirrors()) {
                        res.append(visibilityViewer.getVisibility(annotation));
                    }
                    res.append(enclosedElement.getSimpleName());
                    res.append("()");
                    res.append(this.getLineBreaker());
                }
            }
        }
        return res.toString();
    }

    public String getInheritance(Element element) {
        String res = "";
        StringBuilder infos = new StringBuilder();
        TypeElement typeElement = (TypeElement) element;
        int nbImplements = 0; // number of implement who will be written
        boolean written = false; // specifies if 'implements' word is needed
        // implements smt?
        if (typeElement.getInterfaces().size() > 0) {
            for (TypeMirror implementedInterface : typeElement.getInterfaces()) {
                // is it a 'personal' interface?
                if (isNotFromJava(implementedInterface)) {
                    // so 'implements' word needed
                    written = true;
                    // 1 more impl
                    nbImplements++;
                    // get name of implemented interface
                    infos.append(implementedInterface);
                    // finally, comma needed?
                    if (nbImplements > 1) infos.append(", ");
                }
            }
        }
        // have to be written?
        if (written) res = " extends " + infos;
        return res;
    }
}
