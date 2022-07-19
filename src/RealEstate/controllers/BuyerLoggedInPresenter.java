package RealEstate.controllers;

public class BuyerLoggedInPresenter {
    public void printWelcomeMessage(){
        System.out.println("Welcome (back) to your account");
        System.out.println("Press 1 to see your favorites");
        System.out.println("Press 2 to favorite a listing");
    }

    public void returnToMainMenu(){
        printRedirectingBuffer();
        printWelcomeMessage();
    }

    public void askForMessageSendingWhenViewingFavorites(){
        System.out.println("Press 1 to send a message to any of your favorites.");
        System.out.println("Press any other key to return to the previous menu.");
    }

    public void askWhoToSendMessageTo(){
        System.out.println("Please enter who you want to send your message to. (1 if it is listed as the first item," +
                "2 if it is listed as the second item etc...");
    }

    public void askMessageInput(){
        System.out.println("Type your message: ");
    }

    public void askForMessageConfirmation(){
        System.out.println("Confirm that you want to send the message(Y/N)");
    }

    public void printMessageSent(){
        System.out.println("Message sent, thank you. You will be redirected to the main menu.");
        returnToMainMenu();
    }

    public void printRedirectingBuffer(){
        System.out.println("Redirecting, standby.");
    }

    public void askForInputWhenFavoritingAListing(){
        System.out.println("Please enter the number on the list of the listing you want to favorite");
    }

    public void confirmFavoritingAction(){
        System.out.println("Please confirm that you want to favorite this listing. (Y/N)");
    }

    public void printFavoriteConfirmation(){
        System.out.println("Listing has been favorited, thank you. You will be redirected to the main menu");
        returnToMainMenu();
    }

}
