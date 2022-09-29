package useCases.messageUseCases;

import entities.users.AdminUser;
import entities.Message;
import entities.ReportMessage;
import entities.users.User;
import entities.containers.UserContainer;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class ViewMessages {
    private final UserContainer<String, User> users;

    public ViewMessages(UserContainer<String, User> users) {
        this.users = users;
    }

    public List<String> getAdminReportsStrings(String username){
        List<String> reportStrings = new ArrayList<>();
        AdminUser admin = users.getAdmin(username);
        for(Message message : admin.getInbox().values()){
            if(message instanceof ReportMessage){
                reportStrings.add(message.toString());
            }
        }
        return reportStrings;
    }

    public List<ReportMessage> getAdminReports(String username){
        List<ReportMessage> reports = new ArrayList<>();
        AdminUser admin = users.getAdmin(username);
        for(Message message : admin.getInbox().values()){
            if(message instanceof ReportMessage){
                reports.add((ReportMessage) message);
            }
        }
        return reports;
    }

    /**
     * Get the message inbox of a user
     *
     * @param username Username of the user
     * @return ArrayList of String containing the messages
     */
    public ArrayList<String> getMessageInbox(String username) {
        ArrayList<String> messages = new ArrayList<>();
        User user = users.get(username);

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
        User user = users.get(username);

        SortedSet<Message> values = new TreeSet<>(user.getOutbox().values());

        for (Message m : values) {
            messages.add(m.toString());
        }
        return messages;
    }




}
