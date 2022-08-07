package useCases.userUseCases;

import entities.User;
import entities.containers.UserContainer;
import useCases.DataInterface;

import java.io.IOException;
import java.util.ArrayList;


public class UpdateUserHistory {
    private final UserContainer<String, User> interfaceUsers;
    public DataInterface i;

    public UpdateUserHistory(UserContainer<String, User> interfaceUsers, DataInterface i) {
        this.interfaceUsers = interfaceUsers;
        this.i = i;
    }

    public void read() throws IOException {
        for (String[] data : i.read()) {
            addLoginHistory(data[0], data[1]);
        }
    }

    public void write() throws IOException {
        i.write();
    }

    /**
     * Get this user's login history locally (without reading from csv file).
     *
     * @param username this user's username.
     */
    public ArrayList<String> getLoginHistory(String username) {
        User user = interfaceUsers.get(username);
        return user.getLoginHistory();
    }

    public void addLoginHistory(String username, String newLogin) {
        User user = interfaceUsers.get(username);
        user.addToLoginHistory(newLogin);
    }
}
