package LoginSystem.useCases;
import java.io.File;


public class csvFilePath {

    private String filePath;
    private String fileName;

    public csvFilePath(String filename){
        this.filePath = System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "LoginSystem" + File.separator + filename;
        this.fileName = filename;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public static String getDatabasePath() {
        return System.getProperty("user.dir") + File.separator +
                "src" + File.separator + "LoginSystem" + File.separator;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
