package pumlFromJava.translators.visibilityViewer;

import javax.lang.model.element.AnnotationMirror;

public interface IVisibilityWriter {

    /**
     * Translate a annotation into Puml equivalent
     * @param annotation any annotationMirror from a class' attribute or method
     * @return + for public, - for private, ~ for protected
     */
    public String getVisibility(AnnotationMirror annotation);
}
