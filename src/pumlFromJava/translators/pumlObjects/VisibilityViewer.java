package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Modifier;
import java.util.Set;

public class VisibilityViewer {

    /**
     * Translate a annotation into Puml equivalent
     * @param visibility any annotationMirror from a class' attribute or method
     * @return + for public, - for private, ~ for protected
     */
    public String getVisibility(Set<Modifier> visibility){
        String res = "";
        if (visibility.contains(Modifier.PUBLIC)){
            res = "+";
        }
        else if (visibility.contains(Modifier.PRIVATE)){
            res = "-";
        }
        else if (visibility.contains(Modifier.PROTECTED)){
            res = "#";
        }
        return res;
    }
}
