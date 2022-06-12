import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


public class UpdateUserHistory {
    private UserNameAndPasswordContainer<String, User> interface_users;

    public UpdateUserHistory(UserNameAndPasswordContainer<String, User> interface_users) {
        this.interface_users = interface_users;
    }

    /**
     * Read this user's login history from csv file.
     * @param u this user.
     */
    public ArrayList<String> readUserHistory(User u) {
        ArrayList<String> userHistory = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("UserHistories.csv"));
            String line;

            while ((line = br.readLine()) != null) {
                Object[] contents = line.split(",");

                if (u.getUsername().equals(contents[0])) {
                    userHistory.add(contents[1].toString());
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        u.setLoginHistory(userHistory);
        return userHistory;
    }

    /**
     * Write a new login for this user to csv file.
     * @param u this user.
     */
    public void writeUserHistory(User u) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("UserHistories.csv", true));
            Scanner sc = new Scanner("UserHistories.csv");
            Date newLogin = new Date();

            while (sc.hasNext()) {
                sc.next();
            }

            bw.append(u.getUsername());
            bw.append(",");
            bw.append(newLogin.toString());
            bw.append("\n");

            bw.flush();
            bw.close();

            u.addToLoginHistory(newLogin.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get this user's login history locally (without reading from csv file).
     * @param u this user.
     */
    public ArrayList<String> getLoginHistory(User u) {
        return u.getLoginHistory();
    }
}
