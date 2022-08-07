package gateways;

import entities.Message;
import entities.containers.MessageContainer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MessagesCSVController extends CSVController {

    private final MessageContainer<Integer, Message> messages;

    public MessagesCSVController(MessageContainer<Integer, Message> messages) {
        super("Messages.csv");

        this.messages = messages;
    }

    /**
     * Rewrites the Messages.csv file to include old and new messages
     */
    public void write() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filepath.getFilePath(), false));

            for (Integer id : messages.keySet()) {
                Message m = messages.get(id);
                String messageData = m.getSender().getUsername() + "," + m.getRecipient().getUsername() + "," +
                        m.getMessageID() + "," + m.getContents() + "," + m.getFormattedDateTime() + "\n";

                bw.append(messageData);
            }

            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
