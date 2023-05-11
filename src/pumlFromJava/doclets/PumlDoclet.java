package pumlFromJava.doclets;


import pumlFromJava.diagrams.PumlDiagram;
import pumlFromJava.translator.pumlMarker.Marker;
import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import pumlFromJava.doclets.Options.OutOption;
import pumlFromJava.doclets.Options.PathOption;
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
    @Override
    public void init(Locale locale, Reporter reporter) {

    }

    @Override
    public String getName() {
        return getClass().getSimpleName();
    }

    @Override
    public Set<? extends Option> getSupportedOptions() {
        Option[] options = {oPath, oOut};
        return Set.of(options);
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latest();
    }

    @Override
    public boolean run(DocletEnvironment environment) {
        Marker translater = new Marker();
        Element[] elements = environment.getIncludedElements().toArray(new Element[0]);
        Writer writer;
        // options
        if (oOut != null    // specific path and file name
                && oOut.getFileName() != null
                && !oOut.getNames().equals("")
                && oPath != null
                && oPath.getNames() != null
                && !oPath.getNames().equals(""))
        {
            writer = new Writer(oPath.getPath(), oOut.getFileName());
        }
        else if (oOut != null   // just specific file name
                && oOut.getFileName() != null
                && !oOut.getNames().equals(""))
        {
            writer = new Writer(oOut.getFileName());
        }
        else
        {
            writer =  new Writer("gen.puml");
        }
        // open file
        writer.open();

        // WRITING WHAT YOU WANT HERE
        PumlDiagram diagram = new PumlDiagram();
        writer.write(diagram.getACD(environment));

        // close file
        writer.close();
        return true;
    }
}

/*

        for (Element includeEl : environment.getIncludedElements())
        {
            for (Element anotherEl : includeEl.getEnclosedElements()){
                System.out.println(anotherEl.getKind().toString());
            }
        }
        return true;
 */
