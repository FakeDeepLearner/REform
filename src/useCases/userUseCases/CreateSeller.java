package useCases.userUseCases;

import entities.User;
import entities.containers.UserContainer;
import entities.Seller;
import useCases.DataInterface;

import java.io.IOException;
import java.util.HashMap;

public class CreateSeller {
    private final UserContainer<String, User> userContainer;
    private final HashMap<String, Seller> createdSellers;
    public DataInterface i;

    /**
     * Constructor for the CreateSeller Class
     *
     * @param userContainer UserContainer Class
     */
    public CreateSeller(UserContainer<String, User> userContainer, DataInterface i) {
        this.userContainer = userContainer;
        createdSellers = new HashMap<>();
        this.i = i;
    }

    public void read() throws IOException {
        UserFactory userFactory = new UserFactory(userContainer);

        for (String[] data : i.read()) {
            userFactory.createUser("seller", data[0], data[1]);
        }
    }

    public void write() throws IOException {
        i.write();
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

