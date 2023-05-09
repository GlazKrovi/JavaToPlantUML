package pumlFromJava;

import jdk.javadoc.doclet.Doclet;

import java.util.List;

public class PathOption implements Doclet.Option {

    private String path;

    @Override
    public int getArgumentCount() {
        return 1;
    }

    @Override
    public String getDescription() {
        return "Permet de dire ou on mettre le fichier";
    }

    @Override
    public Kind getKind() {
        return Kind.STANDARD;
    }

    @Override
    public List<String> getNames() {
        return List.of("-d");
    }

    @Override
    public String getParameters() {
        return "Chemin2";
    }

    @Override
    public boolean process(String option, List<String> arguments) {
        path = arguments.get(0);
        return true;
    }

    public String getPath() {
        String res = "./";
        if (path != null && !path.equals("")){
            res = path;
        }
        return res;
    }
}
