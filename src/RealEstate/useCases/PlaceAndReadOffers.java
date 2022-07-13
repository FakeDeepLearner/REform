package RealEstate.useCases;

import LoginSystem.entities.NonAdminUser;
import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import RealEstate.entities.Message;
import RealEstate.entities.MessageContainer;

import java.io.*;


public class PlaceAndReadOffers {

    private final MessageContainer<Integer, Message> messageContainer;
    private final UserContainer<String, User> userContainer;
    private final DatabaseFilePath file;


    public PlaceAndReadOffers(MessageContainer<Integer, Message> messageContainer,
                              UserContainer<String, User> userContainer) {
        this.messageContainer = messageContainer;
        this.userContainer = userContainer;
        file = new DatabaseFilePath("UserMessages.csv");
    }

    public void sendMessage(String senderUsername, String recipientUsername, String content) {
        User sender = userContainer.get(senderUsername);
        User recipient = userContainer.get(recipientUsername);

        if (!sender.getAdmin() && !recipient.getAdmin()) {
            Message message = new Message(sender, recipient, 0, content); //change id to call id generating use case

            messageContainer.put(0, message); //change id to call id generating use case

            ((NonAdminUser) sender).sendMessage((NonAdminUser) recipient, message);
        }
    }


    public void readMessageHistory() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(file.getFilePath()));
            String line;

            while ((line = br.readLine()) != null) {
                String[] contents = line.split(",");
                User sender = userContainer.get(contents[0]);
                User recipient = userContainer.get(contents[1]);

                Message m = new Message(sender, recipient, Integer.parseInt(contents[2]), contents[3]);
                messageContainer.put(Integer.parseInt(contents[2]), m);
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeMessages() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getFilePath(), true));

            for (Message m : messageContainer.values()) {

                bw.append(m.getSender().getUsername());
                bw.append(",");
                bw.append(m.getRecipient().getUsername());
                bw.append(",");
                bw.append(String.valueOf(m.getMessageID()));
                bw.append(",");
                bw.append(m.getContents());
                bw.append("\n");
            }
            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}