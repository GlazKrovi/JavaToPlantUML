package pumlFromJava.translators.pumlEntities.pumlObjects;

import pumlFromJava.translators.pumlEntities.PumlEntity;

import javax.lang.model.element.Element;

/**
 * Translator for any java package, class, enum or interface
 */
public interface PumlObjectSpecies extends PumlEntity {
    /**
     * Translate what is inside the Element (like attributes, constants or method)
     *
     * @param element an element like class, enum or interface
     * @return Puml equivalents of each attribute, constants or method from specified Element
     */
    String getContent(Element element);

    /**
     * Gives an open bracket to open the element description
     *
     * @return " {"
     */
    String open();

    /**
     * Gives a line break and a closing bracket to close the element description
     *
     * @return Returns the string "\n}"
     */
    String close();
}