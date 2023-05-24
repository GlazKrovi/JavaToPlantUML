package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.type.TypeMirror;

/**
 * Translator for any java package, class, enum or interface
 */
public abstract class PumlObject implements PumlObjectSpecies {

    public abstract String getName(Element element);

    public abstract String getContent(Element element);

    public String getTranslation(Element element) {
        return getName(element) + // name
                open() + // {
                getLineBreaker() +
                getContent(element) + // -field, +method
                close() + // }
                getLineBreaker();
    }

    public String open(){ return " {"; }

    public String close() { return "\n}"; }

    /**
     * Indicates if the element comes from the java language or not
     *
     * @param typeMirror The type of any element
     * @return Returns true if not, false otherwise
     */
    protected boolean isNotFromJava(TypeMirror typeMirror) {
        boolean isNotJavaOrJdk = false;
        String name = typeMirror.toString();
        // get the 3-4 firsts char
        if (name.length() >= 5) {
            String fiveChar = name.substring(0, 5);
            // starts with java. or javax ?
            if (fiveChar.equals("java.") || fiveChar.equals("javax")) {
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
    protected boolean isPrimitiveType(TypeMirror type) {
        return type.getKind().isPrimitive() || type.toString().equals("java.lang.String");
    }

    /**
     * Gives a line break
     *
     * @return Returns "\n"
     */
    public String getLineBreaker() {
        return "\n";
    }

    /**
     * Cut the eventual packages names to save only the strict name of
     * an element
     *
     * @param name String like food.meal.rice, representing a element's name
     * @return Returns string like rice (cut from the complete specified name)
     */
    protected String cutPackage(String name) {
        int starting = 0;
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) == '.') {
                starting = i + 1;
            }
        }
        return name.substring(starting);
    }
    // todo
    // a ajouter au plantuml


    /**
     * As we've split the package, parameter names from the collection are returned with a '>' remaining at the end of their name.
     * This makes it easy to identify them! So that replace '>' with '[*]'
     *
     * @param parameterName String representing a parameter name
     * @return Returns string like 'parameterName[*]' if it's a collection,
     * 'parameterName' else
     */ // to improve !! // todo
    protected String identifyCollection(String parameterName){
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
    // todo
    // a ajouter au plantuml
}
