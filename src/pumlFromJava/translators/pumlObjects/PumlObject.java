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
}
