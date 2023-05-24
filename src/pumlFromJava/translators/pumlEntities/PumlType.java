package pumlFromJava.translators.pumlEntities;

import javax.lang.model.element.Element;

// a ajouter au plantuml // todo
public class PumlType {
    public PumlType() {
    }

    private static String translateType(String type) {
        String res;
        switch (type.toLowerCase()) {
            case "int" -> res = "Integer";
            case "float" -> res = "Float";
            case "boolean" -> res = "Boolean";
            case "char" -> res = "Char";
            default -> {
                res = type;
            }
        }
        return res;
    }

    public static String reformat(String name) {
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

    /**
     * Adds the collection symbol (i.e. [*]) if the specified name is a collection
     *
     * @param parameterName String representing a parameter name
     * @return Returns the passed name, plus the collection symbol (i.e. [*]) if the specified name is a collection,
     * or just the passed name otherwise
     */
    public static String identifyCollection(String parameterName) {
        /* * As we've split the package, parameter names from the collection are returned with a '>' remaining at the end of their name.
        * This makes it easy to identify them! So that replace '>' and '|' with '[*]' */
        String res = parameterName;
        int end = 0;
        for (int i = 0; i < parameterName.length(); i++) {
            if (parameterName.charAt(i) == '>' || parameterName.charAt(i) == '[' ) {
                end = i;
                break;
            }
        }
        if (end > 0) {
            res = parameterName.substring(0, end) + "[*]";
        }
        return res;
    }
}
