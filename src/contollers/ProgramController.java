package contollers;

import entities.Listing;
import entities.Message;
import entities.User;
import entities.containers.ListingContainer;
import entities.containers.MessageContainer;
import entities.containers.UserContainer;
import exceptions.ExitProgramException;
import useCases.miscellaneous.StoreData;

import java.io.IOException;

public class ProgramController {
    private final LoggedOutManager loggedOutManager;
    private final LoggedInManager loggedInManager;
    private final StoreData storeUsers;


    /**
     * Constructor for ProgramController
     * @param users is the container that stores users of the program
     * @param messages is the container that stores messages between users
     * @param listings is the container that stores listings
     */
    public ProgramController(UserContainer<String, User> users, MessageContainer<Integer, Message> messages,
                             ListingContainer<Integer, Listing> listings) {
        loggedOutManager = new LoggedOutManager(users);
        loggedInManager = new LoggedInManager(users, messages, listings);
        storeUsers = new StoreData(users, messages, listings);
    }

    /**
     * Controls the overall flow of the program
     * @throws IOException when the save file is inaccessible
     */
    public void runProgram() throws IOException {
        storeUsers.loadData();

        while (true) {
            String username = null;
            try {
                username = loggedOutManager.menuSelector();
            } catch (ExitProgramException e) {
                storeUsers.storeData();
                System.exit(0);
            }

            if (username == null) {
                continue;
            }

            boolean isLoggedIn = true;
            while (isLoggedIn) {
                isLoggedIn = loggedInManager.userScreen(username);
            }
        }
    }
}
