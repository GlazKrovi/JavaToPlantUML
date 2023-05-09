package pumlFromJava;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Writer {
    private PrintWriter pw;
    private String filename;

    /* CREATE */
    public Writer(String filename)
    {
        this.filename = filename;
    }

    public Writer()
    {
        this.filename = "gen.puml";
    }

    /* Function */
    /**
     * Opens a file to write to (and create if it does not exist)
     * @return true
     */
    public boolean open(){
        verify();
        links();
        return true;
    }

    /**
     * Closes the current file (necessary to open another one!)
     * @return true
     */
    public boolean close(){
        // close file
        if (pw != null) pw.close();
        return true;
    }

    public boolean write(String text){
        if (pw != null){
            pw.println(text);
        }
        else{
            System.out.println("File isn't open !\nPlease write mywriter.open() to open file.\n" +
                    "(Don't forget to close this before opening another file -- with mywriter.close() ");
        }
        return true;
    }


    /**
     * Checks for the existence of a file, otherwise creates it
     * @return
     */
    private boolean verify(){
        File file = new File(filename);
        /*
        // Directory
        if (filepath != null && filepath != ""){
            File dir = new File(filepath);
            if (!dir.exists()){
                dir.mkdir();
            }
        }
        */
        // File
        if (file.exists()){
            file.delete();
        }
        return true;
    }

    /**
     * Binds the file to the class
     * @return
     */
    private boolean links()
    {
        try
        {
            /*
            if(filepath != null && filepath != "") {
                pw = new PrintWriter(filepath + filename);
            }
            else{ } */
                pw = new PrintWriter(filename);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
        return true;
    }
}
