package pumlFromJava.translators.elements.relations;

import javax.lang.model.element.Element;

/**
 * Represents any java element translation in Puml, who can "use" another
 * object-element (like class, enums, interfaces)
 *
 * @pumlUses
 */
public interface RelationableObject {
    /**
     * Translate all relations from this class with another in their Puml equivalent
     *
     * @param element a class, enum or interface element
     * @return Returns string like 'meal.Burger <-- ingredients.Steak : << Use >>'
     * or 'Apple <-- IngredientsKind.FRUIT : << Use >>'
     * @pumlUses
     */
    String relationsTranslate(Element element);
}