package pumlFromJava.translators.marker;

/**
 * This writes the basic lines for opening and closing a puml file, as well as a possible theme and/or option
 */
public class Marker {
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

    /**
     * Returns '@enduml'
     */
    public String umlEnd() {
        return ("\n@enduml\n");
    }
}

