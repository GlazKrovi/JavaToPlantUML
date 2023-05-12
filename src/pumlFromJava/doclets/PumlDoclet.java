package pumlFromJava.doclets;


import pumlFromJava.diagrams.PumlDCA;
import pumlFromJava.diagrams.PumlDCC;
import pumlFromJava.translator.pumlMarker.Marker;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import pumlFromJava.doclets.options.OutOption;
import pumlFromJava.doclets.options.PathOption;
import pumlFromJava.doclets.Options.TypeOption;
import pumlFromJava.writer.Writer;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import java.util.Locale;
import java.util.Set;

/* Directories management is not yet complete
 * A command line which work:
 * -private -sourcepath .\src -doclet pumlFromJava.PumlDoclet western -out Western.puml -d .\tests\
 * */

public class PumlDoclet implements Doclet {

    PathOption oPath = new PathOption();
    OutOption oOut = new OutOption();

    TypeOption oType = new TypeOption();

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
            }
            else if (oType.getType().equalsIgnoreCase("dcc")) {
                generateDCC(environment);
            }
            else if (oType.getType().equalsIgnoreCase("dca")) {
                generateDCA(environment);
            }
            else {
                throw new IllegalArgumentException("Please specify if you want 'both', 'dca' or 'dcc' scheme");
            }
        }
        catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        }
        return true;
    }


    private boolean generateDCA(DocletEnvironment environment){
        Marker translater = new Marker();
        Element[] elements = environment.getIncludedElements().toArray(new Element[0]);
        PumlDCA diagram = new PumlDCA();
        Writer writer;
        // options
        if (oOut != null    // specific path and file name
                && oOut.getFileName() != null
                && !oOut.getNames().equals("")
                && oPath != null
                && oPath.getNames() != null
                && !oPath.getNames().equals(""))
        {
            writer = new Writer(oPath.getPath(), "DCA_" + oOut.getFileName());
        }
        else if (oOut != null   // just specific file name
                && oOut.getFileName() != null
                && !oOut.getNames().equals(""))
        {
            writer = new Writer("DCA_" + oOut.getFileName());
        }
        else
        {
            writer =  new Writer("DCA_gen.puml");
        }
        // open file
        writer.open();
        // WRITING WHAT YOU WANT HERE
        writer.write(diagram.getScheme(environment));
        // close file
        writer.close();
        return true;
    }

    private boolean generateDCC(DocletEnvironment environment){
        Marker translater = new Marker();
        Element[] elements = environment.getIncludedElements().toArray(new Element[0]);
        PumlDCC diagram = new PumlDCC();
        Writer writer;
        // options
        if (oOut != null    // specific path and file name
                && oOut.getFileName() != null
                && !oOut.getNames().equals("")
                && oPath != null
                && oPath.getNames() != null
                && !oPath.getNames().equals(""))
        {
            writer = new Writer(oPath.getPath(), "DCC_" + oOut.getFileName());
        }
        else if (oOut != null   // just specific file name
                && oOut.getFileName() != null
                && !oOut.getNames().equals(""))
        {
            writer = new Writer("DCC_" + oOut.getFileName());
        }
        else
        {
            writer =  new Writer("DCC_gen.puml");
        }
        // open file
        writer.open();
        // WRITING WHAT YOU WANT HERE
        writer.write(diagram.getScheme(environment));
        // close file
        writer.close();
        return true;
    }
}
