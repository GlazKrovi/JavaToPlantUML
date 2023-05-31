package pumlFromJava.translators.elements.relations;

import javax.lang.model.element.Element;

public interface RelationableObject {
    /**
     * Translate all relations from this class with another in their Puml equivalent
     *
     * @param element a class, enum or interface element
     * @return Returns string like 'meal.Burger <-- ingredients.Steak : << Use >>'
     * or 'Apple <-- IngredientsKind.FRUIT : << Use >>'
     */
    String relationsTranslate(Element element);
}