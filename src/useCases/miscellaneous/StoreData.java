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
import java.math.BigDecimal;

public class StoreData {
    private final BuyersCSVController buyersCSVController;
    private final SellersCSVController sellersCSVController;
    private final AdminCSVController adminCSVController;
    private final HistoriesCSVController historiesCSVController;
    private final ListingsCSVController listingsCSVController;
    private final MessagesCSVController messagesCSVController;
    private final FavouritesCSVController favouritesCSVController;

    private final UserFactory userFactory;
    private final SendMessages sendMessages;
    private final CreateListing createListing;
    private final FavoriteListing favoriteListing;
    private final UpdateUserHistory history;

    /**
     * Constructor for StoresUsers
     * @param users is the container that stores users of the program
     * @param messages is the container that stores messages between users
     * @param listings is the container that stores listings
     */
    public StoreData(UserContainer<String, User> users, MessageContainer<Integer, Message> messages,
                     ListingContainer<Integer, Listing> listings) {
        buyersCSVController = new BuyersCSVController(users);
        sellersCSVController = new SellersCSVController(users);
        adminCSVController = new AdminCSVController(users);

        historiesCSVController = new HistoriesCSVController(users);
        favouritesCSVController = new FavouritesCSVController(users);
        listingsCSVController = new ListingsCSVController(users);
        messagesCSVController = new MessagesCSVController(messages);

        userFactory = new UserFactory(users);
        createListing = new CreateListing(listings, users, listingsCSVController);
        sendMessages = new SendMessages(messages, users, messagesCSVController);
        favoriteListing = new FavoriteListing(users, listings, favouritesCSVController);
        history = new UpdateUserHistory(users, historiesCSVController);
    }

    /**
     * Loads stored users for a file
     * @throws IOException when the file is inaccessible
     */
    public void loadData() throws IOException {
        for (String[] data : buyersCSVController.read()) {
            userFactory.createUser("buyer", data[0], data[1]);
        }

        for (String[] data : sellersCSVController.read()) {
            userFactory.createUser("seller", data[0], data[1]);
        }

        for (String[] data : adminCSVController.read()) {
            userFactory.createUser("admin", data[0], data[1]);
        }

        for (String[] data : historiesCSVController.read()) {
            history.addLoginHistory(data[0], data[1]);
        }

        for (String[] data : messagesCSVController.read()) {
            sendMessages.addMessage(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4]);
        }

        for (String[] data : favouritesCSVController.read()) {
            favoriteListing.addListingToBuyerFavorites(data[0], Integer.parseInt(data[1]));
            favoriteListing.addCreationToGeneratedFavorites(data[0], Integer.parseInt(data[1]));
        }

        for (String[] data : listingsCSVController.read()) {
            String username = data[0];
            int ID = Integer.parseInt(data[1]);
            int civicAddress = Integer.parseInt(data[2]);
            String streetName = data[3];
            String city = data[4];
            String type = data[5];
            int bedrooms = Integer.parseInt(data[6]);
            int bathrooms = Integer.parseInt(data[7]);
            BigDecimal price = new BigDecimal(data[8]);
            String info = data[9];

            boolean isUnit = Boolean.parseBoolean(data[10]);
            int unitNumberFloor = Integer.parseInt(data[11]);
            if (isUnit) {
                Listing listing = createListing.addListing(ID, unitNumberFloor, civicAddress, streetName, city, type,
                        bedrooms, bathrooms, price, info);
                createListing.addListingToSeller(username, listing);
                createListing.addListingToCreatedListings(username, listing);
            }
            else {
                Listing listing = createListing.addListing(ID, civicAddress, streetName, city, type, bedrooms,
                        unitNumberFloor, bathrooms, price, info);
                createListing.addListingToSeller(username, listing);
                createListing.addListingToCreatedListings(username, listing);
            }
        }
    }

    /**
     * Saves the current program data in files
     * @throws IOException when the file is inaccessible
     */
    public void storeData() throws IOException {
        buyersCSVController.write();
        sellersCSVController.write();
        adminCSVController.write();

        historiesCSVController.read();
        messagesCSVController.write();
        listingsCSVController.write();
        favouritesCSVController.write();
    }
}
