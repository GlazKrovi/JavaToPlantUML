package pumlFromJava.writer;

public interface IWriter {
    /**
     * Opens a file to write to (and create if it does not exist).
     * If the file is not closed, the changes will be left in a buffer,
     * and the file will not be edited
     */
    void open();

    /**
     * Closes the current file (necessary to open another one!)
     */
    void close();

    /**
     * Write the specified text into the opened file
     *
     * @param text any text
     */
    void write(String text);
}
