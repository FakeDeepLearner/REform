package entities;

public class ReportMessage extends Message{
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

    public ReportMessage(User sender, User recipient, Integer messageID, String contents, String datetime, User reportedUser) {
        super(sender, recipient, messageID, contents, datetime);
        resolved = false;
        this.reportedUser = reportedUser;
    }

    public void resolve() {
        resolved = true;
    }

    public boolean isResolved() {
        return resolved;
    }

    public User getReportedUser() {
        return reportedUser;
    }

    @Override
    public String toString() {
        return "\n" + "REPORT MESSAGE" + "\n" + datetime.format(formatter) + "\n" +
                getSender().getUsername() + " reported " +
                getReportedUser().getUsername() + " (Message ID of " + getMessageID().toString() + ")" + "\n"
                + "The report message is:" + "\n" + getContents() + "\n";
    }
}
