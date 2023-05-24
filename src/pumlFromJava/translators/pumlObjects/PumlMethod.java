package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;

public class PumlMethod extends PumlObject {
    public PumlMethod() {
    }

    public String getName(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.METHOD) {
            res = element.getSimpleName().toString();
        }
        return res;
    }

    /**
     * Translate what is inside the method Element (parameters and returned value type)
     *
     * @param element a method element
     * @return String like (paramName1 : paramType1, paramName2 : paramType2) : returnedValueType
     */
    public String getContent(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.METHOD) {
            ExecutableElement executableElement = (ExecutableElement) element;
            res.append("(");
            res.append(this.getParameters(executableElement));
            res.append(")");
            if (!this.getReturnType(executableElement).isEmpty()) {
                res.append(" : ");
                res.append(this.getReturnType(executableElement));
            }
        }
        return res.toString();
    }

    /**
     * Translate the name (like class, enum or interface)
     * and what is inside the method element (like parameters, their type and method's returned type)
     *
     * @param element a method element
     * @return String like 'openFile(path : String, mode : Mode) : Boolean
     */
    public String getTranslation(Element element) {
        return this.getName(element) + this.getContent(element);
    }

    /**
     * Gives puml equivalent for parameters of specified class' method
     *
     * @param methodElement A cast element representing a method
     * @return String like 'parameter1 : parameterType1, parameter2 : parameterType2'
     */
    private String getParameters(ExecutableElement methodElement) {
        StringBuilder res = new StringBuilder();
        int nbType = 0;
        for (VariableElement parameter : methodElement.getParameters()) {
            res.append(identifyCollection(cutPackage(parameter.getSimpleName().toString())));
            res.append(" : ");
            res.append(identifyCollection(cutPackage(parameter.asType().toString())));
            nbType++;
            // is comma necessary?
            if (nbType > 1) res.append(", ");
        }
        return res.toString();
    }

    /**
     * Gives puml equivalent for returned type of specified class' method
     *
     * @param methodElement A cast element representing a method
     * @return String like ' : returnedType'
     */
    private String getReturnType(ExecutableElement methodElement) {
        String res = "";
        if (!methodElement.getReturnType().toString().equals("void")) {
            res = identifyCollection(cutPackage(methodElement.getReturnType().toString()));
        }
        return res;
    }
}
