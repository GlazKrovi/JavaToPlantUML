package pumlFromJava.translators.pumlObjects.pumlClasses;

import pumlFromJava.translators.TranslatorTools;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;

/**
 * Represents a Puml translator for java class,
 * but doesn't show any method, visibility, constructors
 * or aggregation/composition name
 * (perfect for ACD)
 */
public class PumlLiteClass extends PumlClasses {

    @Override
    public String selfTranslate(Element element) {
        String classType = "class ";
        StringBuilder res = new StringBuilder();
        // opening
        if (element.getModifiers().contains(Modifier.ABSTRACT)) classType = "abstract ";
        res.append(classType) // class (or abstract)
            .append(modifiersViewer.selfTranslate(element)) // {abstract}
            .append(getFullName(element)) // name
            .append(inheritanceTranslate(element)); // extends something implements otherThing
        // content (open and closing)?
        if (countPrimitivesFields(element) > 0){
            res.append(open()); // {
            res.append("\n");
            res.append(contentTranslate(element)); // -field, +method
            res.append(close()); // }
        }
        // line break
        res.append("\n\n");
        // return
        return res.toString();
    }

    @Override
    public String relationsTranslate(Element element) {
        return getAggregationsCompositions(element);
    }

    @Override
    protected String getUses(Element element) {
        StringBuilder res = new StringBuilder();
        for (Element enclosedElement : element.getEnclosedElements()) {
            if (enclosedElement.getKind() == ElementKind.METHOD) {
                ExecutableElement executableElement = (ExecutableElement) enclosedElement;
                for (VariableElement parameter : executableElement.getParameters()) {
                    // managing classes used by another
                    if (TranslatorTools.isNotFromJava(parameter.asType()) &&
                            !TranslatorTools.isPrimitiveType(parameter.asType())) {
                        res.append(this.getFullName(element));
                        res.append(" -- ");
                        res.append(parameter.asType());
                        res.append(" : <<Use>>");
                        res.append("\n");
                    }
                }
            }
        }
        return res.toString();
    }

    @Override
    protected String getAggregationsCompositions(Element element) {
        StringBuilder res = new StringBuilder();
        if (element != null && element.getKind() == ElementKind.CLASS && TranslatorTools.isNotFromJava(element.asType())) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                // is the field a class or enum? (non-primitive)
                if (enclosedElement.asType().getKind() == TypeKind.DECLARED &&
                        !TranslatorTools.isPrimitiveType(enclosedElement.asType()) &&
                        TranslatorTools.isNotFromJava(enclosedElement.asType())) {
                    // get the name of the class or interface of enclosedElement
                    String className = enclosedElement.asType().toString();
                    // add the class name
                    res.append(className);
                    // add arrow
                    res.append(" <-- ");
                    // get the defined class (aggregation or composition)
                    res.append(element.getSimpleName().toString());
                    // use
                    res.append(" : <<Use>>");
                    // line break
                    res.append("\n");
                }
            }
        }
        return res.toString();
    }

    @Override
    public String contentTranslate(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.CLASS) {
            // search inside
            res.append(fieldsTranslate(element));
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
        if (element.getKind() == ElementKind.CLASS) {
            // search inside
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.FIELD &&
                        TranslatorTools.isPrimitiveType(enclosedElement.asType())) {
                    res.append(enclosedElement);
                    res.append("\n");
                }
            }
        }
        return res.toString();
    }

    // todo
    private int countPrimitivesFields(Element element){
        int nbPrimitiveFields = 0;
        if (element.getKind() == ElementKind.CLASS) {
            for (Element enclosedElement : element.getEnclosedElements()){
                if (enclosedElement.getKind() == ElementKind.FIELD && TranslatorTools.isPrimitiveType(enclosedElement.asType())){
                    nbPrimitiveFields++;
                }
            }
        }
        return nbPrimitiveFields;
    }
}
