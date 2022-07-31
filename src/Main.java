import contollers.InputHandler;
import contollers.LoggedOutManager;
import contollers.UserInterface;
import contollers.UserManager;
import entities.User;
import entities.containers.UserContainer;
import exceptions.ExitProgramException;
import gateways.*;
import contollers.FavAndUnFavManager;
import contollers.LoggedInManager;
import entities.Listing;
import entities.containers.ListingContainer;
import entities.Message;
import entities.containers.MessageContainer;
import useCases.CSVUseCases.UsernamePasswordFileEditor;
import useCases.listingUseCases.CreateListing;
import useCases.listingUseCases.FavoriteListing;
import useCases.listingUseCases.ListingProperties;
import useCases.messageUseCases.SendMessages;
import useCases.userUseCases.AuthenticateUser;
import useCases.userUseCases.RestrictUser;
import useCases.userUseCases.UpdateUserHistory;
import useCases.userUseCases.UserFactory;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        UserContainer<String, User> users = new UserContainer<>();
        MessageContainer<Integer, Message> messages = new MessageContainer<>();
        ListingContainer<Integer, Listing> listings = new ListingContainer<>();

        UserFactory userFactory = new UserFactory(users);
        AuthenticateUser auth = new AuthenticateUser(users);
        UpdateUserHistory history = new UpdateUserHistory(users);
        UsernamePasswordFileEditor usernamePassword = new UsernamePasswordFileEditor(auth, users);
        RestrictUser restrict = new RestrictUser(users);
        UsernamePasswordFileEditor file = new UsernamePasswordFileEditor(auth, users);
        SendMessages sendMessages = new SendMessages(messages, users);
        ListingProperties listingProperties = new ListingProperties(listings);
        CreateListing createListing = new CreateListing(listings, users);
        FavoriteListing favoriteListing = new FavoriteListing(users);
        FavAndUnFavManager favAndUnFavManager = new FavAndUnFavManager(favoriteListing);

        UserInterface ui = new UserInterface();
        InputHandler input = new InputHandler(ui);
        UserManager userManager = new UserManager(input, ui, userFactory, auth, history, file);
        LoggedOutManager loggedOutManager = new LoggedOutManager(input, ui, userManager);
        LoggedInManager loggedInManager = new LoggedInManager(
                input, ui, auth, history, restrict, userManager, file, sendMessages, listingProperties, createListing,
        favAndUnFavManager);

        BuyersCSVController buyersCSVController = new BuyersCSVController(userFactory);
        SellersCSVController sellersCSVController = new SellersCSVController(userFactory);
        ListingsCSVController listingsCSVController = new ListingsCSVController(createListing);
        UserMessagesCSVController userMessagesCSVController = new UserMessagesCSVController(sendMessages);
        UsersAndFavoritesCSVController usersAndFavoritesCSVController = new UsersAndFavoritesCSVController(favoriteListing);

        buyersCSVController.read();
        sellersCSVController.read();

        ArrayList<ArrayList<String>> usersPassword = usernamePassword.getUsersFromCSV();
        for (ArrayList<String> userPassword : usersPassword) {
            if (userPassword.get(2).equals("true")) {
                userFactory.createUser("ADMIN", userPassword.get(0), userPassword.get(1));
            }
        }

        listingsCSVController.read();
        userMessagesCSVController.read();
        usersAndFavoritesCSVController.read();

        history.readUserHistories();

        while (true) {
            String username = null;
            try {
                username = loggedOutManager.menuSelector();
            } catch (ExitProgramException e) {
                buyersCSVController.write();
                sellersCSVController.write();
                listingsCSVController.write();
                userMessagesCSVController.write();
                usersAndFavoritesCSVController.write();
                history.overwriteUserHistories();

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
