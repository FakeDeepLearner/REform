package RealEstate.controllers;

public class SellerLoggedInPresenter {

    public void printWelcomeMessage(){
        System.out.println("Welcome (back) to your account");
        System.out.println("Press 1 to see your listings");
        System.out.println("Press 2 to add a listing");
        System.out.println("Press 3 to remove a listing");
    }

    public void askInputForListingRemoval(){
        System.out.println("Please enter the number of the listing you want removed (1 if the listing was listed as" +
                "the first, 2 if the listing was listed as the second etc...");
    }

    public void askListingRemovalConfirmation(){
        System.out.println("Confirm that you want to remove the listing. (Y/N)");
    }

    public void printListingRemoval(){
        System.out.println("Listing has been successfully removed, you will be redirected to the main menu");
        printWelcomeMessage();
    }
}
