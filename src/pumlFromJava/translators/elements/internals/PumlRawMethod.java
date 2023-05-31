package pumlFromJava.translators.elements.internals;

import pumlFromJava.translators.elements.tools.TranslatorTools;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

/**
 * Represents any method
 * with his visibility, modifiers, parameters
 * and eventual returned type
 */
public abstract class PumlRawMethod extends PumlInternal {
    public PumlRawMethod(Element self) {
        super(self);
        if (self == null) throw new NullPointerException();
    }
    public String getSimplifiedName() {
        return TranslatorTools.cutPackage(self.getSimpleName().toString());
    }

    public String getFullName() {
        String res = "";
        if (self.getKind() == ElementKind.METHOD) {
            res = self.getSimpleName().toString();
        } else if (self.getKind() == ElementKind.CONSTRUCTOR) {
            res = self.getEnclosingElement().getSimpleName().toString();
        }
        return res;
    }

    @Override
    public abstract String getSelfTranslation() ;

    /**
     * Gives puml equivalent for parameters of specified class' method
     *
     * @param methodElement A cast element representing a method
     * @return String like 'parameter1 : parameterType1, parameter2 : parameterType2'
     */
    protected String getParameters(ExecutableElement methodElement) {
        StringBuilder res = new StringBuilder();
        int nbType = 0;
        for (VariableElement parameter : methodElement.getParameters()) {
            PumlType parameterType = new PumlType(parameter.asType());
            // managing classes used by another
            res.append(TranslatorTools.reformatName(parameter.getSimpleName().toString()));
            res.append(" : ");
            res.append(TranslatorTools.reformatName(parameterType.getSelfTranslation()));
            nbType++;
            // is comma necessary?
            if (nbType >= 1 && nbType < methodElement.getParameters().size()) res.append(", ");
        }
        return res.toString();
    }

    /**
     * Gives puml equivalent for returned type of specified class' method
     *
     * @param methodElement A cast element representing a method
     * @return String like ' : returnedType'
     */
    protected String getReturnType(ExecutableElement methodElement) {
        String res = "";
        PumlType type = new PumlType(methodElement.getReturnType());
        res = type.getSelfTranslation();
        return res;
    }
}
