package RealEstate.useCases;

import LoginSystem.entities.User;
import LoginSystem.entities.UserContainer;
import RealEstate.entities.Seller;

import java.util.HashMap;

public class CreateSeller {
    private final UserContainer<String, User> userContainer;
    private final HashMap<String, Seller> createdSellers;

    public CreateSeller(UserContainer<String, User> userContainer){
        this.userContainer = userContainer;
        createdSellers = new HashMap<>();
    }

    public UserContainer<String, User> getUserContainer() {
        return userContainer;
    }

    public HashMap<String, Seller> getCreatedSellers() {
        return createdSellers;
    }

    public void createNewSeller(String username, String password){
        Seller seller = new Seller(username, password);
        userContainer.put(username, seller);
        createdSellers.put(username, seller);
    }

}

