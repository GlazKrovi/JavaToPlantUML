package pumlFromJava.translators.elements.objects;

import pumlFromJava.translators.elements.objects.internals.PumlMethod;
import pumlFromJava.translators.elements.tools.TranslatorTools;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.util.List;

public class PumlInterface extends PumlObject implements InheritableObject {

    @Override
    public String selfTranslate(Element element) {
        // security
        if (element == null || element.getKind() != ElementKind.INTERFACE) {
            throw new IllegalArgumentException();
        }

        return "interface " +
                getFullName(element) + // name
                inheritanceTranslate(element) +
                open() + // {
                "\n" +
                contentTranslate(element) + // -field, +method
                close() + // }
                "\n";

    }

    @Override
    public String inheritanceTranslate(Element element) {
        // security
        if (element == null || element.getKind() != ElementKind.INTERFACE) {
            throw new IllegalArgumentException();
        }

        String res = "";
        StringBuilder info = new StringBuilder();
        TypeElement typeElement = (TypeElement) element;
        int nbImplements = 0; // number of implement who will be written
        boolean written = false; // specifies if 'implements' word is needed
        // implements smt?
        if (typeElement.getInterfaces().size() > 0) {
            List<? extends TypeMirror> implementedInterface = typeElement.getInterfaces();
            for (int i = 0; i < implementedInterface.size(); i++) {
                // is it a 'personal' interface?
                if (TranslatorTools.isNotFromJava(implementedInterface.get(i))) {
                    // so 'implements' word needed
                    written = true;
                    // 1 more impl
                    nbImplements++;
                    // get name of implemented interface
                    info.append(implementedInterface.get(i));
                    // finally, comma needed?
                    if (nbImplements >= 1 && i < implementedInterface.size() - 1) info.append(", ");
                }
            }
        }
        // have to be written?
        if (written) res = " extends " + info;
        return res;
    }

    @Override
    public String contentTranslate(Element element) {
        // security
        if (element == null || element.getKind() != ElementKind.INTERFACE) {
            throw new IllegalArgumentException();
        }

        StringBuilder res = new StringBuilder();
        for (Element enclosedElement : element.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.METHOD) {
                PumlMethod method = new PumlMethod(enclosedElement);
                res.append(method.getSelfTranslation());
                res.append("\n");
            }
        }
        return res.toString();
    }
}
