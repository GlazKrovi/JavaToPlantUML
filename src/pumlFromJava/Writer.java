package pumlFromJava;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Writer {
    private PrintWriter pw;
    private String filepath;

    /* CREATE */
    public Writer(String filepath, String filename)
    {
        this.filepath = filepath + filename;
        create_directories(filepath); // create nonexistent parents directories
    }
    public Writer(String filename)
    {
        this.filepath = filename;
    }
    public Writer()
    {
        this.filepath = "autogen.puml";
    }

    /* Function */
    /**
     * Opens a file to write to (and create if it does not exist)
     * @return true
     */
    public boolean open(){
        createFile();
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
    private boolean createFile(){
        File file = new File(filepath);
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
                pw = new PrintWriter(filepath);
        }
        catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
        return true;
    }

    /**
     * Test if specified directory exists
     * @param path
     * @return Returns true if and only if directory exists, else returns false
     */
    private boolean create_directories(String path){
        File directories = new File(path);
        directories.mkdirs();
        return true;
    }
}
