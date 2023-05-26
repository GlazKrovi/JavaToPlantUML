package pumlFromJava.translators.pumlViewers;

import pumlFromJava.translators.ElementTranslator;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import java.util.Set;

/**
 * Verify if element is static, abstract and/or final and translate presents
 * annotation into their Puml equivalent
 */
public class ModifiersViewer implements ElementTranslator {

    @Override
    public String selfTranslate(Element element) {
        String res = "";
        if (element.getKind() != ElementKind.CLASS) { // avoid redundant abstract
            Set<Modifier> modifiers = element.getModifiers();
            res = getStatic(modifiers) + getAbstract(modifiers) + getFinal(modifiers);
        }
        return res;
    }

    private String getStatic(Set<Modifier> modifiers) {
        String res = "";
        if (modifiers.contains(Modifier.STATIC)) {
            res = " {static} ";
        }
        return res;
    }

    private String getAbstract(Set<Modifier> modifiers) {
        String res = "";
        if (modifiers.contains(Modifier.ABSTRACT)) {
            res = " {abstract} ";
        }
        return res;
    }

    private String getFinal(Set<Modifier> modifiers) {
        String res = "";
        if (modifiers.contains(Modifier.PROTECTED)) {
            res = " {readonly} ";
        }
        return res;
    }
}
