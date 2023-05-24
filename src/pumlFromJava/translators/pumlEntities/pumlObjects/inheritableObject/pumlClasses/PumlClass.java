package pumlFromJava.translators.pumlEntities.pumlObjects.inheritableObject.pumlClasses;

import pumlFromJava.translators.pumlEntities.PumlType;
import pumlFromJava.translators.pumlEntities.VisibilityViewer;
import pumlFromJava.translators.pumlEntities.pumlObjects.ModifiersViewer;
import pumlFromJava.translators.pumlEntities.pumlObjects.PumlMethod;

import javax.lang.model.element.*;
import java.util.Set;

/**
 * Represents a Puml translator for java class
 */
public class PumlClass extends PumlClasses {
    private final VisibilityViewer visibilityViewer = new VisibilityViewer();
    private final ModifiersViewer modifiersViewer = new ModifiersViewer();

    public PumlClass() {
    }

    public String getContent(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.CLASS) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.METHOD) {
                    res.append(translate_methods(enclosedElement));
                    res.append(getLineBreaker());
                } else if (enclosedElement.getKind() == ElementKind.FIELD) {
                    res.append(translate_fields(enclosedElement));
                    res.append(getLineBreaker());
                }
                else if (enclosedElement.getKind() == ElementKind.CONSTRUCTOR) {
                    res.append(translate_constructor(enclosedElement));
                    res.append(getLineBreaker());
                }
            }
        }
        return res.toString();
    }

    protected String translate_methods(Element enclosedElement) {
        StringBuilder res = new StringBuilder();
        PumlMethod pumlMethod = new PumlMethod();
        if (enclosedElement.getKind() == ElementKind.METHOD) {
            Set<Modifier> visibility = enclosedElement.getModifiers();
            res.append(visibilityViewer.getVisibility(visibility));
            res.append(modifiersViewer.getStatic(visibility));
            res.append(modifiersViewer.getAbstract(visibility));
            res.append(modifiersViewer.getFinal(visibility));
            res.append(pumlMethod.getTranslation(enclosedElement));
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
        PumlType pumlType = new PumlType();
        if (enclosedElement.getKind() == ElementKind.FIELD) {
            Set<Modifier> visibility = enclosedElement.getModifiers();
            res.append(visibilityViewer.getVisibility(visibility));
            res.append(modifiersViewer.getStatic(visibility));
            res.append(modifiersViewer.getAbstract(visibility));
            res.append(modifiersViewer.getFinal(visibility));
            res.append(enclosedElement.getSimpleName());
            res.append(" : ");
            res.append(pumlType.getTranslation(enclosedElement));
        }
        return res.toString();
    }

    protected String translate_constructor(Element enclosedElement) {
        StringBuilder res = new StringBuilder();
        PumlMethod pumlMethod = new PumlMethod();
        if (enclosedElement.getKind() == ElementKind.CONSTRUCTOR) {
            Set<Modifier> visibility = enclosedElement.getModifiers();
            res.append(visibilityViewer.getVisibility(visibility));
            res.append(" <<create>> ");
            res.append(pumlMethod.getTranslation(enclosedElement));
        }

        return res.toString();
    }
}