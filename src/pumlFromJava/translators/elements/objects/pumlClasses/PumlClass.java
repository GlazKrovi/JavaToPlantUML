package pumlFromJava.translators.elements.objects.pumlClasses;

import pumlFromJava.translators.elements.objects.internals.PumlConstructor;
import pumlFromJava.translators.elements.objects.internals.PumlField;
import pumlFromJava.translators.elements.objects.internals.PumlMethod;
import pumlFromJava.translators.elements.objects.viewers.VisibilityViewer;
import pumlFromJava.translators.elements.relations.PumlArrow;
import pumlFromJava.translators.elements.relations.PumlArrowLook;
import pumlFromJava.translators.elements.tools.TranslatorTools;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;

/**
 * Represents a Puml translator for java class
 * (perfect for CCD)
 */
public class PumlClass extends PumlClasses {
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
                    temporary = oneMethodTranslate(enclosedElement);
                } else if (enclosedElement.getKind() == ElementKind.FIELD &&
                        TranslatorTools.isPrimitiveType(enclosedElement.asType())) {
                    temporary = onePrimitiveFieldTranslate(enclosedElement);
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
    private String oneMethodTranslate(Element element) {
        PumlMethod method = new PumlMethod(element);
        return method.getSelfTranslation();
    }

    /**
     * Translate class' fields in their Puml equivalent
     *
     * @param element an enclosed element from class
     * @return Returns translated string
     */
    private String onePrimitiveFieldTranslate(Element element) {
        PumlField field = new PumlField(element);
        return field.getSelfTranslation();
    }

    /**
     * Translate class' constructors in their Puml equivalent
     *
     * @param element an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    private String constructorsTranslate(Element element) {
        PumlConstructor constructor = new PumlConstructor(element);
        return String.valueOf(constructor.getSelfTranslation());
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
                        res.append("\"").append(TranslatorTools.cutCollection(this.getFullName(element))).append("\"");
                        // add first multiplicity
                        res.append("\"1\"");
                        res.append(" ").append(arrow.getArrow()).append(" ");
                        // add second multiplicity
                        if (TranslatorTools.isCollection(element.getSimpleName().toString())) res.append("\"*\"");
                        else res.append("\"1\"");
                        res.append("\"").append(TranslatorTools.cutCollection(parameter.asType().toString())).append("\"");
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


