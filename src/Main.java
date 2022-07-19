import LoginSystem.controllers.InputHandler;
import LoginSystem.controllers.LoggedOutManager;
import LoginSystem.controllers.UserInterface;
import LoginSystem.controllers.UserManager;
import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import LoginSystem.exceptions.ExitProgramException;
import LoginSystem.useCases.AuthenticateUser;
import LoginSystem.useCases.RestrictUser;
import LoginSystem.useCases.UpdateUserHistory;
import LoginSystem.useCases.UsernamePasswordFileEditor;
import RealEstate.controllers.RealtyLoggedInManager;
import RealEstate.entities.Listing;
import RealEstate.entities.ListingContainer;
import RealEstate.entities.Message;
import RealEstate.entities.MessageContainer;
import RealEstate.useCases.CreateListing;
import RealEstate.useCases.ListingProperties;
import RealEstate.useCases.SendMessages;
import RealEstate.useCases.UserFactory;

public class Main {
    public static void main(String[] args) {
        UserContainer<String, User> users = new UserContainer<>();
        MessageContainer<Integer, Message> messages = new MessageContainer<>();
        ListingContainer<Integer, Listing> listings = new ListingContainer<>();

        UserFactory userFactory = new UserFactory(users);
        AuthenticateUser auth = new AuthenticateUser(users);
        UpdateUserHistory history = new UpdateUserHistory(users);
        RestrictUser restrict = new RestrictUser(users);
        UsernamePasswordFileEditor file = new UsernamePasswordFileEditor(auth, users);
        SendMessages sendMessages = new SendMessages(messages, users);
        ListingProperties listingProperties = new ListingProperties(listings);
        CreateListing createListing = new CreateListing(listings, users);



        UserInterface ui = new UserInterface();
        InputHandler input = new InputHandler(ui);
        UserManager userManager = new UserManager(input, ui, userFactory, auth, history, file);
        LoggedOutManager loggedOutManager = new LoggedOutManager(input, ui, userManager);
        RealtyLoggedInManager loggedInManager = new RealtyLoggedInManager(
                input, ui, auth, history, restrict, userManager, file, sendMessages, listingProperties, createListing
        );

        while (true) {
            String username = null;
            try {
                username = loggedOutManager.menuSelector();
            } catch (ExitProgramException e) {
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
