package useCases.CSVUseCases;

import entities.User;
import entities.containers.UserContainer;
import useCases.userUseCases.AuthenticateUser;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;

public class UsernamePasswordFileEditor extends CsvEditor {
    private final AuthenticateUser auth;
    private final UserContainer<String, User> interfaceUsers;

    /**
     * Constructor for UsernamePasswordFileEditor
     * @param interfaceUsers is the container that stores users of the program
     */
    public UsernamePasswordFileEditor(UserContainer<String, User> interfaceUsers) {
        super(csvFilePath.getDatabasePath(), "UsernamePassword.csv");
        this.interfaceUsers = interfaceUsers;

        auth = new AuthenticateUser(interfaceUsers);
    }

    /**
     * Constructor for UsernamePasswordFileEditor
     * @param auth is the user authenticator
     * @param interfaceUsers is the container that stores users of the program
     */
    public UsernamePasswordFileEditor(AuthenticateUser auth, UserContainer<String, User> interfaceUsers) {
        super(csvFilePath.getDatabasePath(), "UsernamePassword.csv");
        this.auth = auth;
        this.interfaceUsers = interfaceUsers;
    }


    // Static method to add the first line as "Username","Password,IsAdmin"
    private static void addFirstLine() throws IOException {
        FileWriter fw = new FileWriter(csvFilePath.getDatabasePath() + "UsernamePassword.csv");
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
            super.addLine(csvFilePath.getDatabasePath() + "UsernamePassword.csv", line);
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

            out.add(key + "," + value.getPassword() + "," + value.getAdmin());

        }

        return out;
    }

    public void addUserInfo(String username, String password) throws IOException {
        String info = username + "," + password + "," + auth.checkUserAdmin(username);
        addLine(csvFilePath.getDatabasePath() + "UsernamePassword.csv", info);
    }


    public ArrayList<ArrayList<String>> getUsersFromCSV() throws IOException {
        ArrayList<ArrayList<String>> outside = new ArrayList<>();

        FileReader fw = new FileReader(csvFilePath.getDatabasePath() + "UsernamePassword.csv");
        return getArrayLists(outside, fw);
    }


    private ArrayList<ArrayList<String>> getArrayLists(ArrayList<ArrayList<String>> outside, FileReader fw) throws IOException {
        String line;
        BufferedReader br = new BufferedReader(fw);
        br.readLine();

        while ((line = br.readLine()) != null) {
            ArrayList<String> inside = new ArrayList<>();
            Collections.addAll(inside, line.split(",", 0));
            outside.add(inside);
        }

        return outside;
    }
}
