package RealEstate.useCases;
import java.io.File;


/**
 * Class to get the absolute file path of a file.
 *
 * Usage Example:
 *         DatabaseFilePath c = new DatabaseFilePath("EXAMPLE.csv");
 *         System.out.println(c.getFilePath());     // prints the absolute path of EXAMPLE.csv in the databse folder
 *
 *         // below will create EXAMPLE.csv under the database folder
 *         FileWriter fw = new FileWriter(new File(DatabaseFilePath.getDatabasePath(), c.getFileName()));
 *         BufferedWriter output = new BufferedWriter(fw);
 *         output.close();
 */

public class DatabaseFilePath {

    private String filePath;
    private String fileName;

    public DatabaseFilePath(String filename){
        this.filePath = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "RealEstate" + File.separator + "database" + File.separator + filename;
        this.fileName = filename;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static String getDatabasePath(){
        return System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "RealEstate" + File.separator + "database" + File.separator;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
