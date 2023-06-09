package pumlFromJava.translators.elements.objects.viewers;

import pumlFromJava.translators.elements.objects.PumlRawObject;

import javax.lang.model.element.Element;
import javax.lang.model.element.Modifier;
import java.util.Set;

/**
 * Translate an annotation into Puml equivalent,
 * like + for public, - for private, ~ for protected
 */
public class VisibilityViewer implements PumlRawObject {
    @Override
    public String selfTranslate(Element element) {
        String res = "";
        Set<Modifier> visibility = element.getModifiers();
        if (visibility.contains(Modifier.PUBLIC)) {
            res = "+";
        } else if (visibility.contains(Modifier.PRIVATE)) {
            res = "-";
        } else if (visibility.contains(Modifier.PROTECTED)) {
            res = "#";
        } else if (visibility.contains(Modifier.DEFAULT)) {
            res = "+";
        } else {
            res = "~";
        }
        return res;
    }

}
