package useCases.miscellaneous;

import entities.Listing;
import entities.Message;
import entities.ReportMessage;
import entities.users.User;
import entities.containers.ListingContainer;
import entities.containers.MessageContainer;
import entities.containers.ReportContainer;
import entities.containers.UserContainer;
import gateways.*;
import useCases.listingUseCases.CreateListing;
import useCases.listingUseCases.FavoriteListing;
import useCases.messageUseCases.SendMessages;
import useCases.messageUseCases.SendReportMessage;
import useCases.userUseCases.*;

import java.io.IOException;

public class StoreData {
    private final SendMessages sendMessages;
    private final CreateListing createListing;
    private final FavoriteListing favoriteListing;
    private final UpdateUserHistory history;
    private final CreateBuyer createBuyer;
    private final CreateSeller createSeller;
    private final CreateAdmin createAdmin;
    private final SendReportMessage sendReportMessage;

    /**
     * Constructor for StoresUsers
     * @param users is the container that stores users of the program
     * @param messages is the container that stores messages between users
     * @param listings is the container that stores listings
     */
    public StoreData(UserContainer<String, User> users, MessageContainer<Integer, Message> messages,
                     ListingContainer<Integer, Listing> listings, ReportContainer<ReportMessage, Integer> reports) {
        BuyersCSVController buyersCSVController = new BuyersCSVController(users);
        SellersCSVController sellersCSVController = new SellersCSVController(users);
        AdminCSVController adminCSVController = new AdminCSVController(users);

        HistoriesCSVController historiesCSVController = new HistoriesCSVController(users);
        FavouritesCSVController favouritesCSVController = new FavouritesCSVController(users);
        ListingsCSVController listingsCSVController = new ListingsCSVController(users);
        MessagesCSVController messagesCSVController = new MessagesCSVController(messages);
        ReportsCSVController reportsCSVController = new ReportsCSVController(reports);

        createListing = new CreateListing(listings, users, listingsCSVController);
        sendMessages = new SendMessages(messages, users, messagesCSVController);
        favoriteListing = new FavoriteListing(users, favouritesCSVController);
        history = new UpdateUserHistory(users, historiesCSVController);
        createBuyer = new CreateBuyer(users, buyersCSVController);
        createSeller = new CreateSeller(users, sellersCSVController);
        createAdmin = new CreateAdmin(users, adminCSVController);
        sendReportMessage = new SendReportMessage(users, messages, reports, reportsCSVController);
    }

    /**
     * Loads stored users for a file
     * @throws IOException when the file is inaccessible
     */
    public void loadData() throws IOException {
        createBuyer.read();
        createSeller.read();
        createAdmin.read();
        history.read();
        sendMessages.read();
        favoriteListing.read();
        createListing.read();
        sendReportMessage.read();
    }

    /**
     * Saves the current program data in files
     * @throws IOException when the file is inaccessible
     */
    public void storeData() throws IOException {
        createBuyer.write();
        createSeller.write();
        createAdmin.write();
        history.write();
        sendMessages.write();
        favoriteListing.write();
        createListing.write();
        sendReportMessage.write();
    }
}
