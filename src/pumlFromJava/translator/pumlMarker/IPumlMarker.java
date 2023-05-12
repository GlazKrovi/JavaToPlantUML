package pumlFromJava.translator.pumlMarker;

public interface IPumlMarker {
    /**
     * Returns '@startUml'
     */
    default String umlStart() {
        return ("@startuml\n");
    }

    /**
     * Returns 'skinparam style strictuml'
     */
    default String option_strictUml() {
        return "skinparam style strictuml\n";
    }

    /**
     * Returns '@enduml'
     */
    default String umlEnd() {
        return ("\n@enduml\n");
    }
}
