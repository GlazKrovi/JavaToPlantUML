package pumlFromJava.translator.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

/**
 * Translator for any java package, class, enum or interface
 */
public interface IPumlObject {
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
     * @param element an enclosed element from class, enum or interface
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
    default String getTranslation(Element element) {
        return getName(element) + // name
                open() + // {
                getLineBreaker() +
                getContent(element) + // -field, +method
                close() + // }
                getLineBreaker();
    }

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

    /**
     * Indicates if the type passed is primitive (int, float, void, etc) or not
     *
     * @param type TypeMirror from a class
     * @return Returns true if the type passed is primitive, false otherwise
     */
    default boolean is_primitive(TypeMirror type) {
        return type.getKind().isPrimitive();
    }

    /**
     * Gives a line break
     *
     * @return Returns "\n"
     */
    default String getLineBreaker() {
        return "\n";
    }
}
