package contollers;

import entities.Message;
import entities.ReportMessage;
import entities.users.AdminUser;
import entities.users.User;
import entities.containers.ListingContainer;
import entities.containers.MessageContainer;
import entities.containers.ReportContainer;
import entities.containers.UserContainer;
import exceptions.*;
import gateways.HistoriesCSVController;
import gateways.ListingsCSVController;
import gateways.MessagesCSVController;
import gateways.ReportsCSVController;
import useCases.listingUseCases.ViewListings;
import useCases.messageUseCases.*;
import useCases.userUseCases.AuthenticateUser;
import useCases.userUseCases.RestrictUser;
import useCases.userUseCases.UpdateUserHistory;
import entities.Listing;
import useCases.listingUseCases.CreateListing;
import useCases.listingUseCases.ListingProperties;

import java.math.BigDecimal;
import java.util.*;

public class LoggedInManager {
    private final InputHandler input;
    private final UserInterface ui;
    private final AuthenticateUser auth;
    private final UpdateUserHistory history;
    private final RestrictUser restrict;
    private final UserManager userManager;
    private final SendMessages sendMessages;
    private final ListingProperties listingProperties;
    private final CreateListing createListing;
    private final FavAndUnFavManager favAndUnFavManager;
    private final MessageChat messageChat;
    private final SendReportMessage sendReportMessage;
    private final ViewListings viewListings;
    private final OpenAndCloseReports openAndCloseReports;
    private final ViewMessages viewMessages;
    private final ListingUpdateManager listingUpdateManager;

    /**
     * Constructor for LoggedInManager
     * @param users is the container that stores users of the program
     * @param messages is the container that stores messages between users
     * @param listings is the container that stores listings
     */
    public LoggedInManager(UserContainer<String, User> users, MessageContainer<Integer, Message> messages,
                           ListingContainer<Integer, Listing> listings, ReportContainer<ReportMessage, Integer> reports) {

        auth = new AuthenticateUser(users);
        history = new UpdateUserHistory(users, new HistoriesCSVController(users));
        restrict = new RestrictUser(users);
        sendMessages = new SendMessages(messages, users, new MessagesCSVController(messages));
        listingProperties = new ListingProperties(listings);
        createListing = new CreateListing(listings, users, new ListingsCSVController(users));
        favAndUnFavManager = new FavAndUnFavManager(users, listings);
        userManager = new UserManager(users);

        ui = new UserInterface();
        input = new InputHandler(ui);
        messageChat = new MessageChat(messages);
        sendReportMessage = new SendReportMessage(users, messages, reports, new ReportsCSVController(reports));
        viewListings = new ViewListings(users, listings);
        openAndCloseReports = new OpenAndCloseReports();
        viewMessages = new ViewMessages(users);
        listingUpdateManager = new ListingUpdateManager(listings);
    }

    /**
     * Calls the appropriate screen depending on what type of user is logged in
     *
     * @param username the logged-in user's username
     * @return true if the correct user screen successfully appears
     */
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

    /**
     * Presents buyer with options to navigate through program
     *
     * @param username the logged-in user's username
     * @return true if the buyer screen successfully appears
     */
    public boolean buyerScreen(String username) {
        ui.printBuyerLoginMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int select = input.intInput(allowedInputs);
        switch (select) {
            case 1:
                // View listings
                searchProperties(username);
                break;
            case 2:
                viewAllListings();
                break;
            case 3:
                // View favourite listings
                viewFavouriteListings(username);
                checkDeleteFavouriteListing(username);
                break;
            case 4:
                // Send message
                messageUser(username);
                break;
            case 5:
                // View inbox
                viewInbox(username);
                break;
            case 6:
                // View outbox
                viewOutbox(username);
                break;
            case 7:
                reportUser(username);
                break;
            case 8:
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                ui.printLoginHistory(userHistory);
                break;
            case 9:
                //View chat history
                viewMessageChat(username);
                break;
            case 10:
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                return false;
        }

        return true;
    }

    /**
     * Presents seller with options to navigate through program
     *
     * @param username the logged-in user's username
     * @return true if the seller screen successfully appears
     */
    public boolean sellerScreen(String username) {
        ui.printSellerLoginMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);

        int select = input.intInput(allowedInputs);
        switch (select) {
            case 1:
                // View posted listings
                ui.printFilteredListings(viewListings.getSellerListingsStrings(username));
                break;
            case 2:
                updateUserListing(username);
                break;
            case 3:
                // Message user
                messageUser(username);
                break;
            case 4:
                // View inbox
                viewInbox(username);
                break;
            case 5:
                // View outbox
                viewOutbox(username);
                break;
            case 6:
                reportUser(username);
                break;
            case 7:
                // Create listing
                createNewListing(username);
                break;
            case 8:
                // Delete listing
                deleteListing(username);
                break;
            case 9:
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                ui.printLoginHistory(userHistory);
                break;
            case 10:
                //View chat history
                viewMessageChat(username);
                break;
            case 11:
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                return false;
        }

        return true;
    }

    /**
     * Bans specified user
     */
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

    /**
     * Deletes specified user
     */
    private void deleteUser() {
        ui.printDeleteUsernameInput();
        String deleteUser = input.strInput();
        sendMessages.clearMessages(deleteUser);
        boolean deleted = restrict.deleteNonAdminUser(deleteUser);
        if (deleted) {
            ui.printDeleteUserSuccess(deleteUser);
        } else {
            ui.printDeleteUserFail(deleteUser);
        }
    }

    /**
     * Sends a message from the logged-in user to a specified recipient
     *
     * @param username the username of the logged-in user
     */
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

    private void reportUser(String username){
        ui.printEnterType("the username of the user you wish to report");
        String reportedUsername = input.strInput();
        ui.printEnterType("a report message");
        String reportMessage = input.strInput();
        sendReportMessage.sendReportFrom(username, reportedUsername, reportMessage);
        ui.printReportSuccess();
    }

    /**
     * Creates a new listing with the specified properties
     *
     * @param username the username of the logged-in user
     */
    private void createNewListing(String username) {
        ui.printListingHasUnitNumber();
        ArrayList<String> allowedStrings = new ArrayList<>();
        Collections.addAll(allowedStrings, "Y", "N");
        String choice = input.strInput(allowedStrings);

        int unitNumberFloors = 0;
        if (choice.equals("Y")) {
            ui.printEnterType("unit number");
            unitNumberFloors = input.intInput();
        }

        ui.printEnterType("civic address number");
        int number = input.intInput();

        ui.printEnterType("street name");
        String street = input.strInput();

        ui.printEnterType("city");
        String city = input.strInput();

        ui.printEnterType("price");
        BigDecimal price = input.bigDecimalInput();

        ui.printEnterType("listing type (apartment, house, townhouse)");
        String[] inputsAllowed = {"apartment", "house", "townhouse"};
        String type = input.strInput(List.of(inputsAllowed), true);

        if (!choice.equals("Y")) {
            ui.printEnterType("number of floors");
            unitNumberFloors = input.intInput();
        }

        ui.printEnterType("number of bedrooms");
        int bedrooms = input.intInput();

        ui.printEnterType("number of bathrooms");
        int bathrooms = input.intInput();


        ui.printEnterType("additional information about the listing");
        String info = input.strInput();

        Listing listing;
        if (choice.equals("Y")) {
            listing = createListing.addListing(unitNumberFloors, number, street, city, type, bedrooms, bathrooms, price, info);
        } else {
            listing = createListing.addListing(number, street, city, type, bedrooms, bathrooms, unitNumberFloors, price, info);
        }

        createListing.addListingToSeller(username, listing);
        createListing.addListingToCreatedListings(username, listing);

        ui.printListingCreatedSuccess();
    }

    /**
     * Deletes a specified listing posted by the logged-in user
     *
     * @param username the username of the logged-in user
     */
    private void deleteListing(String username) {
        List<String> listingStrings = viewListings.getSellerListingsStrings(username);
        ui.printNumberedListings(listingStrings);
        ui.printEnterType("number of property to delete");
        int number = input.intInput(1, listingStrings.size());
        Listing listingToRemove = viewListings.getSellerListings(username).get(number - 1);
        createListing.deleteListing(username, listingToRemove);
        createListing.removeFromCreatedListings(username, listingToRemove);

        ui.printDeleteListingSuccess();
    }

    private void checkAddListingToFavourites(String username, ArrayList<Listing> listings){
        ArrayList<Integer> listingID = listingProperties.getListingsID(listings);

        if(listingID.size() > 0) {
            ui.printAddListingToFavourites();
            ArrayList<String> allowedStrings = new ArrayList<>();
            Collections.addAll(allowedStrings, "Y", "N");
            String choice = input.strInput(allowedStrings);

            if (choice.equals("Y")) {
                ui.printEnterType("number of listing to add");
                int number = input.intInput(1, listings.size());
                try {
                    favAndUnFavManager.addToBuyerFavorites(username, listingID.get(number - 1));
                    ui.printListingFavouriteSuccess();
                }
                catch(ListingAlreadyFavouritedException exception){
                    ui.printArbitraryException(exception);
                }
            }
        }
    }

    private void checkDeleteFavouriteListing(String username){
        List<Integer> listingIDs = favAndUnFavManager.getBuyerFavouritesID(username);

        if(listingIDs.size() > 0){
            ui.printDeleteListingFromFavourites();
            ArrayList<String> allowedStrings = new ArrayList<>();
            Collections.addAll(allowedStrings, "Y", "N");
            String choice = input.strInput(allowedStrings);

            if (choice.equals("Y")) {
                ui.printEnterType("number of listing you want to remove");
                int number = input.intInput(1, listingIDs.size());
                favAndUnFavManager.removeFromBuyerFavorites(username, number - 1);
                ui.printFavouriteRemovedSuccess();
            }
        }
        else{
            ui.printNoFavourites();
        }
    }

    /**
     * Displays available listings based on specific properties
     */
    private void searchProperties(String username) {
        ui.printListingOptionsMenu();

        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5, 6, 7, 8);

        int select = input.intInput(allowedInputs);
        switch (select) {
            case 1: {
                // Search by civic address
                ui.printEnterType("civic address number");
                int number = input.intInput();
                ArrayList<Listing> listings = listingProperties.searchByCivicAddress(number);
                ui.printNumberedListings(listingProperties.getListingsStrings(listings));
                checkAddListingToFavourites(username, listings);
                break;
            }

            case 2: {
                // Search by street name
                ui.printEnterType("street name");
                String street = input.strInput();
                ArrayList<Listing> listings = listingProperties.searchByStreetName(street);
                ui.printNumberedListings(listingProperties.getListingsStrings(listings));
                checkAddListingToFavourites(username, listings);
                break;
            }

            case 3: {
                // Search by city
                ui.printEnterType("city");
                String city = input.strInput();
                ArrayList<Listing> listings = listingProperties.searchByCity(city);
                ui.printNumberedListings(listingProperties.getListingsStrings(listings));
                checkAddListingToFavourites(username, listings);
                break;
            }

            case 4: {
                // Search by bedroom number
                ui.printEnterType("number of bedrooms wanted");
                int number = input.intInput();
                ArrayList<Listing> listings = listingProperties.searchByBedrooms(number);
                ui.printNumberedListings(listingProperties.getListingsStrings(listings));
                checkAddListingToFavourites(username, listings);
                break;
            }

            case 5: {
                // Search by bathroom number
                ui.printEnterType("number of bathrooms wanted");
                int number = input.intInput();
                ArrayList<Listing> listings = listingProperties.searchByBathrooms(number);
                ui.printNumberedListings(listingProperties.getListingsStrings(listings));
                checkAddListingToFavourites(username, listings);
                break;
            }

            case 6: {
                // Search by floor number
                ui.printEnterType("number of floors wanted");
                int number = input.intInput();
                ArrayList<Listing> listings = listingProperties.searchByFloors(number);
                ui.printNumberedListings(listingProperties.getListingsStrings(listings));
                checkAddListingToFavourites(username, listings);
                break;
            }

            case 7: {
                // Search by price
                ui.printEnterType("minimum price");
                BigDecimal min = input.bigDecimalInput();
                ui.printEnterType("maximum price");
                BigDecimal max = input.bigDecimalInput();

                ui.printEnterType("Sort By 1-Ascending 2-Descending");
                ArrayList<Integer> allowedInputSort = new ArrayList<>(Arrays. asList(1, 2));
                int order = input.intInput(allowedInputSort);
                ArrayList<Listing> listings = listingProperties.searchByPrice(max, min, order);
                ui.printNumberedListings(listingProperties.getListingsStrings(listings));
                checkAddListingToFavourites(username, listings);
                break;
            }

            case 8: {
                // Search by listing type
                ui.printEnterType("listing type (apartment, house, townhouse) wanted");
                String[] inputsAllowed = {"apartment", "house", "townhouse"};
                String type = input.strInput(List.of(inputsAllowed));
                ArrayList<Listing> listings = listingProperties.searchByListingType(type);
                ui.printNumberedListings(listingProperties.getListingsStrings(listings));
                checkAddListingToFavourites(username, listings);
                break;
            }
        }
    }

    private void viewMessageChat(String username){
        ui.printEnterType("the username of the user you want to see your message history with");
        String otherUsername = input.strInput();
        messageChat.printChatHistory(username, otherUsername);
    }

    private void viewMessageChatAdminVersion(){
        ui.printEnterType("the first username");
        String firstUsername = input.strInput();
        ui.printEnterType("the second username");
        String secondUsername = input.strInput();
        messageChat.printChatHistory(firstUsername, secondUsername);
    }

    private void displaySellerListings(){
        ui.printEnterType("the seller you want to see the listings of");
        String username = input.strInput();
        try{
            List<String> printedListings =  viewListings.getSellerListingsStrings(username);
            ui.printNumberedListings(printedListings);
        }
        catch (UserIsNotASellerException exception){
            ui.printArbitraryException(exception);
            displaySellerListings();
        }
    }

    private void changeReportStatus(String username){
        List<String> adminReportsStrings = viewMessages.getAdminReportsStrings(username);
        List<ReportMessage> adminReports = viewMessages.getAdminReports(username);
        ui.printNumberedMessages(adminReportsStrings);
        ui.printEnterType("the number of the message you want to change the status of.");
        ui.printReportStatusChangeDisclaimer();
        int number = input.intInput(1, adminReportsStrings.size());
        ReportMessage messageToUpdate = adminReports.get(number - 1);
        if(messageToUpdate.isResolved()){
            openAndCloseReports.openReport(messageToUpdate);
        }
        else{
            openAndCloseReports.closeReport(messageToUpdate);
            checkReportRemoval(messageToUpdate);
        }
        ui.printReportStatusChangeSuccess();
    }

    private void checkReportRemoval(ReportMessage message){
        ui.printReportRemovalConfirmation();
        String confirmation = input.strInput(Arrays.asList("Y", "N"), false);
        if(confirmation.equals("Y")){
            sendReportMessage.removeReportMessage(message);
            ui.printReportRemovalSuccess();
        }
    }

    private void updateUserListing(String username){
        List<Listing> sellerListings = viewListings.getSellerListings(username);
        List<String> sellerListingStrings = viewListings.getSellerListingsStrings(username);
        ui.printNumberedListings(sellerListingStrings);
        ui.printEnterType("the number of the listing you want to modify");
        int numListing = input.intInput(1, sellerListings.size());
        Integer listingID = sellerListings.get(numListing - 1).getId();
        updateListingNextStep(listingID);
        ui.printListingUpdateSuccess();
    }

    private void updateListingNextStep(Integer listingID){
        ui.printUpdateListingMenu();
        int updateInput = input.intInput(1, 9);
        switch (updateInput){
            case 1:
                try{
                    updateListingBathrooms(listingID);
                }
                catch (ModificationErrorException exception){
                    ui.printArbitraryException(exception);
                    updateListingBathrooms(listingID);
                }
                break;
            case 2:
                try{
                    updateListingBedrooms(listingID);
                }
                catch (ModificationErrorException exception){
                    ui.printArbitraryException(exception);
                    updateListingBedrooms(listingID);
                }
                break;
            case 3:
                try{
                    updateListingCity(listingID);
                }
                catch (ModificationErrorException exception){
                    ui.printArbitraryException(exception);
                    updateListingCity(listingID);
                }
                break;
            case 4:
                try{
                    updateCivicAddress(listingID);
                }
                catch (ModificationErrorException exception){
                    ui.printArbitraryException(exception);
                    updateCivicAddress(listingID);
                }
                break;
            case 5:
                try{
                    updateListingDescription(listingID);
                }
                catch (ModificationErrorException exception){
                    ui.printArbitraryException(exception);
                    updateListingDescription(listingID);
                }
                break;
            case 6:
                try{
                    updateListingFloors(listingID);
                }
                catch (ModificationErrorException exception){
                    ui.printArbitraryException(exception);
                    updateListingFloors(listingID);
                }
                break;
            case 7:
                try{
                    updateListingPrice(listingID);
                }
                catch (ModificationErrorException exception){
                    ui.printArbitraryException(exception);
                    updateListingPrice(listingID);
                }
                break;
            case 8:
                try{
                    updateListingStreet(listingID);
                }
                catch (ModificationErrorException exception){
                    ui.printArbitraryException(exception);
                    updateListingStreet(listingID);
                }
                break;
            case 9:
                try{
                    updateUnitNumber(listingID);
                }
                catch (ModificationErrorException exception){
                    ui.printArbitraryException(exception);
                    updateUnitNumber(listingID);
                }
                break;
        }

    }

    private void updateListingBathrooms(Integer listingID){
        ui.printEnterType("the new number of bathrooms");
        Integer newBathroomNum = input.intInput();
        listingUpdateManager.updateListingAttribute("bathroom", listingID, newBathroomNum);
    }

    private void updateListingBedrooms(Integer listingID){
        ui.printEnterType("the new number of bedrooms");
        Integer newBedroomNum = input.intInput();
        listingUpdateManager.updateListingAttribute("bedrooms", listingID, newBedroomNum);
    }

    private void updateListingCity(Integer listingID){
        ui.printEnterType("the new city");
        String newCity = input.strInput();
        listingUpdateManager.updateListingAttribute("city", listingID, newCity);
    }

    private void updateCivicAddress(Integer listingID){
        ui.printEnterType("the new civic address");
        Integer newCivicAddress = input.intInput();
        listingUpdateManager.updateListingAttribute("civic_address", listingID, newCivicAddress);
    }

    private void updateListingDescription(Integer listingID){
        ui.printEnterType("the new description");
        String newDesc = input.strInput();
        listingUpdateManager.updateListingAttribute("description", listingID, newDesc);
    }

    private void updateListingFloors(Integer listingID){
        ui.printEnterType("the new number of floors");
        Integer newFloorNum = input.intInput();
        listingUpdateManager.updateListingAttribute("floors", listingID, newFloorNum);
    }

    private void updateListingPrice(Integer listingID){
        ui.printEnterType("the new price");
        BigDecimal newPrice = BigDecimal.valueOf(input.intInput());
        listingUpdateManager.updateListingAttribute("price", listingID, newPrice);
    }

    private void updateListingStreet(Integer listingID){
        ui.printEnterType("the new street");
        String newStreet = input.strInput();
        listingUpdateManager.updateListingAttribute("street", listingID, newStreet);
    }

    private void updateUnitNumber(Integer listingID){
        ui.printEnterType("the new unit number");
        Integer newUnitNum = input.intInput();
        listingUpdateManager.updateListingAttribute("unit_number", listingID, newUnitNum);
    }

    /**
     * Displays the messages inbox of the logged-in user
     * @param username the username of the logged-in user
     */
    public void viewInbox(String username) {
        ui.printMessages(viewMessages.getMessageInbox(username));
    }

    /**
     * Displays the messages outbox of the logged-in user
     * @param username the username of the logged-in user
     */
    public void viewOutbox(String username) {
        ui.printMessages(viewMessages.getMessageOutbox(username));
    }

    public void viewFavouriteListings(String username){
        ui.printNumberedListings(favAndUnFavManager.getBuyerFavouritesString(username));
    }


    public void viewAllListings(){
        ui.printNumberedListings(viewListings.getAllListings());
    }
    /**
     * Presents admin with options to navigate through program
     *
     * @param username the logged-in user's username
     * @return true if the admin screen successfully appears
     */
    public boolean adminScreen(String username) {
        ui.printAdminLoginMenu();
        AdminUser user = (AdminUser) userManager.getUser(username);
        if(user.isNotify()){
            ui.printAdminInboxNotification();
        }
        ArrayList<Integer> allowedInputs = new ArrayList<>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        int select = input.intInput(allowedInputs);
        switch (select) {
            case 1:
                // View login history
                ArrayList<String> userHistory = history.getLoginHistory(username);
                ui.printLoginHistory(userHistory);
                break;
            case 2:
                // Create new admin user
                userManager.createNewUser("ADMIN");
                break;
            case 3:
                viewInbox(username);
                break;
            case 4:
                viewOutbox(username);
                break;
            case 5:
                // Ban or unban user
                updateUserBanStatus();
                break;
            case 6:
                // Delete user
                deleteUser();
                break;
            case 7:
                //View chat history between 2 users
                viewMessageChatAdminVersion();
                break;
            case 8:
                displaySellerListings();
                break;
            case 9:
                changeReportStatus(username);
                break;
            case 10:
                // Logout user
                auth.logoutUser(username);
                ui.printLogOutSuccess();
                return false;
        }

        return true;
    }
}
