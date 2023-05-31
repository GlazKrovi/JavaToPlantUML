package pumlFromJava.translators.elements.objects;

import javax.lang.model.element.Element;

/**
 * Represents any type of element (class, enum, interface, method, constructor, fields, ...)
 * in his puml equivalent
 */
public interface PumlRawObject {
    /**
     * Translate the name (like class, enum or interface)
     * and what is inside the Element (like attributes, constants or method)
     *
     * @param element an enclosed element from class, enum or interface
     * @return Puml equivalents of an entire object (like class fruit { -field, +method() }
     */
    String selfTranslate(Element element);
}
