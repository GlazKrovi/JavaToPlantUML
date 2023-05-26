package pumlFromJava.generators;

import java.util.spi.ToolProvider;

/*
    javadoc -private -sourcepath <src> -doclet pumlFromJava.FirstDoclet -docletpath out/production/<projet>
      <package> ... <fichiers>
*/

public class Java2Puml {

    public static void main(String[] args) {
        ToolProvider toolProvider = ToolProvider.findFirst("javadoc").get();
        System.out.println(toolProvider.name());
        toolProvider.run(System.out, System.err, args);
    }
}
