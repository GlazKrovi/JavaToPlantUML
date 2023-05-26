package pumlFromJava.translators.pumlElements.pumlObjects;

import pumlFromJava.translators.TranslatorTools;

import javax.lang.model.element.Element;

/**
 * Translator for any java package, class, enum or interface
 */
public abstract class PumlObject implements ObjectTranslator {

    public abstract String selfTranslate(Element element) ;

    public String getFullName(Element element) {
        return element.toString();
    }

    public String getSimplifiedName(Element element) {
        return TranslatorTools.reformatName(this.getFullName(element));
    }

    public abstract String contentTranslate(Element element);

    public String open() {
        return " {";
    }

    public String close() {
        return "\n}";
    }
}
