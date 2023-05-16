package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;
import java.util.List;

public class PumlInheritance {

    /**
     * Translates what the element inherits from (implementation or extension)
     *
     * @param element an element like class, enum or interface
     * @return Puml equivalents for smt extends/inherit Asmt/Ismt
     */
    public String get(Element element) { // Todo: don't work for interface
        StringBuilder res = new StringBuilder();
        int nbToWrite = 0;
        if (element != null) {
            if (element.getKind() == ElementKind.INTERFACE) {
                List<? extends TypeMirror> superInterfaces = ((TypeElement) element).getInterfaces();
                if (!superInterfaces.isEmpty()) {
                    for (int i = 0; i < superInterfaces.size(); i++) {
                        TypeMirror actSupInterface = superInterfaces.get(i);
                        // is a 'personal' interface, not from language
                        if (!isFromJava(actSupInterface)) {
                            res.append(actSupInterface);
                            if (i < superInterfaces.size() - 1) {
                                res.append(", ");
                            }
                            nbToWrite++; // will be written
                        }
                    }
                    if (nbToWrite>0) res.append(" extends ");
                }
            } else if (element.getKind() == ElementKind.CLASS) {
                TypeMirror superClass = ((TypeElement) element).getSuperclass();
                // is a 'personal' class, not from language
                if (superClass != null && !isFromJava(superClass)) {
                    res.append(" extends ").append(superClass);
                }
                List<? extends TypeMirror> implementedInterfaces = ((TypeElement) element).getInterfaces();
                if (!implementedInterfaces.isEmpty()) {
                    for (int i=0;i<implementedInterfaces.size()-1;i++) {
                        TypeMirror actImpInterface = implementedInterfaces.get(i);
                        // is a 'personal' interface, not from language
                        if (!isFromJava(actImpInterface)) {
                            res.append(actImpInterface);
                            nbToWrite++; // will be written
                            // add comma
                            if (nbToWrite > 1) {
                                res.append(", ");
                            }
                        }
                    }
                }
            }
        }
        return res.toString();
    }

    private boolean isFromJava(TypeMirror typeMirror){
        boolean isNotJavaOrJdk = false;
        String name = typeMirror.toString();
        // get the 3-4 firsts char
        if (name.length() >= 5) {
            String fiveChar = name.substring(0, 5);
            // starts with java. or javax ?
            if (fiveChar.equals("java.") || fiveChar.equals("javax")){
                isNotJavaOrJdk = true;
            }
        }
        return isNotJavaOrJdk;


    }

}
