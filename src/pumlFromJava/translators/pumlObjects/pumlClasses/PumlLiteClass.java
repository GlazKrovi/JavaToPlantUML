package pumlFromJava.translators.pumlObjects.pumlClasses;

import pumlFromJava.translators.TranslatorTools;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;

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
