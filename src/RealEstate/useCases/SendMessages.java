package RealEstate.useCases;

import LoginSystem.entities.NonAdminUser;
import RealEstate.entities.Message;
import RealEstate.entities.MessageContainer;
import RealEstate.entities.NonAdminContainer;

import java.util.ArrayList;


public class SendMessages {

    private final MessageContainer<Integer, Message> messageContainer;
    private final NonAdminContainer<String, NonAdminUser> nonAdminContainer;


    public SendMessages(MessageContainer<Integer, Message> messageContainer,
                        NonAdminContainer<String, NonAdminUser> nonAdminContainer) {
        this.messageContainer = messageContainer;
        this.nonAdminContainer = nonAdminContainer;

    }

    /**
     * Sends a new message to the NonAdminUser recipient
     *
     * @param senderUsername    the username of the sender
     * @param recipientUsername the username of the recipient
     * @param content           the content of the message
     */
    public void sendMessage(String senderUsername, String recipientUsername, String content) {
        int id = GenerateUniqueID.getUniqueId();
        addMessage(senderUsername, recipientUsername, id, content);
    }

    /**
     * Adds previously sent messages that have an id to messageContainer
     *
     * @param senderUsername    the username of the sender
     * @param recipientUsername the username of the recipient
     * @param id                the id of the message being sent
     * @param content           the content of the message
     */
    public void addMessage(String senderUsername, String recipientUsername,
                           int id, String content) {
        NonAdminUser sender = nonAdminContainer.get(senderUsername);
        NonAdminUser recipient = nonAdminContainer.get(recipientUsername);

        Message message = new Message(sender, recipient, id, content);
        messageContainer.put(id, message);
        sender.sendMessage(recipient, message);
    }

    /**
     * Creates a list containing all the messages in messageContainer in CSV format
     *
     * @return an ArrayList<String> containing all the messages
     */
    public ArrayList<String> getMessageStrings() {
        ArrayList<String> messages = new ArrayList<>();
        for (Message m : messageContainer.values()) {
            String messageData = m.getSender().getUsername() +
                    "," +
                    m.getRecipient().getUsername() +
                    "," +
                    m.getMessageID() +
                    "," +
                    m.getContents() +
                    "\n";
            messages.add(messageData);
        }
        return messages;
    }

}