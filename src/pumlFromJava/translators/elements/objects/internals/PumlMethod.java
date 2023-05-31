package pumlFromJava.translators.elements.objects.internals;

import pumlFromJava.translators.elements.objects.viewers.AnnotationsViewer;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;

/**
 * Represents any method of any class
 * with his visibility, modifiers, parameters
 * and eventual returned type
 */
public class PumlMethod extends PumlRawMethod {

    public PumlMethod(Element self) {
        super(self);
        if (self.getKind() != ElementKind.METHOD) throw new IllegalArgumentException();
    }

    public String getSelfTranslation() {
        StringBuilder res = new StringBuilder();
        AnnotationsViewer annotationsViewer = new AnnotationsViewer();
        ExecutableElement executableElement = (ExecutableElement) self;
        // visibility
        res.append(visibilityViewer.selfTranslate(self));
        // modifiers
        res.append(modifiersViewer.selfTranslate(self));
        // name + parameters
        res.append(" ")
                .append(annotationsViewer.selfTranslate(self)) // {@Override} {@myPersonalTag}
                .append(" ")
                .append(this.getSimplifiedName())
                .append("(")
                .append(this.getParameters(executableElement))
                .append(")");
        // manage returned type
        if (!this.getReturnType(executableElement).isEmpty()) {
            res.append(" : ")
                    .append(this.getReturnType(executableElement));
        }
        return res.toString();
    }
}
