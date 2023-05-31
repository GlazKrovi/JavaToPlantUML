package pumlFromJava.translators.elements.internals;

import pumlFromJava.translators.elements.tools.TranslatorTools;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PumlField extends PumlInternal {

    public PumlField(Element self) {
        super(self);
        if (self.getKind() != ElementKind.FIELD) throw new IllegalArgumentException();
    }

    @Override
    public String getSelfTranslation() {
        StringBuilder res = new StringBuilder();
        PumlType type = new PumlType(self.asType());
        if (TranslatorTools.isPrimitiveType(self.asType())) {
            // visibility
            res.append(visibilityViewer.selfTranslate(self));
            // modifiers
            res.append(modifiersViewer.selfTranslate(self));
            // name + type
            res.append(this.getName());
            res.append(" : ");
            res.append(type.getSelfTranslation());
        }
        return res.toString();
    }
}
