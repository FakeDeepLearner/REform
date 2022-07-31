package useCases.sellerUseCases;

import entities.User;
import entities.containers.UserContainer;
import entities.Seller;

import java.util.HashMap;

public class CreateSeller {
    private final UserContainer<String, User> userContainer;
    private final HashMap<String, Seller> createdSellers;

    /**
     * Constructor for the CreateSeller Class
     *
     * @param userContainer UserContainer Class
     */
    public CreateSeller(UserContainer<String, User> userContainer) {
        this.userContainer = userContainer;
        createdSellers = new HashMap<>();
    }

    /**
     * Getter for userContainer
     *
     * @return Stored userContainer
     */
    public UserContainer<String, User> getUserContainer() {
        return userContainer;
    }

    /**
     * Getter for createdSellers
     *
     * @return Hashmap of created Sellers
     */
    public HashMap<String, Seller> getCreatedSellers() {
        return createdSellers;
    }

    /**
     * Create a Seller class by calling its constructor
     *
     * @param username Username of the Seller.
     * @param password Password of the Seller.
     */
    public void createNewSeller(String username, String password) {
        Seller seller = new Seller(username, password);
        userContainer.put(username, seller);
        createdSellers.put(username, seller);
    }

}

