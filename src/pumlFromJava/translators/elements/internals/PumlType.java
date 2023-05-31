package pumlFromJava.translators.elements.internals;

import pumlFromJava.translators.elements.tools.TranslatorTools;

import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

/**
 * Represents the puml equivalent of a java type (integer, string, etc.),
 * such as a local variable, a field or a method parameter
 */
public class PumlType {

    private final TypeMirror self;

    public PumlType(TypeMirror self) {
        this.self = self;

        // security
        if (self.getKind() == TypeKind.NULL || self.getKind() == TypeKind.NONE){
            throw new IllegalArgumentException();
        }
    }

    public String getSelfTranslation() {
        String res;
        // primitive
        if (TranslatorTools.isPrimitiveType(self)){
            res = this.translatePrimitive();
        }
        // others
        else{
            res = this.translateNonPrimitive();
        }
        return res;
    }

    private String translatePrimitive(){
        String res;
        String typeName = TranslatorTools.reformatName(this.self.toString().toLowerCase());
        switch (typeName) {
            case "int" -> res = "Integer";
            case "float" -> res = "Float";
            case "boolean" -> res = "Boolean";
            case "char" -> res = "Char";
            case "string" -> res = "String";
            case "null", "none", "void" -> res = "";
            default -> {
                res = typeName;
            }
        }
        return res;
    }

    private String translateNonPrimitive(){
        return TranslatorTools.reformatName(this.self.toString());
    }
}
