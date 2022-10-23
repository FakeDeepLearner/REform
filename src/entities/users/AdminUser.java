package entities.users;

import entities.Message;
import entities.ReportMessage;

public non-sealed class AdminUser extends User {

    /**
     * Creates an instance of an AdminUser
     *
     * @param username of the User
     * @param password of the User
     */
    public AdminUser(String username, String password) {
        super(username, password);
        this.isAdmin = true;
    }

    private boolean inboxContainsOpenReports(){
        for (Message message : inbox.values()) {
            if (message instanceof ReportMessage report && !(report.isResolved())) {
                return true;
            }
        }
        return false;
    }

    public boolean isNotify() {
        return inboxContainsOpenReports();
    }
}