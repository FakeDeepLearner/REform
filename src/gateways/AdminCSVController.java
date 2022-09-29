package gateways;

import entities.users.AdminUser;
import entities.users.User;
import entities.containers.UserContainer;

import java.io.*;
import java.util.HashMap;

public non-sealed class AdminCSVController extends CSVController {
    private final UserContainer<String, User> users;

    public AdminCSVController(UserContainer<String, User> users) {
        super("Admin.csv");

        this.users = users;
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), false));
        HashMap<String, AdminUser> createdAdmins = users.getAllAdmins();
        for (String username : createdAdmins.keySet()){
            writer.write(username + "," + createdAdmins.get(username).getPassword() + "\n");
        }
        writer.close();
    }
}
