package pumlFromJava.translator.pumlMarker;

public class Marker implements PumlMarker {

    public Marker() {
    }

    /**
     * Returns '@startUml' and theme
     */
    public String umlStart() {
        return ("@startuml\n!theme reddress-darkgreen\n");
    }
}

