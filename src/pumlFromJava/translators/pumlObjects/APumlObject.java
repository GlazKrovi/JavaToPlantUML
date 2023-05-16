package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;

/**
 * Translator for any java package, class, enum or interface
 */
public abstract class APumlObject implements IPumlObject {

    private String name;
    
    public abstract String getName(Element element);

    public abstract String getContent(Element element);
    public String getTranslation(Element element){
        return getName(element) + // name
                // getInheritance(element) + // implements/extends smt
                open() + // {
                getLineBreaker() +
                getContent(element) + // -field, +method
                close() + // }
                getLineBreaker();
    }
}
