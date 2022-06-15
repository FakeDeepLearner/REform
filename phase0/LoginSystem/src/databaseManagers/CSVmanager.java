package databaseManagers;

import java.io.*;
import java.util.ArrayList;

public class CSVmanager {

    private String directory;
    private String filename;


    // Constructors
    public CSVmanager(String directory, String filename) {
        this.directory = directory;
        this.filename = CSVmanager.formatFilename(filename);
    }

    public CSVmanager() {
    }


    /**
     * Static method to format the filename as filename.csv
     *
     * @param filename
     * @return
     */
    public static String formatFilename(String filename) {
        String out = "";

        // filename can't end with .csv
        if (filename.length() < 5) {
            out = filename + ".csv";
        } else {
            if ((filename.substring(filename.length() - 4).equals(".csv"))) {    // filename ends with .csv
                out = filename;
            } else {
                out = filename + ".csv";
            }
        }

        return out;
    }


    // Methods for creating a new CSV file.

    /**
     * Create a new .csv file
     *
     * @param directory the directory at which the .csv file will be saved
     * @param filename  name of the .csv file to be saved
     * @param content   content of the .csv file to be saved
     * @throws IOException
     */
    public void createCSVfile(String directory, String filename, String content) throws IOException {
        FileWriter fw = new FileWriter(new File(directory, CSVmanager.formatFilename(filename)));
        BufferedWriter output = new BufferedWriter(fw);
        output.write(content);
        output.close();
    }

    public void createCSVfile(String filename, String content) throws IOException {
        FileWriter fw = new FileWriter(CSVmanager.formatFilename(filename));
        BufferedWriter output = new BufferedWriter(fw);
        output.write(content);
        output.close();
    }

    public void createCSVfile(String filename) throws IOException {
        FileWriter fw = new FileWriter(CSVmanager.formatFilename(filename));
        BufferedWriter output = new BufferedWriter(fw);
//        output.write("");
        output.close();
    }

    public void createCSVfile() throws IOException {
        FileWriter fw = new FileWriter(new File(this.directory, this.filename));
        BufferedWriter output = new BufferedWriter(fw);
//        output.write("");
        output.close();
    }


    // Methods for appending line

    /**
     * Add line to the end of the csv file
     *
     * @param filename name of the .csv file to add the line
     * @param content  content of the line to add
     * @throws IOException
     */
    public void addLine(String filename, String content) throws IOException {
        FileWriter fw = new FileWriter(CSVmanager.formatFilename(filename), true);
        BufferedWriter output = new BufferedWriter(fw);
        output.append("\n" + content);
        output.close();
    }

    public void addLine(String content) throws IOException {
        FileWriter fw = new FileWriter(CSVmanager.formatFilename(this.filename), true);
        BufferedWriter output = new BufferedWriter(fw);
        output.append("\n" + content);
        output.close();
    }

    /**
     * Read lines from a .csv file and return an ArrayList
     *
     * @param filename name of the .csc file to read
     * @return ArrayList of String
     * @throws IOException
     */
    public ArrayList<String> readLines(String filename) throws IOException {
        ArrayList<String> out = new ArrayList<>();
        String line = null;

        FileReader fw = new FileReader(CSVmanager.formatFilename(filename));
        BufferedReader br = new BufferedReader(fw);

        while ((line = br.readLine()) != null) {
            out.add(line + "\n");
        }

        return out;

    }


}
