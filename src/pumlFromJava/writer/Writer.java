package pumlFromJava.writer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Writer implements IWriter {
    private final String filepath;
    private PrintWriter pw;

    public Writer(String filepath, String filename) {
        this.filepath = filepath + filename;
        create_directories(filepath); // create nonexistent parents directories
    }

    public Writer(String filename) {
        this.filepath = filename;
    }

    /**
     * Opens a file to write to (and create if it does not exist).
     * If the file is not closed, the changes will be left in a buffer,
     * and the file will not be edited
     */
    public void open() {
        createFile();
        links();
    }

    /**
     * Closes the current file (necessary to open another one!)
     */
    public void close() {
        // close file
        if (pw != null) pw.close();
    }

    /**
     * Write the specified text into the opened file
     *
     * @param text any text
     */
    public void write(String text) {
        if (pw != null) {
            pw.println(text);
        }
    }

    /**
     * Checks for the existence of a file, otherwise creates it
     */
    private void createFile() {
        File file = new File(filepath);
        if (file.exists()) {
            file.delete();
        }
    }

    /**
     * Binds the file to the class
     */
    private void links() {
        try {

            pw = new PrintWriter(filepath);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            throw new RuntimeException(e);
        }
    }

    /**
     * Create any missing parent files
     *
     * @param path the path to the file
     */
    private void create_directories(String path) {
        File directories = new File(path);
        directories.mkdirs();
    }
}
