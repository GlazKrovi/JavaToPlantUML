package pumlFromJava.translators.pumlElements;

import pumlFromJava.translators.TranslatorTools;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

/**
 * Represents puml equivalent for a java type (integer, string, etc.)
 */
public class PumlType implements ElementTranslator {

    public String selfTranslate(Element element) {
        String res = "";
        if (element.getKind() == ElementKind.PARAMETER) {
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
        }
        return res;
    }
}
