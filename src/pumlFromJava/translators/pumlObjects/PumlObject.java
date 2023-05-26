package pumlFromJava.translators.pumlObjects;

import pumlFromJava.translators.TranslatorTools;

import javax.lang.model.element.Element;

/**
 * Translator for any java package, class, enum or interface
 */
public abstract class PumlObject implements ObjectTranslator {

    @Override
    public abstract String selfTranslate(Element element) ;

    @Override
    public String getFullName(Element element) {
        return element.toString();
    }

    @Override
    public String getSimplifiedName(Element element) {
        return TranslatorTools.reformatName(this.getFullName(element));
    }

    public abstract String contentTranslate(Element element);

    @Override
    public String open() {
        return " {";
    }

    @Override
    public String close() {
        return "\n}";
    }
}
