package gateways;

import entities.User;
import entities.containers.UserContainer;
import useCases.CSVUseCases.csvFilePath;

import java.io.*;

public class HistoriesCSVController extends CSVController {
    private final UserContainer<String, User> users;

    public HistoriesCSVController(UserContainer<String, User> users) {
        super("Histories.csv");

        this.users = users;
    }

    @Override
    public void write() throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter(csvFilePath.getDatabasePath() + "Histories.csv", false));

        for (String username : users.keySet()) {
            for (String loginHistory : users.get(username).getLoginHistory()) {
                bw.append(username);
                bw.append(",");
                bw.append(loginHistory);
                bw.append("\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
