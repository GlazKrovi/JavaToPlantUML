package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.*;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

public abstract class PumlClasses extends PumlObject implements InheritableObject {
    /**
     * Translate a Java class file into his Puml equivalent
     *
     * @param element any java class
     * @return Returns string of type "file-type name" for Uml
     * if and only if the specified element is of the correct type, returns an empty string otherwise
     */
    public String getName(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.CLASS) {
            res = "class " + element.getSimpleName().toString();
        }
        return res;
    }

    public String getInheritance(Element element) {
        StringBuilder res = new StringBuilder();
        if (element != null) {
            TypeElement typeElement = (TypeElement) element; // cast
            res.append(getExtends(typeElement));
            res.append(getImplements(typeElement));
        }
        return res.toString();
    }

    /**
     * Translates the relations of the specified class
     *
     * @param element a class element
     * @return Returns string like Hamburger -- Steak 'or' Hamburger -- MealType.FOOD
     */
    public String getUses(Element element) {
        return getAggregationsCompositions(element) +
                getMethodsUsage(element);
    }

    /**
     * Indicates the superclass that the specified element extends.
     *
     * @param typeElement a class element casted in TypeElement
     * @return Returns the name of the extended superclass
     */
    protected String getExtends(TypeElement typeElement) {
        String res = "";
        if (typeElement.getSuperclass() != null && isNotFromJava(typeElement.getSuperclass())) {
            res = " extends " + typeElement.getSuperclass();
        }
        return res;
    }

    /**
     * Indicates the interfaces that the specified element extends.
     *
     * @param typeElement a class element casted in TypeElement
     * @return Returns the name of the extended superclass
     */
    protected String getImplements(TypeElement typeElement) {
        String res = "";
        StringBuilder info = new StringBuilder();
        int nbImplements = 0; // number of implement who will be written
        boolean written = false; // specifies if 'implements' word is needed
        // implements smt?
        if (typeElement.getInterfaces().size() > 0) {
            for (TypeMirror implementedInterface : typeElement.getInterfaces()) {
                // is it a 'personal' interface?
                if (isNotFromJava(implementedInterface)) {
                    // so 'implements' word needed
                    written = true;
                    // 1 more impl
                    nbImplements++;
                    // get name of implemented interface
                    info.append(implementedInterface);
                    // finally, comma needed?
                    if (nbImplements > 1) info.append(", ");
                }
            }
        }
        // have to be written?
        if (written) res = " implements " + info;
        return res;
    }

    /**
     * Translates the usage of another class or some enum or interface into the specified class
     *
     * @param element a class element
     * @return Returns string like Hamburger -- Steak 'or' Hamburger -- MealType.FOOD
     */
    protected String getMethodsUsage(Element element) { // todo
        /*
        for (Element enclosedElement : element.getEnclosedElements()) {
            // get the class' methods
            if (enclosedElement.getKind() == ElementKind.METHOD) {
                ExecutableElement executableElement = (ExecutableElement) enclosedElement;
                System.out.println(enclosedElement.getSimpleName()); // test
                // get the types of parameters
                for (TypeParameterElement typeParameter : executableElement.getTypeParameters()) {
                    System.out.println(typeParameter.getGenericElement());
                }
            }
        }
        return "";

         */
        return "";
    }

    /**
     * Get the aggregations and compositions of the specified class
     *
     * @param element a class
     * @return Returns string like Hamburger -- Steak
     */
    protected String getAggregationsCompositions(Element element) {
        StringBuilder res = new StringBuilder();
        if (element != null && element.getKind() == ElementKind.CLASS) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                // is the field a class or enum? (non-primitive)
                if (enclosedElement.asType().getKind() == TypeKind.DECLARED &&
                        !isPrimitiveType(enclosedElement.asType()) &&
                        isNotFromJava(enclosedElement.asType())) {
                    // get the name of the class of enclosedElement
                    String className = enclosedElement.asType().toString();
                    // add the class name
                    res.append(className);
                    // add arrow
                    res.append(" -- ");
                    // get the defined class (aggregation or composition)
                    res.append(element.getSimpleName().toString());
                    // line break
                    res.append("\n");
                }
            }
        }
        return res.toString();
    }

    /**
     * Translate a class' method in his Puml equivalent
     *
     * @param enclosedElement an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    protected abstract String translate_methods(Element enclosedElement);

    /**
     * Translate a class' field in his Puml equivalent
     *
     * @param enclosedElement an enclosed element from class, enum or interface
     * @return Returns translated string
     */
    protected abstract String translate_fields(Element enclosedElement);
}
