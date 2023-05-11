package pumlFromJava.doclets.Options;

import jdk.javadoc.doclet.Doclet;

import java.util.List;

public class OutOption implements Doclet.Option {

    private String NameFile;

    @Override
    public int getArgumentCount() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Permet créer un fichier .puml";
    }

    @Override
    public Kind getKind() {
        return Kind.STANDARD;
    }

    @Override
    public List<String> getNames() {
        return List.of("-out");
    }

    @Override
    public String getParameters() {
        return "Chemin";
    }

    @Override
    public boolean process(String option, List<String> arguments) {
        NameFile = arguments.get(0);
        return true;
    }

    public String getFileName() {
        String res = "autogenfile.puml";
        if (NameFile != null && !NameFile.equals("")){
            res = NameFile;
        }
        return res;
    }
}
