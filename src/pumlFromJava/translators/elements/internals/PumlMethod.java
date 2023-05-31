package pumlFromJava.translators.elements.internals;

import pumlFromJava.translators.elements.Nameable;
import pumlFromJava.translators.elements.rawObjects.PumlRawObject;
import pumlFromJava.translators.elements.tools.TranslatorTools;
import pumlFromJava.translators.viewers.AnnotationsViewer;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;


public class PumlMethod implements PumlRawObject, Nameable {
    /**
     * Translate what is inside the method Element (parameters and returned value type)
     *
     * @param element a method element
     * @return String like (paramName1 : paramType1, paramName2 : paramType2) : returnedValueType
     */
    @Override
    public String selfTranslate(Element element) {
        StringBuilder res = new StringBuilder();
        AnnotationsViewer annotationsViewer = new AnnotationsViewer();
        if (element.getKind() == ElementKind.METHOD) {
            ExecutableElement executableElement = (ExecutableElement) element;
            res.append(" ")
                    .append(annotationsViewer.selfTranslate(element)) // {@Override} {@myPersonalTag}
                    .append(" ")
                    .append(this.getSimplifiedName(element))
                    .append("(")
                    .append(TranslatorTools.reformatName(this.getParameters(executableElement)))
                    .append(")");
            if (!this.getReturnType(executableElement).isEmpty()) {
                res.append(" : ");
                res.append(this.getReturnType(executableElement));
            }
        } else if (element.getKind() == ElementKind.CONSTRUCTOR) {
            ExecutableElement executableElement = (ExecutableElement) element;
            res.append(TranslatorTools.cutPackage(element.getEnclosingElement().toString()));
            res.append("(");
            res.append(this.getParameters(executableElement));
            res.append(")");
        }
        return res.toString();
    }

    @Override
    public String getFullName(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.METHOD) {
            res = element.getSimpleName().toString();
        } else if (element.getKind() == ElementKind.CONSTRUCTOR) {
            res = element.getEnclosingElement().getSimpleName().toString();
        }
        return res;
    }

    @Override
    public String getSimplifiedName(Element element) {
        return TranslatorTools.cutPackage(getFullName(element));
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
            // managing classes used by another
            res.append(TranslatorTools.reformatName(parameter.getSimpleName().toString()));
            res.append(" : ");
            res.append(TranslatorTools.reformatName(parameter.asType().toString()));
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
    private String getReturnType(ExecutableElement methodElement) {
        String res = "";
        if (!methodElement.getReturnType().toString().equals("void")) {
            res = TranslatorTools.reformatName(methodElement.getReturnType().toString());
        }
        return res;
    }
}
