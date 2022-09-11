import contollers.ProgramController;
import entities.Message;
import entities.ReportMessage;
import entities.User;
import entities.containers.ReportContainer;
import entities.containers.UserContainer;
import entities.Listing;
import entities.containers.ListingContainer;
import entities.containers.MessageContainer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        UserContainer<String, User> users = new UserContainer<>();
        MessageContainer<Integer, Message> messages = new MessageContainer<>();
        ListingContainer<Integer, Listing> listings = new ListingContainer<>();
        ReportContainer<ReportMessage, Integer> reports = new ReportContainer<>();

        ProgramController program = new ProgramController(users, messages, listings, reports);
        program.runProgram();
    }
}
