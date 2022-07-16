package RealEstate.useCases;

import LoginSystem.entities.NonAdminUser;
import RealEstate.entities.Buyer;
import RealEstate.entities.BuyerContainer;
import RealEstate.entities.NonAdminContainer;

public class CreateBuyer {
    private final BuyerContainer<String, Buyer> buyerContainer;
    private final NonAdminContainer<String, NonAdminUser> nonAdminContainer;

    public CreateBuyer(BuyerContainer<String, Buyer> buyerContainer,
                       NonAdminContainer<String, NonAdminUser> nonAdminContainer){
        this.buyerContainer = buyerContainer;
        this.nonAdminContainer = nonAdminContainer;
    }

    public BuyerContainer<String, Buyer> getBuyerContainer() {
        return buyerContainer;
    }

    public NonAdminContainer<String, NonAdminUser> getNonAdminContainer() {
        return nonAdminContainer;
    }

    public void createNewBuyer(String username, String password){
        buyerContainer.put(username, new Buyer(username, password));
        nonAdminContainer.put(username, new NonAdminUser(username, password));
    }






}
