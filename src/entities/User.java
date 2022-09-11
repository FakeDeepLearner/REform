package entities;
import entities.containers.MessageContainer;

import java.util.ArrayList;

public abstract sealed class User
permits AdminUser, NonAdminUser
{
    private final String username;
    private final String password;
    private final ArrayList<String> loginHistory;
    protected Boolean isAdmin = false;
    private boolean isLoggedIn = false;
    protected MessageContainer<Integer, Message> outbox;
    protected MessageContainer<Integer, Message> inbox;

    /**
     * @param username of the User
     * @param password of the User
     */
    protected User(String username, String password) {
        this.username = username;
        this.password = password;
        loginHistory = new ArrayList<>();
        outbox = new MessageContainer<>();
        inbox = new MessageContainer<>();
    }

    /**
     * @param username     of the User
     * @param password     of the User
     * @param loginHistory of the User
     */
    protected User(String username, String password, ArrayList<String> loginHistory) {
        this.username = username;
        this.password = password;
        this.loginHistory = loginHistory;
        outbox = new MessageContainer<>();
        inbox = new MessageContainer<>();
    }

    /**
     * @return the username of the User
     */
    public String getUsername() {
        return username;
    }

    /**
     * @return the password of the User
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the login history of the User
     */
    public ArrayList<String> getLoginHistory() {
        return loginHistory;
    }

    /**
     * @return admin status of the User
     */
    public Boolean getAdmin() {
        return isAdmin;
    }

    /**
     * @return whether the User is logged in
     */
    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    /**
     * @param newLogin to be added to the login history of the User
     */
    public void addToLoginHistory(String newLogin) {
        loginHistory.add(newLogin);
    }

    /**
     * @param bool to set login status to
     */
    public void setIsLoggedIn(boolean bool) {
        isLoggedIn = bool;
    }

    public MessageContainer<Integer, Message> getOutbox() {
        return outbox;
    }

    public MessageContainer<Integer, Message> getInbox() {
        return inbox;
    }

    public void sendMessage(User user, Message message) {
        user.inbox.put(message.getMessageID(), message);
        this.outbox.put(message.getMessageID(), message);
    }
}
