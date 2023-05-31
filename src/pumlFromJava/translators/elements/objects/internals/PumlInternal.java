package pumlFromJava.translators.elements.objects.internals;

import pumlFromJava.translators.elements.objects.viewers.ModifiersViewer;
import pumlFromJava.translators.elements.objects.viewers.VisibilityViewer;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.TypeMirror;

/**
 * Represents a specific content of a PumlRawObject,
 * like method or field
 */
public abstract class PumlInternal implements RawInternal {

    protected final VisibilityViewer visibilityViewer = new VisibilityViewer();
    protected final ModifiersViewer modifiersViewer = new ModifiersViewer();
    protected Element self;

    // children have to secure super() with (element.getKind() != ElementKind.SOMETHING)
    public PumlInternal(Element self) {
        this.self = self;
    }

    @Override
    public abstract String getSelfTranslation();

    @Override
    public String getName() {
        return self.getSimpleName().toString();
    }

    @Override
    public ElementKind getKind() {
        return self.getKind();
    }

    @Override
    public TypeMirror getType() {
        return self.asType();
    }
}
