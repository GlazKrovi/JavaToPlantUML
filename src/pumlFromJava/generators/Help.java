package pumlFromJava.generators;

public class Help {

    public void print(){
        System.out.println("--Help for usages--");
        System.out.println(outOption());
        System.out.println(pathOption());
        System.out.println(typeOption());
        System.out.println(subPackages());
        System.out.println(scannedPackage());
        System.out.println(); // line break
    }

    private String outOption(){
        return "-out fileName\t: give the generated file(s) a personalized name";
    }
    private String pathOption(){
        return "-d path/to/file\t: give the generated file(s) a personalized path";
    }
    private String typeOption(){
        return "-g both 'or' acd 'or' ccd\t: (mandatory) precise which scheme(s) have to be generated";
    }
    private String subPackages(){
        return "-subpackages\t: (highly recommended) allow the recursive generation of all subpackages from the" +
                "precised package";
    }
    private String scannedPackage(){
        return "packageName\t: (mandatory) precise which package will be scanned";
    }

}
