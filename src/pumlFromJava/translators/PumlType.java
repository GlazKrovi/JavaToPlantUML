package pumlFromJava.translators;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;

/**
 * Represents puml equivalent for a java type (integer, string, etc.)
 */
public class PumlType implements ElementTranslator {
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
