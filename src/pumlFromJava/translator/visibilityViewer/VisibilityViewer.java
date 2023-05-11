package pumlFromJava.translator.visibilityViewer;

import javax.lang.model.element.AnnotationMirror;

public class VisibilityViewer {
    /**
     * Translate a annotation into Puml equivalent
     * @param annotation any annotationMirror from a class' attribute or method
     * @return + for public, - for private, ~ for protected
     */
    public String getVisibility(AnnotationMirror annotation){
        String res = "";
        if (annotation.getAnnotationType().toString().equals("public")){
            res = "+";
        }
        else if (annotation.getAnnotationType().toString().equals("private")){
            res = "-";
        }
        else if (annotation.getAnnotationType().toString().equals("protected")){
            res = "~";
        }
        return res;
    }
}
