package pumlFromJava.translators.elements.tools;

import javax.lang.model.type.TypeMirror;

/**
 * Some static functions to identify if specified element is a collection, to cut
 * the packages name and get only the strict name of an element, or also to
 * verify if element is a primitive or an out-of-language element
 */
public class TranslatorTools {

    /**
     * Associate cutPackage and identifyCollection :
     * cut the eventual packages names to save only the strict name of an element,
     * plus adds the collection symbol (i.e. [*]) if the specified name is a collection
     *
     * @param name String like food.meal.rice, representing a element's name
     * @return Returns string like 'Persons[*]' (from the complete specified name java.List< Society.Person >)
     */
    public static String reformatName(String name) {
        return identifyAsCollection(cutPackage(name));
    }

    /**
     * Cut the eventual packages names to save only the strict name of
     * an element adds the collection symbol (i.e. [*]) if the specified name is a collection
     *
     * @param name String representing a parameter or field name
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
     * @param parameterName String representing a parameter  or field name
     * @return Returns the passed name, plus the collection symbol (i.e. [*]) if the specified name is a collection,
     * or just the passed name otherwise
     */
    public static String identifyAsCollection(String parameterName) {
        /* * As we've split the package, parameter names from the collection are returned with a '>' remaining at the end of their name.
         * This makes it easy to identify them! So that replace '>' and '[' with '[*]' */
        String res = parameterName;
        int end = 0;
        for (int i = 0; i < parameterName.length(); i++) {
            if (parameterName.charAt(i) == '>' || parameterName.charAt(i) == '[') {
                end = i;
                break;
            }
        }
        if (end > 0) {
            res = parameterName.substring(0, end) + "[*]";
        }
        return res;
    }

    public static boolean isCollection(String parameterName) {
        boolean is = false;
        for (int i = 0; i < parameterName.length(); i++) {
            if (parameterName.charAt(i) == '>' || parameterName.charAt(i) == '[') {
                is = true;
                break;
            }
        }
        return is;
    }

    /**
     * Indicates if the element comes from the java language or not
     *
     * @param typeMirror The type of any element
     * @return Returns true if not, false otherwise
     */
    public static boolean isNotFromJava(TypeMirror typeMirror) {
        boolean isNotJavaOrJdk = false;
        String name = typeMirror.toString();
        // get the 3-4 firsts char
        if (name.length() >= 4) {
            String fiveChar = name.substring(0, 4);
            // starts with java. or javax ?
            if (fiveChar.equals("java") ||
                    fiveChar.equals("jdk.")) {
                isNotJavaOrJdk = true;
            }
        }
        return !isNotJavaOrJdk;
    }

    /**
     * Indicates if the type passed is primitive (int, float, void, etc) or not
     *
     * @param type TypeMirror from a class
     * @return Returns true if the type passed is primitive, false otherwise
     */
    public static boolean isPrimitiveType(TypeMirror type) {
        return type.getKind().isPrimitive() ||
                TranslatorTools.cutPackage(type.toString()).equalsIgnoreCase("string") ||
                type.toString().equalsIgnoreCase("void") ||
                type.toString().equalsIgnoreCase("null");
    }

    /**
     * Cut the strict name of an element to get only packages names
     *
     * @param fullName String representing a parameter or field name
     * @return Returns string like 'food.ingredients.' (cut from the complete specified name)
     */
    public static String obtainPackage(String fullName) {
        int ending = 0;
        for (int i = 0; i < fullName.length(); i++) {
            if (fullName.charAt(i) == '.') {
                ending = i + 1;
            }
        }
        return fullName.substring(0, ending);
    }

    /**
     * Cut eventual collections marks
     *
     * @param name String representing a parameter or field name
     * @return Returns string like 'something' (cut from the complete specified name somethings[])
     */
    public static String cutCollection(String name) {
        return name.replaceAll("[<>\\[\\]]", "");
    }
}
