package pumlFromJava.translators.elements.internals;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.TypeMirror;

public abstract class PumlInternal implements RawInternal {

    Element self;

    // children have to secure super() with (element.getKind() != ElementKind.SOMETHING)
    public PumlInternal(Element self) {
        this.self = self;
    }

    @Override
    public abstract String getContentTranslation();

    @Override
    public String getName() {
        return self.getSimpleName().toString();
    }

    @Override
    public ElementKind getKind() {
        return self.getKind();
    }

    @Override
    public TypeMirror getElementType() {
        return self.asType();
    }
}
