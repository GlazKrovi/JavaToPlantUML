package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.*;
import java.util.Set;

/**
 * Represents a Puml translator for java class
 */
public class PumlClass extends PumlClasses {
    private final VisibilityViewer visibilityViewer = new VisibilityViewer();
    private final Modificateurs modificateurs = new Modificateurs();

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
                // TODO
                // 2) else if pour les <<create>> pour constructeur
                // 1) faire un :  res.append(pumlMethod.getTranslation(enclosedElement)); pour les FIELD afin qu'il renvoie : " -nom : String " par exemple et pas seulement : "-nom"
                // 5) voir pk virgule n'est pas bien : of(nom : Stringgenre : Genre, ) : Boisson
                // 3) Faire le FINAL
                // 4) truc prof (multiplicité...)
                // 6) Rapport, DCC API, DCC doc,
            }
        }
        return res.toString();
    }

    // todo
    // visibility doesn't work for now
    protected String translate_methods(Element enclosedElement) { // TODO
        StringBuilder res = new StringBuilder();
        PumlMethod pumlMethod = new PumlMethod();
        if (enclosedElement.getKind() == ElementKind.METHOD) {
            Set<Modifier> visibility = enclosedElement.getModifiers();
            res.append(visibilityViewer.getVisibility(visibility));
            res.append(modificateurs.getStatic(visibility));
            res.append(modificateurs.getAbstract(visibility));
           // res.append(modificateurs.getFinal(visibility));
        }
        res.append(pumlMethod.getTranslation(enclosedElement));
        return res.toString();
    }

    // todo
    // visibility doesn't work for now, and type of field isn't printed
    /**
     * Translate a class' field in his Puml equivalent
     *
     * @param enclosedElement an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    protected String translate_fields(Element enclosedElement) {
        StringBuilder res = new StringBuilder();
        if (enclosedElement.getKind() == ElementKind.FIELD) {
            Set<Modifier> visibility = enclosedElement.getModifiers();
            res.append(visibilityViewer.getVisibility(visibility));
        }
        res.append(enclosedElement.getSimpleName());
        return res.toString();
    }
}