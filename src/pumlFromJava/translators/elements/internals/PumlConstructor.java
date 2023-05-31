package pumlFromJava.translators.elements.internals;

import pumlFromJava.translators.elements.tools.TranslatorTools;
import pumlFromJava.translators.viewers.AnnotationsViewer;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;

/**
 * Represents the constructor of any class
 * with his visibility, modifiers, parameters
 * and eventual returned type
 */
public class PumlConstructor extends PumlRawMethod {

    public PumlConstructor(Element self) {
        super(self);
        if (self.getKind() != ElementKind.CONSTRUCTOR) throw new IllegalArgumentException();
    }

    public String getSelfTranslation() {
        return visibilityViewer.selfTranslate(self) +
                " <<create>> " +
                TranslatorTools.cutPackage(self.getEnclosingElement().toString()) +
                "(" +
                this.getParameters((ExecutableElement) self) +
                ")";
    }
}