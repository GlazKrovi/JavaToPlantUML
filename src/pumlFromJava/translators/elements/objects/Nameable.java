package pumlFromJava.translators.elements.objects;

import javax.lang.model.element.Element;

/**
 * Represents an element with a name and package name
 */
public interface Nameable {
    /**
     * Give the full name of the specified element
     *
     * @param element a package, class, enum or interface
     * @return Returns string of type "package-name.file-name",
     * if and only if the specified element is of the correct type,
     * returns an empty string otherwise
     */
    String getFullName(Element element);

    /**
     * Give the simplified name of the specified element
     *
     * @param element a package, class, enum or interface
     * @return Returns string of type "file-name" (without packages name),
     * if and only if the specified element is of the correct type,
     * returns an empty string otherwise
     */
    String getSimplifiedName(Element element);
}
