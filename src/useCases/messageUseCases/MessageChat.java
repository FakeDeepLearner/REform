package useCases.messageUseCases;

import entities.Message;
import entities.User;
import entities.containers.MessageContainer;

import java.util.ArrayList;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class MessageChat {

    private MessageContainer messageContainer;

    public MessageChat(MessageContainer mc) {
        this.messageContainer = mc;
    }

    public void setMessageContainer(MessageContainer messageContainer) {
        this.messageContainer = messageContainer;
    }

    public void printChatHistory(User u1, User u2) {
        System.out.println(String.format("Chat history between %s and %s", u1.getUsername(), u2.getUsername()));

        MessageContainer relevantMessages = MessageChat.getMessagesBetweenUsers(u1, u2, this.messageContainer);

        SortedSet<Message> values = new TreeSet<>(relevantMessages.values());

        for (Message m : values) {
            System.out.println(m);
        }

    }

    private static MessageContainer getMessagesBetweenUsers(User u1, User u2, MessageContainer<Integer, Message> mc) {
        MessageContainer out = new MessageContainer();

        ArrayList<User> users = new ArrayList<>();
        users.add(u1);
        users.add(u2);

        for (Message m : mc.values()) {
            User sender = m.getSender();
            User recipient = m.getRecipient();

            if (users.contains(sender) && users.contains(recipient)) {
                out.put(m.getMessageID(), m);
            }

        }

        return out;
    }


}
