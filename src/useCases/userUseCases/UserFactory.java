package useCases.userUseCases;

import entities.User;
import entities.UserType;
import entities.containers.UserContainer;
import exceptions.UsernameAlreadyExistsException;
import gateways.AdminCSVController;
import gateways.BuyersCSVController;
import gateways.SellersCSVController;

public class UserFactory {
    private final CreateAdmin createAdmin;
    private final CreateBuyer createBuyer;
    private final CreateSeller createSeller;
    private final UserContainer<String, User> userContainer;

    /**
     * Factory for creating users using default functions
     *
     * @param users is a hash map that relates usernames to a User objects
     */
    public UserFactory(UserContainer<String, User> users) {
        this.userContainer = users;

        this.createAdmin = new CreateAdmin(users, new AdminCSVController(users));
        this.createBuyer = new CreateBuyer(users, new BuyersCSVController(users));
        this.createSeller = new CreateSeller(users, new SellersCSVController(users));
    }

    /**
     * Checks whether a given username already is associated with a User object
     *
     * @param username to be queried
     * @return whether username already exists in userContainer
     */
    private boolean uniqueUsernameExists(String username) {
        try {
            userContainer.get(username);
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    /**
     * @param type     of user to be created
     * @param username of the user to be created
     * @param password of the user to be created
     */
    public void createUser(String type, String username, String password) {
        UserType userType = UserType.fromString(type);

        if (uniqueUsernameExists(username)) {
            switch (userType) {
                case ADMIN:
                    createAdmin.createAdminUser(username, password);
                    break;
                case BUYER:
                    createBuyer.createNewBuyer(username, password);
                    break;
                case SELLER:
                    createSeller.createNewSeller(username, password);
                    break;
            }
        } else {
            throw new UsernameAlreadyExistsException();
        }
    }
}
