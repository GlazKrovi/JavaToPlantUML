package pumlFromJava.translators.pumlMarker;

public interface IPumlMarker {
    /**
     * Returns '@startUml'
     */
    String umlStart();

    /**
     * Returns '@enduml'
     */
    String umlEnd();
}
