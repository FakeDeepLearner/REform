package useCases.messageUseCases;

import entities.NonAdminUser;
import entities.User;
import entities.containers.UserContainer;
import entities.Message;
import entities.containers.MessageContainer;
import useCases.DataInterface;
import useCases.miscellaneous.GenerateUniqueID;

import java.io.IOException;
import java.util.ArrayList;
import java.util.SortedSet;
import java.util.TreeSet;

//TODO: IntelliJ warning
public class SendMessages {

    private final MessageContainer<Integer, Message> messageContainer;
    private final UserContainer<String, User> userContainer;
    public final DataInterface i;


    /**
     * Create SendMessages objects
     *
     * @param messageContainer MessageContainer
     * @param userContainer    UserContainer
     */
    public SendMessages(MessageContainer<Integer, Message> messageContainer, UserContainer<String, User> userContainer,
                        DataInterface i) {
        this.messageContainer = messageContainer;
        this.userContainer = userContainer;
        this.i = i;
    }

    public void read() throws IOException {
        for (String[] data : i.read()) {
            addMessage(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4]);
        }
    }

    public void write() throws IOException {
        i.write();
    }

    /**
     * Sends a new message to the NonAdminUser recipient
     *
     * @param senderUsername    the username of the sender
     * @param recipientUsername the username of the recipient
     * @param content           the content of the message
     */
    public void sendMessage(String senderUsername, String recipientUsername, String content) {

//        int id = GenerateUniqueID.getUniqueID();
        GenerateUniqueID IDGenerator = new GenerateUniqueID(this.messageContainer);
        int id = IDGenerator.getUniqueID();

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
        NonAdminUser sender = userContainer.getNonAdmin(senderUsername);
        NonAdminUser recipient = userContainer.getNonAdmin(recipientUsername);

        Message message = new Message(sender, recipient, id, content);
        messageContainer.put(id, message);
        sender.sendMessage(recipient, message);
    }

    /**
     * Add previously sent messages that have an id and require a datetime stamp to messageContainer
     *
     * @param senderUsername    the username of the sender
     * @param recipientUsername the username of the recipient
     * @param id                the id of the message being sent
     * @param content           the content of the message
     * @param datetime          the datetime stamp of the message in the format dd-MM-yyyy HH:mm:ss
     */
    public void addMessage(String senderUsername, String recipientUsername,
                           int id, String content, String datetime) {
        NonAdminUser sender = userContainer.getNonAdmin(senderUsername);
        NonAdminUser recipient = userContainer.getNonAdmin(recipientUsername);

        Message message = new Message(sender, recipient, id, content, datetime);
        messageContainer.put(id, message);
        sender.sendMessage(recipient, message);
    }

    /**
     * Clears all the messages sent to and received by user
     *
     * @param username the username of user to remove all messages to and from
     */
    public void clearMessages(String username) {
        NonAdminUser u = userContainer.getNonAdmin(username);
        ArrayList<Integer> idsToRemove = new ArrayList<>();
        for (Integer i : messageContainer.keySet()) {
            if (messageContainer.get(i).getRecipient() == u || messageContainer.get(i).getSender() == u) {
                idsToRemove.add(i);
            }
        }
        for (Integer i : idsToRemove) {
            messageContainer.remove(i);
        }
    }

    /**
     * Get the message inbox of a user
     *
     * @param username Username of the user
     * @return ArrayList of String containing the messages
     */
    public ArrayList<String> getMessageInbox(String username) {
        ArrayList<String> messages = new ArrayList<>();
        NonAdminUser user = userContainer.getNonAdmin(username);

        SortedSet<Message> values = new TreeSet<>(user.getInbox().values());

        for (Message m : values) {
            messages.add(m.toString());
        }
        return messages;
    }

    /**
     * Get the message outbox of a user
     *
     * @param username Username of the user
     * @return ArrayList of String containing the messages
     */
    public ArrayList<String> getMessageOutbox(String username) {
        ArrayList<String> messages = new ArrayList<>();
        NonAdminUser user = userContainer.getNonAdmin(username);

        SortedSet<Message> values = new TreeSet<>(user.getOutbox().values());

        for (Message m : values) {
            messages.add(m.toString());
        }
        return messages;
    }

}