package pumlFromJava.translators.elements.internals;

import javax.lang.model.element.ElementKind;
import javax.lang.model.type.TypeMirror;

/**
 * Any class who represents a specific content of a PumlRawObject,
 * like method or field
 */
public interface RawInternal {
    /**
     * Gives the translation of the elements contained in this internal element
     * (like name and type for a field, or also the returned type for a method)
     *
     * @return String like 'fieldName : fieldType' or
     * 'methodName(arg1Name : arg1Type, arg2Name : arg2Type) : returnedType'
     */
    String getSelfTranslation();

    /**
     * @return Returns the name of the internal element
     */
    String getName();

    /**
     * @return Returns the type of the internal element,
     * in an ElementKind form (CLASS, ENUM, INTERFACE, etc.)
     */
    ElementKind getKind();

    /**
     * @return Returns the entire the name of the element that this internal element
     * instantiates (like food.Meals, if the internal element is "eat()" method)
     */
    TypeMirror getType();
}
