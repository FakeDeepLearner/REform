package RealEstate.useCases;

import LoginSystem.entities.NonAdminUser;
import RealEstate.entities.Buyer;
import RealEstate.entities.BuyerContainer;
import RealEstate.entities.NonAdminContainer;

import java.util.HashMap;

public class CreateBuyer {
    private final BuyerContainer<String, Buyer> buyerContainer;
    private final NonAdminContainer<String, NonAdminUser> nonAdminContainer;

    private final HashMap<String, Buyer> createdBuyers;

    public CreateBuyer(BuyerContainer<String, Buyer> buyerContainer,
                       NonAdminContainer<String, NonAdminUser> nonAdminContainer){
        this.buyerContainer = buyerContainer;
        this.nonAdminContainer = nonAdminContainer;
        createdBuyers = new HashMap<>();
    }

    public BuyerContainer<String, Buyer> getBuyerContainer() {
        return buyerContainer;
    }

    public HashMap<String, Buyer> getCreatedBuyers() {
        return createdBuyers;
    }

    public NonAdminContainer<String, NonAdminUser> getNonAdminContainer() {
        return nonAdminContainer;
    }

    public void createNewBuyer(String username, String password){
        Buyer newBuyer = new Buyer(username, password);
        buyerContainer.put(username, newBuyer);
        nonAdminContainer.put(username, new NonAdminUser(username, password));
        createdBuyers.put(username, newBuyer);
    }






}
