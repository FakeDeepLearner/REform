package useCases.messageUseCases;

import entities.AdminUser;
import entities.Message;
import entities.ReportMessage;
import entities.User;
import entities.containers.UserContainer;

import java.util.ArrayList;
import java.util.List;

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


}
