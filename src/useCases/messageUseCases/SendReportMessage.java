package useCases.messageUseCases;

import entities.users.AdminUser;
import entities.Message;
import entities.ReportMessage;
import entities.users.User;
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
        sendMessageToAllAdminsFromProgram(contents, reportingUser, reportedUser, allAdmins, messageID);
    }

    private void sendReportFrom(String reportingUsername, String reportedUsername, String contents, Integer messageID,
                                String datetime, boolean resolved){
        User reportingUser = userContainer.get(reportingUsername);
        User reportedUser = userContainer.get(reportedUsername);
        Container<String, AdminUser> allAdmins = userContainer.getAllAdmins();
        sendMessageToAllAdminsWithDatetime(contents, reportingUser, reportedUser, allAdmins, messageID, datetime, resolved);
    }

    private void sendMessageToAllAdminsFromProgram (String contents, User reportingUser, User reportedUser,
                                        Container<String, AdminUser> allAdmins, Integer messageID) {
        for(String s : allAdmins.keySet()){
            AdminUser admin = allAdmins.get(s);
            ReportMessage reportMessage = new ReportMessage(reportingUser, admin, messageID, contents, reportedUser);
            reportMessage.send();
            if (!reportContainer.containsValue(messageID)) {
                reportContainer.put(reportMessage, messageID);
            }
        }
    }

    private void sendMessageToAllAdminsWithDatetime(String contents, User reportingUser, User reportedUser,
                                                    Container<String, AdminUser> allAdmins, Integer messageID,
                                                    String datetime, boolean resolved){
        for(String s : allAdmins.keySet()){
            AdminUser admin = allAdmins.get(s);
            ReportMessage reportMessage = new ReportMessage(reportingUser, admin, messageID,
                    contents, datetime, reportedUser, resolved);
            reportMessage.send();
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
            String datetime = line[4];
            boolean resolved = Boolean.parseBoolean(line[5]);
            sendReportFrom(reportingUsername, reportedUsername, messageContents, messageID, datetime, resolved);
        }
    }

    public void write() throws IOException{
        i.write();
    }
}
