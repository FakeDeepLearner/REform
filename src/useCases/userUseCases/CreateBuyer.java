package useCases.userUseCases;

import entities.User;
import entities.containers.UserContainer;
import entities.Buyer;
import gateways.DataInterface;

import java.io.IOException;
import java.util.HashMap;

public class CreateBuyer {
    private final UserContainer<String, User> userContainer;

    private final HashMap<String, Buyer> createdBuyers;
    public final DataInterface i;

    /**
     * Initialize the CreateBuyer Class
     *
     * @param userContainer UserContainer Class
     */
    public CreateBuyer(UserContainer<String, User> userContainer, DataInterface i) {
        this.userContainer = userContainer;
        createdBuyers = new HashMap<>();
        this.i = i;
    }

    /**
     * Read data from csv files
     */
    public void read() throws IOException {
        UserFactory userFactory = new UserFactory(userContainer);

        for (String[] data : i.read()) {
            userFactory.createUser("buyer", data[0], data[1]);
        }
    }

    /**
     * Write data to csv files
     */
    public void write() throws IOException {
        i.write();
    }

    /**
     * Getter for userContainer
     *
     * @return this.userContainer
     */
    public UserContainer<String, User> getUserContainer() {
        return userContainer;
    }

    /**
     * Getter for createdBuyers
     *
     * @return this.createdBuyers
     */
    public HashMap<String, Buyer> getCreatedBuyers() {
        return createdBuyers;
    }

    /**
     * Create a Buyer by calling the constructor and put the info into the container
     *
     * @param username Username of the Buyer
     * @param password Password of the Buyer.
     */
    public void createNewBuyer(String username, String password) {
        Buyer buyer = new Buyer(username, password);
        userContainer.put(username, buyer);
        createdBuyers.put(username, buyer);
    }
}
