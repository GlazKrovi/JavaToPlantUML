import java.util.spi.ToolProvider;

import pumlFromJava.generators.Help;

/*
 * A command line which work:
 * java -cp src/pumlFromJava/generators/Java2Puml.java "-private -sourcepath src -doclet pumlFromJava.doclets.PumlDoclet -d generates/pumls/ -g both -out exemple.puml test"
 *
 * */

public class App {
    public static void main(String[] args) {
        try{
            // show usages
            ToolProvider toolProvider = ToolProvider.findFirst("javadoc").get();
            //System.out.println(toolProvider.name());
            toolProvider.run(System.out, System.err, args);
        }
        catch (Exception e){
            Help help = new Help();
            help.print();
        }
        
    }
}
