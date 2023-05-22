package pumlFromJava.translators.pumlObjects;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;

public class PumlPackage extends PumlObject {
    public PumlPackage() {
    }

    public String getName(Element element) {
        StringBuilder res = new StringBuilder();
        if (element.getKind() == ElementKind.PACKAGE) {
            res.append("package ").append(element.getSimpleName().toString());
        }
        return res.toString();
    }

    // todo
    // returns the name of each element into
    public String getContent(Element element) { return ""; }

}
