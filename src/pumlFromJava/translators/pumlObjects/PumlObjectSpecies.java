package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;

/**
 * Translator for any java package, class, enum or interface
 */
public interface PumlObjectSpecies {
    /**
     * Translates any Java file type into his Puml equivalent
     * (Ex : class Test, enum Kinds, etc)
     *
     * @param element a package, class, enum or interface
     * @return Returns string of type "file-type name" for Uml,
     * if and only if the specified element is of the correct type, returns an empty string otherwise
     */
    String getName(Element element);

    /**
     * Translate what is inside the Element (like attributes, constants or method)
     *
     * @param element an element like class, enum or interface
     * @return Puml equivalents of each attribute, constants or method from specified Element
     */
    String getContent(Element element);

    /**
     * Translate the name (like class, enum or interface)
     * and what is inside the Element (like attributes, constants or method)
     *
     * @param element an enclosed element from class, enum or interface
     * @return Puml equivalents of an entire object (like class fruit { -field, +method() }
     */
    String getTranslation(Element element);

    /**
     * Gives an open bracket to open the element description
     *
     * @return " {"
     */
    default String open() {
        return " {";
    }

    /**
     * Gives a line break and a closing bracket to close the element description
     *
     * @return Returns the string "\n}"
     */
    default String close() {
        return "\n}";
    }
}