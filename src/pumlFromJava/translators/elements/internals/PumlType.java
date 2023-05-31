package pumlFromJava.translators.elements.internals;

import pumlFromJava.translators.elements.tools.TranslatorTools;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents puml equivalent for a java type (integer, string, etc.)
 */
public class PumlType extends PumlInternal {

    // devra être utiliser dans méthode !! methode etant elle-meme PumlInternal // todo
    public PumlType(Element self) {
        super(self);

        // security
        List<ElementKind> corrects = new ArrayList<>();
        corrects.add(ElementKind.PARAMETER);
        corrects.add(ElementKind.LOCAL_VARIABLE);
        corrects.add(ElementKind.FIELD);
        if (!corrects.contains(self.getKind())) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String getContentTranslation() {
        String res;
        String type = TranslatorTools.reformatName(self.toString());
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
