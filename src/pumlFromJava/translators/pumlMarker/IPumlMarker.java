package pumlFromJava.translators.pumlMarker;

public interface IPumlMarker {
    /**
     * Returns '@startUml'
     */
    default String umlStart() {
        return ("@startuml\n");
    }

    /**
     * Returns '@enduml'
     */
    default String umlEnd() {
        return ("\n@enduml\n");
    }
}
