package pumlFromJava.translators.pumlEntities;

import javax.lang.model.element.Element;

public interface PumlEntity {
    /**
     * Translate the name (like class, enum or interface)
     * and what is inside the Element (like attributes, constants or method)
     *
     * @param element an enclosed element from class, enum or interface
     * @return Puml equivalents of an entire object (like class fruit { -field, +method() }
     */
    String getTranslation(Element element);

    /**
     * Translates any Java file type into his Puml equivalent
     * (Ex : class Test, enum Kinds, etc.)
     *
     * @param element a package, class, enum or interface
     * @return Returns string of type "package-name.file-type name" for Uml,
     * if and only if the specified element is of the correct type, returns an empty string otherwise
     */
    String getName(Element element);
}
