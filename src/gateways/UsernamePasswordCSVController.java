package gateways;

import entities.AdminUser;
import entities.User;
import entities.containers.UserContainer;
import useCases.CSVUseCases.DatabaseFilePath;
import useCases.userUseCases.UserFactory;

import java.io.*;
import java.util.HashMap;

public class UsernamePasswordCSVController implements CsvInterface{

    private final UserFactory userFactory;

    private final UserContainer<String, User> users;
    private final static DatabaseFilePath filepath = new DatabaseFilePath("UsernamePassword.csv");


    public UsernamePasswordCSVController(UserContainer<String, User> users,
                                         UserFactory userFactory){
        this.users = users;
        this.userFactory = userFactory;
    }

    @Override
    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath.getFilePath()));
        String line;
        while((line = reader.readLine()) != null){
            String[] readLine = line.split(",");
            String username = readLine[0];
            String password = readLine[1];
            userFactory.createUser("Admin", username, password);
        }
        reader.close();

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
