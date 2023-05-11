package pumlFromJava.translator.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PumlNonVisibleClass implements PumlObject {
    /**
     * Translates any Java file type into his Puml equivalent
     * (Ex : class Test, enum Kinds, etc)
     * @param element a package, class, enum or interface
     * @return Returns string of type "file-type name" for Uml,
     * if and only if the specified element is of the correct type, returns an empty string otherwise
     */
    @Override
    public String getName(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.CLASS){
            res = "class " + element.getSimpleName(); ;
        }
        return res;
    }

    /**
     * Translate what is inside the Element (like attributes, constants or method),
     * but without his visibility
     * @param element an enclosed element from class, enum or interface
     * @return Puml equivalents of each attribute, constants or method from specified Element
     */
    @Override
    public String getContent(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.CLASS) {
            // search inside
            res.append(getFields(element));
            res.append(getMethods(element));
        }
        return res.toString();
    }

    /**
     * Translate all class' field in his Puml equivalent,
     * without his visibility
     * @param element an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    public String getFields(Element element){
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.CLASS) {
            // search inside
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.FIELD
                        && is_primitive(enclosedElement.asType())) {
                    res.append(translate_field(enclosedElement));
                    res.append(getLineBreaker());
                }
            }
        }
        return res.toString();
    }

    /**
     * Translate all class' method in his Puml equivalent,
     * without his visibility
     * @param element an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    public String getMethods(Element element){
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.CLASS) {
            // search inside
            for (Element enclosedElement : element.getEnclosedElements()) {
                if (enclosedElement.getKind() == ElementKind.METHOD) {
                    res.append(translate_method(enclosedElement));
                    res.append(getLineBreaker());
                }
            }
        }
        return res.toString();
    }

    /**
     * Translate a class' field in his Puml equivalent,
     * without his visibility
     * @param enclosedElement an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    private String translate_field(Element enclosedElement){
        return enclosedElement.getSimpleName().toString();
    }

    /**
     * Translate a class' method in his Puml equivalent,
     * without his visibility
     * @param enclosedElement an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    private String translate_method(Element enclosedElement){
        return enclosedElement.getSimpleName() + "()";
    }
}
