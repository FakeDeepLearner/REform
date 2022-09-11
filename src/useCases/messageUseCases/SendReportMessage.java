package useCases.messageUseCases;

import entities.AdminUser;
import entities.Message;
import entities.ReportMessage;
import entities.User;
import entities.containers.Container;
import entities.containers.MessageContainer;
import entities.containers.ReportContainer;
import entities.containers.UserContainer;
import gateways.DataInterface;
import useCases.miscellaneous.GenerateUniqueID;

import java.io.IOException;



public class SendReportMessage{

    private final UserContainer<String, User> userContainer;
    private final MessageContainer<Integer, Message> messageContainer;
    private final ReportContainer<ReportMessage, Integer> reportContainer;
    private final DataInterface i;

    public SendReportMessage(UserContainer<String, User> userContainer,
                             MessageContainer<Integer, Message> messageContainer,
                             ReportContainer<ReportMessage, Integer> reportContainer,
                  DataInterface i){
        this.userContainer = userContainer;
        this.messageContainer = messageContainer;
        this.reportContainer = reportContainer;
        this.i = i;
    }

    public void sendReportFrom(String reportingUsername, String reportedUsername, String contents){
        GenerateUniqueID generateUniqueID = new GenerateUniqueID(messageContainer);
        User reportingUser = userContainer.get(reportingUsername);
        User reportedUser = userContainer.get(reportedUsername);
        Container<String, AdminUser> allAdmins = userContainer.getAllAdmins();
        Integer messageID = generateUniqueID.getUniqueID();
        sendMessageToAllAdmins(contents, reportingUser, reportedUser, allAdmins, messageID);
    }

    private void sendReportFrom(String reportingUsername, String reportedUsername, String contents, Integer messageID){
        User reportingUser = userContainer.get(reportingUsername);
        User reportedUser = userContainer.get(reportedUsername);
        Container<String, AdminUser> allAdmins = userContainer.getAllAdmins();
        sendMessageToAllAdmins(contents, reportingUser, reportedUser, allAdmins, messageID);
    }

    //TODO: Fix the bug of some admins not getting all the reports
    private void sendMessageToAllAdmins (String contents, User reportingUser, User reportedUser,
                                        Container<String, AdminUser> allAdmins, Integer messageID) {
        for(String s : allAdmins.keySet()){
            AdminUser admin = allAdmins.get(s);
            ReportMessage reportMessage = new ReportMessage(reportingUser, admin, messageID, contents, reportedUser);
            reportingUser.sendMessage(admin, reportMessage);
            if (!reportContainer.containsValue(messageID)) {
                reportContainer.put(reportMessage, messageID);
            }
        }
    }


    public void read() throws IOException {
        for (String[] line : i.read()){
            String reportingUsername = line[0];
            String reportedUsername = line[1];
            String messageContents = line[2];
            Integer messageID = Integer.parseInt(line[3]);
            sendReportFrom(reportingUsername, reportedUsername, messageContents, messageID);
        }
    }

    public void write() throws IOException{
        i.write();
    }
}
