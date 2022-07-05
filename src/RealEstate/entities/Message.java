package RealEstate.entities;
import LoginSystem.entities.User;

public class Message {
    private final User sender;
    private final User recipient;
    private final Integer messageID;
    private final String contents;

    public Message(User sender, User recipient, Integer messageID, String contents) {
        this.sender = sender;
        this.recipient = recipient;
        this.messageID = messageID;
        this.contents = contents;
    }

    public Integer getMessageID() {
        return messageID;
    }

    public String getContents() {
        return contents;
    }

    public User getRecipient() {
        return recipient;
    }

    public User getSender() {
        return sender;
    }

    @Override
    public String toString() {
        return "Message from: " + sender.getUsername() + "with an ID of " + messageID.toString() + "\n"
                + contents + "\n\n";
    }
}
