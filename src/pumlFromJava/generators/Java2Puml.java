package pumlFromJava.generators;

import java.util.spi.ToolProvider;

public class Java2Puml
{

    public static void main(String[] args)
    {
        ToolProvider toolProvider = ToolProvider.findFirst("javadoc").get();
        System.out.println(toolProvider.name());

    /*
    javadoc -private -sourcepath <src> -doclet pumlFromJava.FirstDoclet -docletpath out/production/<projet>
      <package> ... <fichiers>

    Exemple :
    javadoc -private -sourcepath "C:\Users\ferna\Documents\sae-doo-fernandes-marsault\Westerns\src\western" -doclet pumlFromJava.FirstDoclet -docletpath out/production/sae-doo-fernandes-marsault
    */
        toolProvider.run(System.out, System.err, args);
    }
}
