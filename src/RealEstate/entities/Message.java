package RealEstate.entities;
import LoginSystem.entities.User;

public class Message {
    private final User sender;
    private final User recipient;
    private final Integer messageID;
    private final String contents;

    /**
     * Create a message
     * @param sender the sender of the message
     * @param recipient the recipient of the message
     * @param messageID the id of the message
     * @param contents the contents of the message
     */
    public Message(User sender, User recipient, Integer messageID, String contents) {
        this.sender = sender;
        this.recipient = recipient;
        this.messageID = messageID;
        this.contents = contents;
    }

    /**
     * Getter for the message ID
     * @return the message ID
     */
    public Integer getMessageID() {
        return messageID;
    }

    /**
     * Getter for the contents of the message
     * @return the message contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * Getter for the recipient of the message
     * @return the message's recipient
     */
    public User getRecipient() {
        return recipient;
    }

    /**
     * Getter for the sender of the message
     * @return the message's sender
     */
    public User getSender() {
        return sender;
    }

    /**
     * Format and print all the information about this message
     * @return a String containing all the details about the message
     */
    @Override
    public String toString() {
        return "Message from: " + sender.getUsername() + " with an ID of " + messageID.toString() + "\n"
                + contents + "\n";
    }
}
