package RealEstate.gateways;

import RealEstate.useCases.DatabaseFilePath;
import RealEstate.useCases.SendMessages;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserMessagesCSVController implements CsvInterface {

    private final DatabaseFilePath file;
    private final SendMessages sendMessages;

    public UserMessagesCSVController(SendMessages sendMessages) {
        this.sendMessages = sendMessages;
        file = new DatabaseFilePath("UserMessages.csv");
    }

    /**
     * Reads the messages from the UserMessages.csv file
     */
    public void read() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getFilePath()));
            String line;

            while ((line = br.readLine()) != null) {
                ArrayList<String> contents = new ArrayList<>(Arrays.asList(line.split(",")));
                String message = String.join(",", contents.subList(3, contents.size()));

                sendMessages.addMessage(contents.get(0), contents.get(1),
                        Integer.parseInt(contents.get(2)), message);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rewrites the UserMessages.csv file to include old and new messages
     */
    public void write() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getFilePath(), false));

            for (String data : sendMessages.getMessageStrings()) {
                bw.append("\n" + data);
            }

            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
