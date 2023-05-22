package pumlFromJava.doclets;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import pumlFromJava.diagrams.PumlDCA;
import pumlFromJava.diagrams.PumlDCC;
import pumlFromJava.doclets.options.TypeOption;
import pumlFromJava.doclets.options.OutOption;
import pumlFromJava.doclets.options.PathOption;
import pumlFromJava.writers.Writer;
import javax.lang.model.SourceVersion;
import java.util.Locale;
import java.util.Set;

/* Directories management is not yet complete
 * A command line which work:
 * -private -sourcepath src -doclet pumlFromJava.doclets.PumlDoclet -d generates/pumls/ -g both -out exemple.puml western
 * */

public class PumlDoclet implements Doclet {

    private final PathOption oPath = new PathOption();
    private final OutOption oOut = new OutOption();

    private final TypeOption oType = new TypeOption();

    @Override
    public void init(Locale locale, Reporter reporter) {

    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Set<? extends Option> getSupportedOptions() {
        Option[] options = {oPath, oOut, oType};
        return Set.of(options);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean run(DocletEnvironment environment) {
        try {
            if (oType.getType().equalsIgnoreCase("both")) {
                generateDCA(environment);
                generateDCC(environment);
            } else if (oType.getType().equalsIgnoreCase("dcc")) {
                generateDCC(environment);
            } else if (oType.getType().equalsIgnoreCase("dca")) {
                generateDCA(environment);
            } else {
                throw new IllegalArgumentException("Please specify if you want 'both', 'dca' or 'dcc' scheme");
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * Generate a file containing a DCA scheme in Puml language
     * @param environment A correct javadoc environment
     */
    private void generateDCA(DocletEnvironment environment) {
        PumlDCA diagram = new PumlDCA();
        Writer writer;
        // specific file's path and/or name
        if (oOut.getFileName() != null && !oOut.getNames().isEmpty() && oPath.getNames() != null && !oPath.getNames().equals("")) {
            writer = new Writer(oPath.getPath(), "DCA_" + oOut.getFileName());
        } else if (oOut.getFileName() != null && !oOut.getNames().isEmpty()) {
            writer = new Writer("DCA_" + oOut.getFileName());
        } else {
            writer = new Writer("DCA_gen.puml");
        }
        // open file
        writer.open();
        // writing what we want (a dca)
        writer.write(diagram.getScheme(environment));
        // close file
        writer.close();
    }

    /**
     * Generate a file containing a DCC scheme in Puml language
     * @param environment A correct javadoc environment
     */
    private void generateDCC(DocletEnvironment environment) {
        PumlDCC diagram = new PumlDCC();
        Writer writer;
        // specific file's path and/or name
        if (oOut.getFileName() != null && !oOut.getNames().isEmpty() && oPath.getNames() != null && !oPath.getNames().isEmpty()) {
            writer = new Writer(oPath.getPath(), "DCC_" + oOut.getFileName());
        } else if (oOut.getFileName() != null && !oOut.getNames().isEmpty()) {
            writer = new Writer("DCC_" + oOut.getFileName());
        } else {
            writer = new Writer("DCC_gen.puml");
        }
        // open file
        writer.open();
        // writing what we want (a dcc)
        writer.write(diagram.getScheme(environment));
        // close file
        writer.close();
    }
}
