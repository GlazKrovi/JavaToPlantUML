package pumlFromJava.writers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Writer implements WriterSpecies {
    private final String filepath;
    private PrintWriter pw;

    public Writer(String filepath, String filename) {
        this.filepath = filepath + filename;
        createDirectoriesPath(filepath); // create nonexistent parents directories
    }

    public Writer(String filename) {
        this.filepath = filename;
    }

    public void open() {
        createFile();
        links();
    }

    public void close() {
        // close file
        if (pw != null) pw.close();
    }

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
    private void createDirectoriesPath(String path) {
        File directories = new File(path);
        directories.mkdirs();
    }
}
