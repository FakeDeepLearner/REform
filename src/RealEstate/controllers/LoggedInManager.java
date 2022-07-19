package RealEstate.controllers;

import LoginSystem.controllers.InputHandler;
import LoginSystem.controllers.UserInterface;
import LoginSystem.controllers.UserManager;
import LoginSystem.exceptions.UserCannotBeBannedException;
import LoginSystem.exceptions.UserNotFoundException;
import LoginSystem.useCases.AuthenticateUser;
import LoginSystem.useCases.RestrictUser;
import LoginSystem.useCases.UpdateUserHistory;
import LoginSystem.useCases.UsernamePasswordFileEditor;
import RealEstate.useCases.CreateListing;
import RealEstate.useCases.ListingProperties;
import RealEstate.useCases.SendMessages;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class LoggedInManager {
    private final InputHandler input;
    private final UserInterface ui;
    private final AuthenticateUser auth;
    private final UpdateUserHistory history;
    private final RestrictUser restrict;
    private final UserManager userManager;
    private final UsernamePasswordFileEditor file;
    private final SendMessages sendMessages;
    private final ListingProperties listingProperties;
    private final CreateListing createListing;


    public LoggedInManager(InputHandler input, UserInterface ui, AuthenticateUser auth,
                           UpdateUserHistory history, RestrictUser restrict,
                           UserManager userManager, UsernamePasswordFileEditor file,
                           SendMessages sendMessages, ListingProperties listingProperties,
                           CreateListing createListing) {
        this.input = input;
        this.ui = ui;
        this.auth = auth;
        this.history = history;
        this.restrict = restrict;
        this.userManager = userManager;
        this.file = file;
        this.sendMessages = sendMessages;
        this.listingProperties = listingProperties;
        this.createListing = createListing;
    }

    public boolean userScreen(String username) {
        if (!auth.checkUserAdmin(username)) {
            if (!auth.checkUserSeller(username)) {
                return buyerScreen(username);
            } else {
                return sellerScreen(username);
            }
        } else {
            return adminScreen(username);
        }
    }

    public boolean buyerScreen(String username) {
        ui.printBuyerLoginMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5, 6);

        int select = input.intInput(allowedInputs);
        switch (select) {
            case 1:
                // View listings
                searchProperties();
                break;
            case 2:
                // Send message
                messageUser(username);
                break;
            case 3:
                // View inbox
                viewInbox(username);
                break;
            case 4:
                // View outbox
                viewOutbox(username);
                break;
            case 5:
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                ui.printLoginHistory(userHistory);
                break;
            case 6:
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                return false;
        }

        return true;
    }

    public boolean sellerScreen(String username) {
        ui.printSellerLoginMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5, 6, 7, 8);

        int select = input.intInput(allowedInputs);
        switch (select) {
            case 1:
                // View posted listings
                ui.printFilteredListings(createListing.getSellerListingsStrings(username));
                break;
            case 2:
                // Message user
                messageUser(username);
                break;
            case 3:
                // View inbox
                viewInbox(username);
                break;
            case 4:
                // View outbox
                viewOutbox(username);
                break;
            case 5:
                // Create listing
                createNewListing(username);
                break;
            case 6:
                // Delete listing
                deleteListing(username);
                break;
            case 7:
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                ui.printLoginHistory(userHistory);
                break;
            case 8:
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                return false;
        }

        return true;
    }

    private void updateUserBanStatus() {
        ui.printRestrictUsernameInput();
        String restrictUser = input.strInput();

        boolean isBanned;
        try {
            isBanned = restrict.isUserBanned(restrictUser);
        } catch (UserCannotBeBannedException | UserNotFoundException e) {
            ui.printArbitraryException(e);
            return;
        }

        ui.printRestrictUserConfirmation(restrictUser, isBanned);

        ArrayList<String> allowedStrings = new ArrayList<>();
        Collections.addAll(allowedStrings, "Y", "N");

        String choice = input.strInput(allowedStrings);
        if (choice.equals("Y")) {
            if (!isBanned) {
                restrict.banNonAdminUser(restrictUser);
            } else {
                restrict.unbanNonAdminUser(restrictUser);
            }
        }
    }

    private void deleteUser() {
        ui.printDeleteUsernameInput();
        String deleteUser = input.strInput();
        boolean deleted = restrict.deleteNonAdminUser(deleteUser);
        if (deleted) {
            try {
                file.createUsernamePasswordFile();
            } catch (IOException e) {
                ui.printArbitraryException(e);
            }

            history.overwriteUserHistories();
            ui.printDeleteUserSuccess(deleteUser);
        } else {
            ui.printDeleteUserFail(deleteUser);
        }
    }

    private void messageUser(String username) {
        ui.printMessageUsernameInput();
        String recipient = input.strInput();

        boolean userExists = auth.checkUserNonAdminExists(recipient);

        if (userExists) {
            ui.printMessageInput();
            String content = input.strInput();

            sendMessages.sendMessage(username, recipient, content);

            ui.printMessageSentSuccess();

        } else {
            ui.printMessageUserFail(recipient);
        }
    }

    private void createNewListing(String username) {
        ui.printListingHasUnitNumber();
        ArrayList<String> allowedStrings = new ArrayList<>();
        Collections.addAll(allowedStrings, "Y", "N");
        String choice = input.strInput(allowedStrings);

        ui.printEnterType("civic address number");
        int number = input.intInput();
        ui.printEnterType("street name");
        String street = input.strInput();
        ui.printEnterType("city");
        String city = input.strInput();
        ui.printEnterType("number of bedrooms");
        int bedrooms = input.intInput();
        ui.printEnterType("number of bathrooms");
        int bathrooms = input.intInput();
        ui.printEnterType("price");
        BigDecimal price = input.bigDecimalInput();
        ui.printEnterType("listing type (apartment, house, townhouse)");
        String[] inputsAllowed = {"apartment", "house", "townhouse"};
        String type = input.strInputCaseInsensitive(List.of(inputsAllowed));

        if (Objects.equals(choice, "Y")) {
            ui.printEnterType("unit number");
            int unitNumber = input.intInput();
            ui.printEnterType("number of floors");
            int floors = input.intInput();
            createListing.addListingToSeller(username, createListing.addListing(unitNumber, number, street, city,
                    type, bedrooms, bathrooms, floors, price));
        } else {
            createListing.addListingToSeller(username, createListing.addListing(number, street, city,
                    type, bedrooms, bathrooms, price));
        }
        ui.printListingCreatedSuccess();
    }

    private void deleteListing(String username) {
        ArrayList<String> listingStrings = createListing.getSellerListingsStrings(username);
        ui.printNumberedListings(listingStrings);
        ui.printEnterType("number of property to delete");
        int number = input.intInput(1, listingStrings.size());
        createListing.deleteListing(username, createListing.getSellerListings(username).get(number - 1));

    }

    private void searchProperties() {
        ui.printListingOptionsMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5, 6, 7, 8);

        int select = input.intInput(allowedInputs);
        switch (select) {
            case 1: {
                // Search by civic address
                ui.printEnterType("civic address number");
                int number = input.intInput();
                ArrayList<String> listings = listingProperties.SearchByCivicAddress(number);
                ui.printFilteredListings(listings);
                break;
            }

            case 2: {
                // Search by street name
                ui.printEnterType("street name");
                String street = input.strInput();
                ArrayList<String> listings = listingProperties.SearchByStreetName(street);
                ui.printFilteredListings(listings);
                break;
            }

            case 3: {
                // Search by city
                ui.printEnterType("city");
                String city = input.strInput();
                ArrayList<String> listings = listingProperties.SearchByCity(city);
                ui.printFilteredListings(listings);
                break;
            }

            case 4: {
                // Search by bedroom number
                ui.printEnterType("number of bedrooms wanted");
                int number = input.intInput();
                ArrayList<String> listings = listingProperties.SearchByBedrooms(number);
                ui.printFilteredListings(listings);
                break;
            }

            case 5: {
                // Search by bathroom number
                ui.printEnterType("number of bathrooms wanted");
                int number = input.intInput();
                ArrayList<String> listings = listingProperties.SearchByBathrooms(number);
                ui.printFilteredListings(listings);
                break;
            }

            case 6: {
                // Search by floor number
                ui.printEnterType("number of floors wanted");
                int number = input.intInput();
                ArrayList<String> listings = listingProperties.SearchByFloors(number);
                ui.printFilteredListings(listings);
                break;
            }

            case 7: {
                // Search by price
                ui.printEnterType("minimum price");
                BigDecimal min = input.bigDecimalInput();
                ui.printEnterType("maximum price");
                BigDecimal max = input.bigDecimalInput();
                ArrayList<String> listings = listingProperties.SearchByPrice(min, max);
                ui.printFilteredListings(listings);
                break;
            }

            case 8: {
                // Search by listing type
                ui.printEnterType("listing type (apartment, house, townhouse) wanted");
                String[] inputsAllowed = {"apartment", "house", "townhouse"};
                String type = input.strInput(List.of(inputsAllowed));
                ArrayList<String> listings = listingProperties.SearchByListingType(type);
                ui.printFilteredListings(listings);
                break;
            }
        }
    }

    public void viewInbox(String username) {
        ui.printMessages(sendMessages.getMessageInbox(username));
    }

    public void viewOutbox(String username) {
        ui.printMessages(sendMessages.getMessageOutbox(username));
    }

    public boolean adminScreen(String username) {
        ui.printAdminLoginMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5);

        int select = input.intInput(allowedInputs);
        switch (select) {
            case 1: {
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                ui.printLoginHistory(userHistory);
                break;
            }

            case 2: {
                // Create new admin user
                userManager.createNewUser("ADMIN");
                break;
            }

            case 3: {
                // Ban or unban user
                updateUserBanStatus();
                break;
            }

            case 4: {
                // Delete user
                deleteUser();
                break;
            }

            case 5: {
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                return false;
            }
        }

        return true;
    }
}
