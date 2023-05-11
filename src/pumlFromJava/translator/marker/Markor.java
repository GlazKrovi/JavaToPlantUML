package pumlFromJava.translator.marker;

public class Markor {

    public Markor() {}

    // TRANSLATE
    /**
     * Returns '@startUml' and theme into file
     */
    public String umlStart(){
        return ("@startuml\n" +
                "!theme reddress-darkgreen\n");
    }

    public String option_strictUml(){
        return "skinparam style strictuml\n";
    }


    /**
     * Returns '@enduml' into file
     */
    public  String umlEnd(){
        return("\n@enduml\n");
    }

    /**
     * Open a package with specified name
     * @param packageName
     * @return Returns "package packageName {"
     */
    public String marks_package(String packageName){
        return "package " + packageName + " {";
    }
}

