package pumlFromJava.doclets;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import pumlFromJava.diagrams.PumlACD;
import pumlFromJava.diagrams.PumlCCD;
import pumlFromJava.doclets.options.OutOption;
import pumlFromJava.doclets.options.PathOption;
import pumlFromJava.doclets.options.TypeOption;
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
                generateACD(environment);
                generateCCD(environment);
            } else if (oType.getType().equalsIgnoreCase("ccd")) {
                generateCCD(environment);
            } else if (oType.getType().equalsIgnoreCase("acd")) {
                generateACD(environment);
            } else {
                throw new IllegalArgumentException("Please specify if you want 'both', 'acd' or 'dcc' scheme");
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * Generate a file containing a CCD scheme in Puml language
     *
     * @param environment A correct javadoc environment
     */
    private void generateACD(DocletEnvironment environment) {
        PumlACD diagram = new PumlACD();
        Writer writer;
        // specific file's path and/or name
        if (oOut.getFileName() != null && !oOut.getNames().isEmpty() && oPath.getNames() != null && !oPath.getNames().equals("")) {
            writer = new Writer(oPath.getPath(), "ACD_" + oOut.getFileName());
        } else if (oOut.getFileName() != null && !oOut.getNames().isEmpty()) {
            writer = new Writer("ACD_" + oOut.getFileName());
        } else {
            writer = new Writer("ACD_gen.puml");
        }
        // open file
        writer.open();
        // writing what we want (a acd)
        writer.write(diagram.translateToScheme(environment));
        // close file
        writer.close();
    }

    /**
     * Generate a file containing a CCD scheme in Puml language
     *
     * @param environment A correct javadoc environment
     */
    private void generateCCD(DocletEnvironment environment) {
        PumlCCD diagram = new PumlCCD();
        Writer writer;
        // specific file's path and/or name
        if (oOut.getFileName() != null && !oOut.getNames().isEmpty() && oPath.getNames() != null && !oPath.getNames().isEmpty()) {
            writer = new Writer(oPath.getPath(), "CCD_" + oOut.getFileName());
        } else if (oOut.getFileName() != null && !oOut.getNames().isEmpty()) {
            writer = new Writer("CCD_" + oOut.getFileName());
        } else {
            writer = new Writer("CCD_gen.puml");
        }
        // open file
        writer.open();
        // writing what we want (a dcc)
        writer.write(diagram.translateToScheme(environment));
        // close file
        writer.close();
    }
}
