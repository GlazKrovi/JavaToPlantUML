package pumlFromJava.translators.elements.rawObjects.pumlClasses;

import pumlFromJava.translators.elements.internals.PumlMethod;
import pumlFromJava.translators.elements.tools.TranslatorTools;
import pumlFromJava.translators.elements.relations.PumlArrow;
import pumlFromJava.translators.elements.relations.PumlArrowLook;
import pumlFromJava.translators.viewers.VisibilityViewer;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;

/**
 * Represents a Puml translator for java class
 * (perfect for CCD)
 */
public class PumlClass extends PumlClasses {

    private final VisibilityViewer visibilityViewer = new VisibilityViewer();

    @Override
    public String relationsTranslate(Element element) {
        // security
        if (element == null || element.getKind() != ElementKind.CLASS) {
            throw new IllegalArgumentException();
        }

        String res = this.AggregationsCompositionsTranslate(element) +
                this.usesTranslate(element);
        // reset relations for next element process
        links.clear();
        return res;
    }

    @Override
    protected String AggregationsCompositionsTranslate(Element element) {
        StringBuilder res = new StringBuilder();
        PumlArrow pumlArrow = new PumlArrow(PumlArrowLook.DIAMOND_EMPTY); // aggregation by default
        VisibilityViewer visibilityViewer = new VisibilityViewer();
        if (TranslatorTools.isNotFromJava(element.asType())) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                // is the field a class or enum? (non-primitive)
                if (enclosedElement.asType().getKind() == TypeKind.DECLARED &&
                        !TranslatorTools.isPrimitiveType(enclosedElement.asType()) &&
                        TranslatorTools.isNotFromJava(enclosedElement.asType()) &&
                        !links.contains(enclosedElement.asType())) {
                    // get the name of the class or interface of enclosedElement
                    String className = enclosedElement.asType().toString();
                    // add the class name
                    res.append(className);
                    // add first multiplicity
                    res.append("\"1\"");
                    // add arrow
                    res.append(" ").append(pumlArrow.getArrow()).append(" ");
                    // add second multiplicity
                    if (TranslatorTools.isCollection(element.getSimpleName().toString())) res.append("\"*\"");
                    else res.append("\"1\"");
                    // get the defined class (aggregation or composition)
                    res.append(element.getSimpleName().toString());
                    // visibility and name
                    res.append(" : ");
                    res.append(visibilityViewer.selfTranslate(enclosedElement));
                    res.append(this.getSimplifiedName(enclosedElement));
                    // line break
                    res.append("\n");

                    // save the relation
                    links.add(enclosedElement.asType());
                }
            }
        }
        return res.toString();
    }

    @Override
    public String selfTranslate(Element element) {
        // security
        if (element == null || element.getKind() != ElementKind.CLASS) {
            throw new IllegalArgumentException();
        }

        String classType = "class ";
        if (element.getModifiers().contains(Modifier.ABSTRACT)) classType = "abstract ";
        return classType + // class (or abstract)
                modifiersViewer.selfTranslate(element) + // {abstract}
                annotationsViewer.selfTranslate(element) + // {@myPersonalTag}
                getFullName(element) + // name
                inheritanceTranslate(element) + // extends something implements otherThing
                open() + // {
                "\n" +
                contentTranslate(element) + // -field, +method
                close() + // }
                "\n";
    }

    @Override
    public String contentTranslate(Element element) {
        // security
        if (element == null || element.getKind() != ElementKind.CLASS) {
            throw new IllegalArgumentException();
        }

        StringBuilder res = new StringBuilder();
        String temporary = "";
        if (element.getKind() == ElementKind.CLASS) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.METHOD) {
                    temporary = methodsTranslate(enclosedElement);
                } else if (enclosedElement.getKind() == ElementKind.FIELD) {
                    temporary = fieldsTranslate(enclosedElement);
                } else if (enclosedElement.getKind() == ElementKind.CONSTRUCTOR) {
                    temporary = constructorsTranslate(enclosedElement);
                }
                // is it correct?
                if (!temporary.isEmpty()) {
                    res.append(temporary).append("\n");
                    temporary = "";
                }
            }
        }
        return res.toString();
    }

    /**
     * Translate class' methods in their Puml equivalent
     *
     * @param element an enclosed element from class
     * @return Returns translated string
     */
    private String methodsTranslate(Element element) {
        StringBuilder res = new StringBuilder();
        PumlMethod pumlMethod = new PumlMethod();
        if (element.getKind() == ElementKind.METHOD) {
            res.append(visibilityViewer.selfTranslate(element));
            res.append(modifiersViewer.selfTranslate(element));
            res.append(pumlMethod.selfTranslate(element));
        }

        return res.toString();
    }

    /**
     * Translate class' fields in their Puml equivalent
     *
     * @param element an enclosed element from class
     * @return Returns translated string
     */
    private String fieldsTranslate(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.FIELD && TranslatorTools.isPrimitiveType(element.asType())) {
            res.append(visibilityViewer.selfTranslate(element));
            res.append(modifiersViewer.selfTranslate(element));
            res.append(element.getSimpleName());
            res.append(" : ");
            res.append(TranslatorTools.reformatName(element.asType().toString()));
        }
        return res.toString();
    }

    /**
     * Translate class' constructors in their Puml equivalent
     *
     * @param element an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    private String constructorsTranslate(Element element) {
        StringBuilder res = new StringBuilder();
        PumlMethod pumlMethod = new PumlMethod();
        if (element.getKind() == ElementKind.CONSTRUCTOR) {
            res.append(visibilityViewer.selfTranslate(element));
            res.append(" <<create>> ");
            res.append(pumlMethod.selfTranslate(element));
        }
        return res.toString();
    }

    @Override
    protected String usesTranslate(Element element) {
        StringBuilder res = new StringBuilder();
        PumlArrow arrow = new PumlArrow(PumlArrowLook.HEADFULL_DOTTED);
        for (Element enclosedElement : element.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.METHOD) {
                ExecutableElement executableElement = (ExecutableElement) enclosedElement;
                for (VariableElement parameter : executableElement.getParameters()) {
                    // managing classes used by another
                    if (TranslatorTools.isNotFromJava(parameter.asType()) &&
                            !TranslatorTools.isPrimitiveType(parameter.asType()) &&
                            !links.contains(parameter.asType())) {
                        res.append(this.getFullName(element));
                        // add first multiplicity
                        res.append("\"1\"");
                        res.append(arrow.getArrow());
                        // add second multiplicity
                        if (TranslatorTools.isCollection(element.getSimpleName().toString())) res.append("\"*\"");
                        else res.append("\"1\"");
                        res.append(parameter.asType());
                        res.append(" : <<Use>>");
                        res.append("\n");

                        // save the relation
                        links.add(parameter.asType());
                    }
                }
            }
        }
        return res.toString();
    }
}


