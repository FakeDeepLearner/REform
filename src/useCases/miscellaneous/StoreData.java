package useCases.miscellaneous;

import entities.Listing;
import entities.Message;
import entities.User;
import entities.containers.ListingContainer;
import entities.containers.MessageContainer;
import entities.containers.UserContainer;
import gateways.*;
import useCases.listingUseCases.CreateListing;
import useCases.listingUseCases.FavoriteListing;
import useCases.messageUseCases.SendMessages;
import useCases.userUseCases.UpdateUserHistory;
import useCases.userUseCases.UserFactory;

import java.io.IOException;

public class StoreData {
    private final BuyersCSVController buyersCSVController;
    private final SellersCSVController sellersCSVController;
    private final UpdateUserHistory history;
    private final UsernamePasswordCSVController usernamePasswordCSVController;
    private final ListingsCSVController listingsCSVController;
    private final UserMessagesCSVController userMessagesCSVController;
    private final UsersAndFavoritesCSVController usersAndFavoritesCSVController;

    /**
     * Constructor for StoresUsers
     * @param users is the container that stores users of the program
     * @param messages is the container that stores messages between users
     * @param listings is the container that stores listings
     */
    public StoreData(UserContainer<String, User> users, MessageContainer<Integer, Message> messages,
                     ListingContainer<Integer, Listing> listings) {
        UserFactory userFactory = new UserFactory(users);
        buyersCSVController = new BuyersCSVController(users, userFactory);
        sellersCSVController = new SellersCSVController(users, userFactory);
        history = new UpdateUserHistory(users);
        listingsCSVController = new ListingsCSVController(users, new CreateListing(listings, users));
        userMessagesCSVController = new UserMessagesCSVController(new SendMessages(messages, users));
        usersAndFavoritesCSVController = new UsersAndFavoritesCSVController(new FavoriteListing(users, listings));
        usernamePasswordCSVController = new UsernamePasswordCSVController(users, userFactory);
    }

    /**
     * Loads stored users for a file
     * @throws IOException when the file is inaccessible
     */
    public void loadData() throws IOException {
        buyersCSVController.read();
        sellersCSVController.read();
        usernamePasswordCSVController.read();
        listingsCSVController.read();
        userMessagesCSVController.read();
        usersAndFavoritesCSVController.read();
        history.readUserHistories();
    }

    /**
     * Saves the current program data in files
     * @throws IOException when the file is inaccessible
     */
    public void storeData() throws IOException {
        buyersCSVController.write();
        sellersCSVController.write();
        listingsCSVController.write();
        usernamePasswordCSVController.write();
        userMessagesCSVController.write();
        usersAndFavoritesCSVController.write();
        history.overwriteUserHistories();
    }
}
