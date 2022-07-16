package RealEstate.useCases;

import LoginSystem.entities.NonAdminUser;
import RealEstate.entities.NonAdminContainer;
import RealEstate.entities.Seller;
import RealEstate.entities.SellerContainer;

public class CreateSeller {
    private final SellerContainer<String, Seller> sellerContainer;
    private final NonAdminContainer<String, NonAdminUser> nonAdminContainer;

    public CreateSeller(SellerContainer<String, Seller> sellerContainer,
                        NonAdminContainer<String, NonAdminUser> nonAdminContainer){
        this.sellerContainer = sellerContainer;
        this.nonAdminContainer = nonAdminContainer;
    }

    public NonAdminContainer<String, NonAdminUser> getNonAdminContainer() {
        return nonAdminContainer;
    }

    public SellerContainer<String, Seller> getSellerContainer() {
        return sellerContainer;
    }

    public void createNewSeller(String username, String password){
        sellerContainer.put(username, new Seller(username, password));
        nonAdminContainer.put(username, new NonAdminUser(username, password));
    }
}

