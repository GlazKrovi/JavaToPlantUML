package pumlFromJava.generators;

import java.util.spi.ToolProvider;

/*
 * A command line which work:
 * -private -sourcepath src -doclet pumlFromJava.doclets.PumlDoclet -d generates/pumls/ -g both -out exemple.puml western
 *
 * */


public class Java2Puml {
    public static void main(String[] args) {
        Help help = new Help();
        // show usages
        help.print();
        ToolProvider toolProvider = ToolProvider.findFirst("javadoc").get();
        //System.out.println(toolProvider.name());
        toolProvider.run(System.out, System.err, args);
    }
}
