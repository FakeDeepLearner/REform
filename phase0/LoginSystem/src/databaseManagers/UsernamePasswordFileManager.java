package databaseManagers;

import Entities.User;
import Entities.UserContainer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class UsernamePasswordFileManager extends CSVmanager {

    private String directory;
    private String filename;

    // Constructors
    public UsernamePasswordFileManager(String directory, String filename) {
        super(directory, filename);
    }

    public UsernamePasswordFileManager() {
        super("phase0/LoginSystem/src/databaseManagers", "UsernamePassword.csv");
    }

    /**
     * Static method to add a custom first line
     * @param content String to add as the first line
     * @throws IOException
     */
    private static void addFirstLine(String content) throws IOException {
        FileWriter fw = new FileWriter(new File("phase0/LoginSystem/src/databaseManagers/UsernamePassword.csv"));
        BufferedWriter output = new BufferedWriter(fw);
        output.write(content);
        output.close();
    }

    // Static method to add the first line as "Username","Password,IsAdmin"
    private static void addFirstLine() throws IOException {
        FileWriter fw = new FileWriter(new File("phase0/LoginSystem/src/databaseManagers/UsernamePassword.csv"));
        BufferedWriter output = new BufferedWriter(fw);
        output.write("Username,Password,IsAdmin");
        output.close();
    }

    // Methods to create (initialize) the csv file
    public void createUsernamePasswordFile() throws IOException {
        super.createCSVfile();
        UsernamePasswordFileManager.addFirstLine();
    }

    public void createUsernamePasswordFile(String filename) throws IOException {
        super.createCSVfile(filename);
        UsernamePasswordFileManager.addFirstLine(filename);
    }

    public void createUsernamePasswordFile(String filename, UserContainer content) throws IOException {
        super.createCSVfile(filename);
        UsernamePasswordFileManager.addFirstLine(filename);

        ArrayList<String> array = UsernamePasswordFileManager.reformatContainer(content);

        for (String line : array) {
            super.addLine(filename, line);
        }
    }

    public void createUsernamePasswordFile(UserContainer content) throws IOException {
        super.createCSVfile();
        UsernamePasswordFileManager.addFirstLine();

        ArrayList<String> array = UsernamePasswordFileManager.reformatContainer(content);

        for (String line : array) {
            super.addLine("phase0/LoginSystem/src/databaseManagers/UsernamePassword.csv", line);
        }
    }


    /**
     * Static method to reformat the UserNameAndPasswordContainer into an ArrayList of String
     *
     * @param c UserNameAndPasswordContainer
     * @return [user1, psw1,IsAdmin1 "\n" user2,psw2, IsAdmin2]
     */

    public static ArrayList<String> reformatContainer(UserContainer<String, User> c) {

        ArrayList<String> out = new ArrayList<>();

        for (Map.Entry<String, User> entry : c.entrySet()) {
            String key = entry.getKey();
            User value = entry.getValue();

            out.add(key + "," + value.getPassword() + String.valueOf(value.isAdmin()));

        }

        return out;
    }

    public static ArrayList<String> reformatContainer(UserContainer<String, User> c, boolean includeType) {

        ArrayList<String> out = new ArrayList<>();

        if (!includeType) {
            for (Map.Entry<String, User> entry : c.entrySet()) {
                String key = entry.getKey();
                User value = entry.getValue();

                out.add(key + "," + value.getPassword());

            }
        } else {
            for (Map.Entry<String, User> entry : c.entrySet()) {
                String key = entry.getKey();
                User value = entry.getValue();

                // TODO: make a isAdmin() in User.java
//                out.add(key + "," + value.getPassword() + value.isAdmin);

            }
        }
        return out;
    }


    /**
     * Methods to add User information to the CSV file
     *
     * @param file name of the file to add the user info
     * @param u    user to be added to the file
     * @throws IOException
     */
    public void addUserInfo(String file, User u) throws IOException {
        String info = u.getUsername() + "," + u.getPassword() + String.valueOf(u.isAdmin());
        addLine(file, info);
    }

    public void addUserInfo(User u) throws IOException {
        String info = u.getUsername() + "," + u.getPassword() + String.valueOf(u.isAdmin());
        addLine("phase0/LoginSystem/src/databaseManagers/UsernamePassword.csv", info);
    }


}

