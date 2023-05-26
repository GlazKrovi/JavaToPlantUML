package pumlFromJava.translators.pumlElements.pumlObjects.pumlObjectKind.pumlClasses;

import pumlFromJava.translators.TranslatorTools;
import pumlFromJava.translators.pumlElements.pumlObjects.InheritableObject;
import pumlFromJava.translators.pumlElements.pumlObjects.PumlObject;
import pumlFromJava.translators.pumlElements.pumlObjects.RelationableObject;
import pumlFromJava.translators.pumlElements.pumlViewers.ModifiersViewer;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

/**
 * A puml equivalent for any type of class,
 * more or less precise according to the scheme's rules
 */
public abstract class PumlClasses extends PumlObject implements InheritableObject, RelationableObject {

    protected final ModifiersViewer modifiersViewer = new ModifiersViewer();

    @Override
    public abstract String selfTranslate(Element element) ;

    @Override
    public String inheritanceTranslate(Element element) {
        StringBuilder res = new StringBuilder();
        if (element != null) {
            TypeElement typeElement = (TypeElement) element; // cast
            res.append(getExtends(typeElement));
            res.append(getImplements(typeElement));
        }
        return res.toString();
    }

    @Override
    public abstract String contentTranslate(Element element);

    @Override
    public abstract String relationsTranslate(Element element);

    /**
     * Indicates the superclass that the specified element extends.
     *
     * @param typeElement a class element casted in TypeElement
     * @return Returns the name of the extended superclass
     */
    private String getExtends(TypeElement typeElement) {
        String res = "";
        if (typeElement.getSuperclass() != null && TranslatorTools.isNotFromJava(typeElement.getSuperclass())) {
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
    private String getImplements(TypeElement typeElement) {
        String res = "";
        StringBuilder info = new StringBuilder();
        int nbImplements = 0; // number of implement who will be written
        boolean written = false; // specifies if 'implements' word is needed
        // implements smt?
        if (typeElement.getInterfaces().size() > 0) {
            for (TypeMirror implementedInterface : typeElement.getInterfaces()) {
                // is it a 'personal' interface?
                if (TranslatorTools.isNotFromJava(implementedInterface)) {
                    // so 'implements' word needed
                    written = true;
                    // 1 more impl
                    nbImplements++;
                    // get name of implemented interface
                    info.append(implementedInterface);
                    // finally, comma needed?
                    if (nbImplements >= 1 && nbImplements < typeElement.getInterfaces().size()) info.append(", ");
                }
            }
        }
        // have to be written?
        if (written) res = " implements " + info;
        return res;
    }

    /**
     * Get the aggregations and compositions of the specified class
     *
     * @param element a class
     * @return Returns string like Hamburger -- Steak
     */
    protected String getAggregationsCompositions(Element element) {
        StringBuilder res = new StringBuilder();
        if (element != null && element.getKind() == ElementKind.CLASS && TranslatorTools.isNotFromJava(element.asType())) {
            for (Element enclosedElement : element.getEnclosedElements()) {
                // is the field a class or enum? (non-primitive)
                if (enclosedElement.asType().getKind() == TypeKind.DECLARED &&
                        !TranslatorTools.isPrimitiveType(enclosedElement.asType()) &&
                        TranslatorTools.isNotFromJava(enclosedElement.asType())) {
                    // get the name of the class or interface of enclosedElement
                    String className = enclosedElement.asType().toString();
                    // add the class name
                    res.append(className);
                    // add arrow
                    res.append(" <-- ");
                    // get the defined class (aggregation or composition)
                    res.append(element.getSimpleName().toString());
                    // use
                    res.append(" : <<Use>>");
                    // line break
                    res.append("\n");
                }
            }
        }
        return res.toString();
    }
}
