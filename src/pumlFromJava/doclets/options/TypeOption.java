package pumlFromJava.doclets.options;

import jdk.javadoc.doclet.Doclet;

import java.util.List;

public class TypeOption implements Doclet.Option {

    private String type;

    public TypeOption() {
    }

    @Override
    public int getArgumentCount() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "regarde si DCC et/ou DCA";
    }

    @Override
    public Kind getKind() {
        return Kind.STANDARD;
    }

    @Override
    public List<String> getNames() {
        return List.of("-g");
    }

    @Override
    public String getParameters() {
        return "Which scheme";
    }

    @Override
    public boolean process(String option, List<String> arguments) {
        type = arguments.get(0);
        return true;
    }

    public String getType() {
        String res = "";
        if (type != null && !type.equals("")) {
            res = type;
        }
        return res;
    }
}
