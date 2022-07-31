package useCases.CSVUseCases;

import java.io.*;

public class CsvEditor {

    private final String directory;
    private final String filename;


    // Constructors
    public CsvEditor(String directory, String filename) {
        this.directory = directory;
        this.filename = CsvEditor.formatFilename(filename);
    }

    /**
     * Static method to format the filename as filename.csv
     *
     * @param filename : the name of the file to be formatted
     * @return the formatted file name
     */
    public static String formatFilename(String filename) {
        String out;

        // filename can't end with .csv
        if (filename.length() < 5) {
            out = filename + ".csv";
        } else {
            if ((filename.endsWith(".csv"))) {    // filename ends with .csv
                out = filename;
            } else {
                out = filename + ".csv";
            }
        }

        return out;
    }


    public void createCSVfile() throws IOException {
        FileWriter fw = new FileWriter(new File(this.directory, this.filename));
        BufferedWriter output = new BufferedWriter(fw);
//        output.write("");
        output.close();
    }


    public void addLine(String filename, String content) throws IOException {
        FileWriter fw = new FileWriter(CsvEditor.formatFilename(filename), true);
        BufferedWriter output = new BufferedWriter(fw);
        output.append("\n").append(content);
        output.close();
    }


}
