package pumlFromJava;


import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;

import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import java.util.Locale;
import java.util.Set;

/* Directories management is not yet complete */

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
        Element[] elements = environment.getIncludedElements().toArray(new Element[0]);
        Writer writer;
        // options
        if (oOut != null
                && oOut.getFileName() != null
                && !oOut.getNames().equals("")){
            writer = new Writer(oOut.getFileName());
        }
        /* TODO
        else if (oOut != null && oOut.equals("")){ // just specific file name
            writer = new Writer(oOut.getFileName());
        }
         */
        else{
            writer =  new Writer("gen.puml");
        }
        // writing
        writer.open();
        writer.write(PumlTranslater.mark_umlStart());
        for (Element elm : elements) {
            writer.write(PumlTranslater.translate_umlObject(elm));
        }
        writer.write(PumlTranslater.mark_umlEnd());
        // close
        writer.close();
        return true;
    }
}
