package exceptions;


public class MessageNotFoundException extends IllegalArgumentException{
    public MessageNotFoundException() {
        super("Message not found, please try again.");
    }
}
