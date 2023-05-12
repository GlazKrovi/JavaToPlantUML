package pumlFromJava.translator.pumlMarker;

public class Marker implements IPumlMarker {

    public Marker() {
    }

    /**
     * Returns '@startUml' and theme
     */
    public String umlStart() {
        return ("@startuml\n!theme reddress-darkgreen\n");
    }
}

