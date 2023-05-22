package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;

/**
 * Represent an object that can implement interfaces and extends a super-class
 */
public interface InheritableObject {
    /**
     * Translates what the element inherits from (implementation or extension)
     *
     * @param element an element like class, enum or interface
     * @return Puml equivalents for smt extends/inherit Asmt/Ismt
     */
    String getInheritance(Element element);
}
