package pumlFromJava;

import javax.lang.model.element.Element;

public class PumlTranslater {
    // TRANSLATE
    /**
     * Returns '@startUml' and theme into file
     */
    public static String mark_umlStart(){
        return ("@startuml\n" +
                "!theme reddress-darkgreen\n"); // "skinparam style strictuml\n\n"
    }

    /**
     * Returns '@enduml' into file
     */
    public static String mark_umlEnd(){
        return("\n@enduml\n");
    }

    /**
     * Translate Jave file type into Uml Object-type
     * (Ex : class Test)
     * @param elm
     * @return Returns string of type "file-type name" for Uml
     */
    public static String translate_umlObject(Element elm) {
        String res = "";
        String elmName = elm.getSimpleName().toString();
        String elmExtension = "";
        boolean valid = false;
        // verify extension
        if (elmName.length() > 4){
            elm.getSimpleName().toString().substring(elmName.length() - 4);
        }
        // translate
        if (elm.getKind().isClass() || elmExtension.equals("class") ){
            res = "class ";
            valid = true;
        }
        else if(elm.getKind().isInterface() || elmExtension.equals("interface")){
            res = "interface ";
            valid = true;
        }
        else if (elmExtension.equals("enum")){ // enum
            res = "enum ";
            valid = true;
        }
        // if something was translated:
        if (valid){
            res += elm.getSimpleName();
            res += " {}";
        }
        return res;
    }
}
