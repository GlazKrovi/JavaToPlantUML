import java.util.spi.ToolProvider;

/*
 * A command line which work:
 * java -cp src/pumlFromJava/generators/Java2Puml.java -private -sourcepath src -doclet pumlFromJava.doclets.PumlDoclet -d generates/pumls/ -g both -out exemple.puml test"
 *
 * */

public class App {
    public static void main(String[] args) {
        try{
            ToolProvider toolProvider = ToolProvider.findFirst("javadoc").get();
            toolProvider.run(System.out, System.err, args);
        }
        catch (Exception e){
            // show usages
            Help help = new Help();
            help.print();
        }
        
    }
}
