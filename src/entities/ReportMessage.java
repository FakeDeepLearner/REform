package entities;

public non-sealed class ReportMessage extends Message{
    private boolean resolved;
    private final User reportedUser;
    /**
     * Create a report message
     *
     * @param sender    the sender of the message
     * @param recipient the recipient of the message
     * @param messageID the id of the message
     * @param contents  the contents of the message
     * @param reportedUser the user that is being reported. Note that the reported user doesn't receive the message
     */
    public ReportMessage(User sender, User recipient, Integer messageID, String contents, User reportedUser) {
        super(sender, recipient, messageID, contents);
        resolved = false;
        this.reportedUser = reportedUser;
    }

    /*
    public ReportMessage(User sender, User recipient, Integer messageID, String contents, String datetime, User reportedUser) {
        super(sender, recipient, messageID, contents, datetime);
        resolved = false;
        this.reportedUser = reportedUser;
    }
    */

    public ReportMessage(User sender, User recipient, Integer messageID, String contents,
                         String datetime, User reportedUser, boolean resolved) {
        super(sender, recipient, messageID, contents, datetime);
        this.resolved = resolved;
        this.reportedUser = reportedUser;
    }

    public void close() {
        resolved = true;
    }

    public void open(){
        resolved = false;
    }

    public boolean isResolved() {
        return resolved;
    }

    public User getReportedUser() {
        return reportedUser;
    }

    @Override
    public String toString() {
        String strToAdd;
        if(resolved){
            strToAdd = "(RESOLVED)";
        }
        else{
            strToAdd = "(PENDING)";
        }
        return "\n" + "REPORT MESSAGE" + strToAdd + "\n" + datetime.format(formatter) + "\n" +
                getSender().getUsername() + " reported " +
                getReportedUser().getUsername() + " (Message ID of " + getMessageID().toString() + ")" + "\n"
                + "The report message is:" + "\n" + getContents() + "\n";
    }

    @Override
    public void send() {
        getRecipient().addToInbox(getMessageID(), this);
    }
}
