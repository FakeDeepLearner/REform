package contollers;

import java.util.ArrayList;
import java.util.List;

public class UserInterface {
    public void printWelcomeMessage() {
        System.out.println("Hello, please press 1 to sign up, or 2 to log in to your account.");
        System.out.println("(Secret) Press 3 to create an admin user.");
        System.out.println("Press 4 to exit the program safely.");
    }

    public void printCreateUserMenu() {
        System.out.println("Press 1 to create a new buyer.");
        System.out.println("Press 2 to create a new seller.");
    }

    public void printAdminLoginMenu() {
        System.out.println("Press 1 to see your login history");
        System.out.println("Press 2 to create a user (this new user will be an admin)");
        System.out.println("Press 3 to view your inbox.");
        System.out.println("Press 4 to view your outbox");
        System.out.println("Press 5 to ban or unban a user.");
        System.out.println("Press 6 to delete a user.");
        System.out.println("Press 7 to view the chat history of 2 users");
        System.out.println("Press 8 to view the listings of a seller.");
        System.out.println("Press 9 to log out.");
    }

    public void printBuyerLoginMenu(){
        System.out.println("Press 1 to view listings");
        System.out.println("Press 2 to view favourite listings");
        System.out.println("Press 3 to message user");
        System.out.println("Press 4 to view your inbox");
        System.out.println("Press 5 to view your outbox");
        System.out.println("Press 6 to report a user.");
        System.out.println("Press 7 to see your login history");
        System.out.println("Press 8 to see your chat history with another user.");
        System.out.println("Press 9 to log out.");
    }

    public void printSellerLoginMenu(){
        System.out.println("Press 1 to view your listings");
        System.out.println("Press 2 to adjust the prices of your listings.");
        System.out.println("Press 3 to message user");
        System.out.println("Press 4 to view your inbox");
        System.out.println("Press 5 to view your outbox");
        System.out.println("Press 6 to report a user.");
        System.out.println("Press 7 to create new listing");
        System.out.println("Press 8 to delete listing");
        System.out.println("Press 9 to see your login history");
        System.out.println("Press 10 to see your chat history with another user.");
        System.out.println("Press 11 to log out.");
    }

    public void printListingOptionsMenu(){
        System.out.println("Press 1 to search listings by civic address");
        System.out.println("Press 2 to search by street name");
        System.out.println("Press 3 to search by city");
        System.out.println("Press 4 to search by number of bedrooms");
        System.out.println("Press 5 to search by number of bathrooms");
        System.out.println("Press 6 to search by number of floors");
        System.out.println("Press 7 to search by price");
        System.out.println("Press 8 to search by listing type");
    }

    public void printRestrictUserConfirmation(String username, boolean isBanned) {
        if (isBanned) {
            System.out.println(username + " is currently banned.");
            System.out.println("Would you like to unban them? (Y/N)");
        } else {
            System.out.println(username + " is currently not banned.");
            System.out.println("Would you like to ban them? (Y/N)");
        }
    }

    public void printEnterType(String type) {
        System.out.println("Enter " + type + ": ");
    }

    public void printCreateUsernameInput() {
        System.out.println("Create a username: ");
    }

    public void printCreatePasswordInput() {
        System.out.println("Create a password: ");
    }

    public void printSignUpSuccess() {
        System.out.println("User successfully created.");
    }

    public void printExistingUsernameInput() {
        System.out.println("Enter your username: ");
    }

    public void printExistingPasswordInput() {
        System.out.println("Enter your password: ");
    }

    public void printLoginSuccess() {
        System.out.println("Successfully logged in.");
    }

    public void printLoginFail() {
        System.out.println("Log in failed. Please try again.");
    }

    public void printLogOutSuccess() {
        System.out.println("Successfully logged out, returning to main menu.");
    }

    public void printRestrictUsernameInput() {
        System.out.println("Enter the username of the user whose ban status you wish to change: ");
    }

    public void printDeleteUsernameInput() {
        System.out.println("Enter the username of the user who you wish to delete: ");
    }

    public void printMessageUsernameInput() {
        System.out.println("Enter the username of the user who you wish to message: ");
    }

    public void printMessageInput() {
        System.out.println("Enter the message you would like to send: ");
    }

    public void printLoginHistory(ArrayList<String> userHistory) {
        System.out.println(userHistory);
    }

    public void printFilteredListings(List<String> listings) {
        System.out.println(listings);
    }

    public void printMessages(ArrayList<String> messages) {
        System.out.println(messages);
    }

    public void printNumberedListings(List<String> listings) {
        for (int i = 0 ; i < listings.size() ; i++){
            System.out.println((i + 1) + ") " + listings.get(i));
        }
    }

    public void printAddListingToFavourites() {
        System.out.println("Would you like to add any of the listings above to your favourites? (Y/N)");
    }

    public void printDeleteListingFromFavourites() {
        System.out.println("Would you like to remove any of your favourite listings? (Y/N)");
    }

    public void printListingFavouriteSuccess() {
        System.out.println("The listing was successfully added to your favourites. ");
    }

    public void printFavouriteRemovedSuccess() {
        System.out.println("The listing was successfully removed from your favourites. ");
    }

    public void printNoFavourites() {
        System.out.println("You currently have no favourite listings. ");
    }

    public void printDeleteUserSuccess(String username) {
        System.out.println(username + "'s account was successfully deleted.");
    }

    public void printDeleteUserFail(String username) {
        System.out.println(username + "'s account could not be deleted.");
    }

    public void printDeleteListingSuccess() {
        System.out.println("Listing was successfully deleted.");
    }

    public void printListingCreatedSuccess() {
        System.out.println("Listing was successfully created.");
    }

    public void printListingHasUnitNumber() {
        System.out.println("Does this listing have a unit number? (Y/N)");
    }

    public void printMessageUserFail(String username) {
        System.out.println("You cannot message " + username);
    }

    public void printMessageSentSuccess() {
        System.out.println("Message was successfully sent.");
    }

    public void printReportSuccess(){
        System.out.println("User has been reported to all admins, the report will be reviewed soon.");
    }

    public void printInvalidInput() {
        System.out.println("Please enter a valid input.");
    }

    public void printArbitraryException (Exception e) {
        System.out.println(e.getMessage());
    }

}
