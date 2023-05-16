package pumlFromJava.translators.visibilityViewer;

import javax.lang.model.element.AnnotationMirror;

public class VisibilityViewer implements IVisibilityWriter {
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
