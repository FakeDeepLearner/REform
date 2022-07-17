package RealEstate.useCases;

import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import RealEstate.entities.Buyer;

import java.util.HashMap;

public class CreateBuyer {
    private final UserContainer<String, User> userContainer;

    private final HashMap<String, Buyer> createdBuyers;

    public CreateBuyer(UserContainer<String, User> userContainer){
        this.userContainer = userContainer;
        createdBuyers = new HashMap<>();
    }

    public UserContainer<String, User> getUserContainer() {
        return userContainer;
    }

    public HashMap<String, Buyer> getCreatedBuyers() {
        return createdBuyers;
    }

    public void createNewBuyer(String username, String password){
        Buyer buyer = new Buyer(username, password);
        userContainer.put(username, buyer);
        createdBuyers.put(username, buyer);
    }






}
