package entities;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class Message implements Comparable<Message> {
    private final User sender;
    private final User recipient;
    private final Integer messageID;
    private final String contents;
    protected final LocalDateTime datetime;

    protected final DateTimeFormatter formatter;


    /**
     * Create a message
     *
     * @param sender    the sender of the message
     * @param recipient the recipient of the message
     * @param messageID the id of the message
     * @param contents  the contents of the message
     */
    public Message(User sender, User recipient, Integer messageID, String contents) {
        this.sender = sender;
        this.recipient = recipient;
        this.messageID = messageID;
        this.contents = contents;
        this.datetime = LocalDateTime.now();
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    }

    public Message(User sender, User recipient, Integer messageID, String contents, String datetime) {
        this.sender = sender;
        this.recipient = recipient;
        this.messageID = messageID;
        this.contents = contents;

        int year = Integer.parseInt(datetime.substring(6, 10));
        int month = Integer.parseInt(datetime.substring(3, 5));
        int dayOfMonth = Integer.parseInt(datetime.substring(0, 2));
        int hour = Integer.parseInt(datetime.substring(11, 13));
        int minute = Integer.parseInt(datetime.substring(14, 16));
        int second = Integer.parseInt(datetime.substring(17, 19));

        this.datetime = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
        this.formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    }

    /**
     * Getter for the message ID
     *
     * @return the message ID
     */
    public Integer getMessageID() {
        return messageID;
    }

    /**
     * Getter for the contents of the message
     *
     * @return the message contents
     */
    public String getContents() {
        return contents;
    }

    /**
     * Getter for the recipient of the message
     *
     * @return the message's recipient
     */
    public User getRecipient() {
        return recipient;
    }

    /**
     * Getter for the sender of the message
     *
     * @return the message's sender
     */
    public User getSender() {
        return sender;
    }


    /**
     * Format and print all the information about this message
     *
     * @return a String containing all the details about the message
     */

    @Override
    public String toString() {
        return "\n" + datetime.format(formatter) + "\n" + "Message from " + sender.getUsername() + " to " +
                recipient.getUsername() + " (Message ID of " + messageID.toString() + ")" + "\n"
                + contents + "\n";
    }

    @Override
    public int compareTo(Message o) {
        return this.datetime.compareTo(o.datetime);
    }

    public String getFormattedDateTime(){
        return this.datetime.format(formatter);
    }
}
