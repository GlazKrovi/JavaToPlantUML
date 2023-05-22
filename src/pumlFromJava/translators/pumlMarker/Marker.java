package pumlFromJava.translators.pumlMarker;

public class Marker implements IPumlMarker {

    public Marker() {
    }

    /**
     * Returns '@startUml' and theme
     */
    public String umlStart() {
        return ("@startuml\n!theme reddress-darkgreen\n");
    }

    /**
     * Returns 'skinparam style strictuml'
     */
    public String option_strictUml() {
        return "skinparam style strictuml\n";
    }
}

