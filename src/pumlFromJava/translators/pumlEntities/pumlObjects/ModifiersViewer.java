package pumlFromJava.translators.pumlEntities.pumlObjects;

import javax.lang.model.element.Modifier;
import java.util.Set;

public class ModifiersViewer {

    /**
     * Translate a annotation into Puml equivalent
     * @param visibility any annotationMirror from a class' attribute or method
     * @return + for public, - for private, ~ for protected
     */
    public String getStatic(Set<Modifier> visibility){
        String res = "";
        if (visibility.contains(Modifier.STATIC)){
            res = " {static} ";
        }
        return res;
    }

    public String getAbstract(Set<Modifier> visibility){
        String res = "";
        if (visibility.contains(Modifier.ABSTRACT)){
            res = " {abstract} ";
        }
        return res;
    }

    public String getFinal(Set<Modifier> visibility){
        String res = "";
        if (visibility.contains(Modifier.PROTECTED)){
            res = " {readonly} ";
        }
        return res;
    }
}
