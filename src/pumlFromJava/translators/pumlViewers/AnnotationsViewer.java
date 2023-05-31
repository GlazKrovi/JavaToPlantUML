package pumlFromJava.translators.pumlViewers;

import pumlFromJava.translators.ElementTranslator;
import pumlFromJava.translators.TranslatorTools;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import java.util.List;

/**
 * Gets and translates the @Override annotations of a method
 * (or, more unusually, a class)
 */
public class AnnotationsViewer implements ElementTranslator {
    @Override
    public String selfTranslate(Element element) {
        StringBuilder res = new StringBuilder();
        List<? extends AnnotationMirror> annotations = element.getAnnotationMirrors();
        for (int i = 0; i < annotations.size(); i++) {
            res.append("{")
                    .append(TranslatorTools.cutPackage(annotations.get(i).getAnnotationType().toString()))
                    .append("}");
            if (annotations.size() > 1 && i < annotations.size() - 1) res.append(" ");
        }
        return res.toString();
    }
}
