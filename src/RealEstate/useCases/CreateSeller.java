package RealEstate.useCases;

import LoginSystem.entities.NonAdminUser;
import RealEstate.entities.NonAdminContainer;
import RealEstate.entities.Seller;
import RealEstate.entities.SellerContainer;

import java.util.HashMap;

public class CreateSeller {
    private final SellerContainer<String, Seller> sellerContainer;
    private final NonAdminContainer<String, NonAdminUser> nonAdminContainer;
    private final HashMap<String, Seller> createdSellers;

    public CreateSeller(SellerContainer<String, Seller> sellerContainer,
                        NonAdminContainer<String, NonAdminUser> nonAdminContainer){
        this.sellerContainer = sellerContainer;
        this.nonAdminContainer = nonAdminContainer;
        createdSellers = new HashMap<>();
    }

    public NonAdminContainer<String, NonAdminUser> getNonAdminContainer() {
        return nonAdminContainer;
    }

    public HashMap<String, Seller> getCreatedSellers() {
        return createdSellers;
    }

    public SellerContainer<String, Seller> getSellerContainer() {
        return sellerContainer;
    }

    public void createNewSeller(String username, String password){
        Seller newSeller = new Seller(username, password);
        sellerContainer.put(username, newSeller);
        nonAdminContainer.put(username, new NonAdminUser(username, password));
        createdSellers.put(username, newSeller);
    }

}

