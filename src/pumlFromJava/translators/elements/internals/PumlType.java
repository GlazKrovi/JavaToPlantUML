package pumlFromJava.translators.elements.internals;

import pumlFromJava.translators.elements.rawObjects.PumlRawObject;
import pumlFromJava.translators.elements.tools.TranslatorTools;

import javax.lang.model.element.Element;

/**
 * Represents puml equivalent for a java type (integer, string, etc.)
 */
public class PumlType implements PumlRawObject {
    @Override
    public String selfTranslate(Element element) {
        String res;
        String type = TranslatorTools.reformatName(element.toString());
        switch (type.toLowerCase()) {
            case "int" -> res = "Integer";
            case "float" -> res = "Float";
            case "boolean" -> res = "Boolean";
            case "char" -> res = "Char";
            default -> {
                res = type;
            }
        }
        return res;
    }
}
