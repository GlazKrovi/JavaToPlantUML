package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.type.TypeMirror;

// a ajouter au plantuml // todo
public class PumlType implements PumlEntity {
    public PumlType() {
    }

    public String getTranslation(Element element) {
        return translateType(identifyCollection(cutPackage(element.getSimpleName().toString())));
    }

    private static String translateType(String type) {
        String res;
        switch (type.toLowerCase()) {
            case "int" -> res = "Integer";
            case "float" -> res = "Float";
            case "boolean" -> res = "Boolean";
            case "char" -> res = "Char";
            default -> { res = type; }
        }
        return res;
    }

    public String getName(Element element) {
        return element.getSimpleName().toString();
    }


    public static String reformat(String name){
        return translateType(identifyCollection(cutPackage(name)));
    }

    /**
     * Cut the eventual packages names to save only the strict name of
     * an element
     *
     * @param name String like food.meal.rice, representing a element's name
     * @return Returns string like rice (cut from the complete specified name)
     */
    public static String cutPackage(String name) {
        int starting = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '.') {
                starting = i + 1;
            }
        }
        return name.substring(starting);
    }
    // a ajouter au plantuml // todo


    /**
     * As we've split the package, parameter names from the collection are returned with a '>' remaining at the end of their name.
     * This makes it easy to identify them! So that replace '>' with '[*]'
     *
     * @param parameterName String representing a parameter name
     * @return Returns string like 'parameterName[*]' if it's a collection,
     * 'parameterName' else
     */ // to improve !! // todo
    public static String identifyCollection(String parameterName){
        String res = parameterName;
        boolean flag = false;
        for (int i = 0; i < parameterName.length(); i++) {
            if (parameterName.charAt(i) == '>') {
                flag = true;
                break;
            }
        }
        if (flag){
            res = parameterName.substring(0, parameterName.length() - 1) + "[*]";
        }
        return res;
    }
    // a ajouter au plantuml // todo
}
