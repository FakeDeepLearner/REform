package useCases;

import Entities.User;
import Entities.UserContainer;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class UsernamePasswordFileEditor extends CsvEditor {
    private String directory;
    private String filename;
    private final AuthenticateUser auth;
    private final UserContainer<String, User> interfaceUsers;

    // Constructors
    public UsernamePasswordFileEditor(AuthenticateUser auth, UserContainer<String, User> interfaceUsers) {
        super("", "UsernamePassword.csv");
        this.auth = auth;
        this.interfaceUsers = interfaceUsers;
    }

    public UsernamePasswordFileEditor(AuthenticateUser auth, UserContainer<String, User> interfaceUsers,
                                      String directory, String filename) {
        super(directory, filename);
        this.auth = auth;
        this.interfaceUsers = interfaceUsers;
    }

    /**
     * Static method to add a custom first line
     *
     * @param content String to add as the first line
     * @throws IOException when file cannot be found
     */
    private static void addFirstLine(String content) throws IOException {
        FileWriter fw = new FileWriter("UsernamePassword.csv");
        BufferedWriter output = new BufferedWriter(fw);
        output.write(content);
        output.close();
    }

    // Static method to add the first line as "Username","Password,IsAdmin"
    private static void addFirstLine() throws IOException {
        FileWriter fw = new FileWriter("UsernamePassword.csv");
        BufferedWriter output = new BufferedWriter(fw);
        output.write("Username,Password,IsAdmin");
        output.close();
    }

    // Methods to create (initialize) the csv file
    public void createUsernamePasswordFile() throws IOException {
        super.createCSVfile();
        UsernamePasswordFileEditor.addFirstLine();

        ArrayList<String> array = UsernamePasswordFileEditor.reformatContainer(interfaceUsers);

        for (String line : array) {
            super.addLine("UsernamePassword.csv", line);
        }
    }

    public void createUsernamePasswordFile(String filename) throws IOException {
        super.createCSVfile(filename);
        UsernamePasswordFileEditor.addFirstLine(filename);

        ArrayList<String> array = UsernamePasswordFileEditor.reformatContainer(interfaceUsers);

        for (String line : array) {
            super.addLine(filename, line);
        }
    }

    public void createUsernamePasswordFile(String filename, UserContainer<String, User> content) throws IOException {
        super.createCSVfile(filename);
        UsernamePasswordFileEditor.addFirstLine(filename);

        ArrayList<String> array = UsernamePasswordFileEditor.reformatContainer(content);

        for (String line : array) {
            super.addLine(filename, line);
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

            out.add(key + "," + value.getPassword() + "," + value.isAdmin());

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

                out.add(key + "," + value.getPassword() + "," + value.isAdmin());

            }
        }
        return out;
    }

    public void addUserInfo(String username, String password) throws IOException {
        String info = username + "," + password + "," + auth.checkUserAdmin(username);
        addLine("UsernamePassword.csv", info);
    }

    /**
     * Methods to add User information to the CSV file
     *
     * @param file     name of the file to add the user info
     * @param username to be added to the file
     * @param password of the user to be added to the file
     * @throws IOException when file cannot be found
     */
    public void addUserInfo(String file, String username, String password) throws IOException {
        String info = username + "," + password + "," + auth.checkUserAdmin(username);
        addLine(file, info);
    }

    public ArrayList<ArrayList<String>> getUsersFromCSV() throws IOException {
        ArrayList<ArrayList<String>> outside = new ArrayList<>();

        String line = null;

        FileReader fw = new FileReader("UsernamePassword.csv");
        return getArrayLists(outside, fw);
    }

    /**
     * @param filename e.g. "phase0/src/databaseManagers/UsernamePassword.csv"
     * @return 2-D arrayList [ [u1,pw2,Admin1], [u2,pw2,Admin2], ... ]
     * @throws IOException when file cannot be found
     */
    public ArrayList<ArrayList<String>> getUsersFromCSV(String filename) throws IOException {
        ArrayList<ArrayList<String>> outside = new ArrayList<>();

        String line = null;

        FileReader fw = new FileReader(CsvEditor.formatFilename(filename));
        return getArrayLists(outside, fw);
    }

    private ArrayList<ArrayList<String>> getArrayLists(ArrayList<ArrayList<String>> outside, FileReader fw) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(fw);
        br.readLine();

        while ((line = br.readLine()) != null) {
            ArrayList<String> inside = new ArrayList<>();
            Collections.addAll(inside, line.split("[,]", 0));
            outside.add(inside);
        }

        return outside;
    }
}
