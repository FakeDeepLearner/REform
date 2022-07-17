package RealEstate.controllers;

import RealEstate.entities.Listing;
import RealEstate.useCases.CreateBuyer;
import RealEstate.useCases.CreateListing;
import RealEstate.useCases.CreateSeller;

import java.math.BigDecimal;

public class Creator {
    private final CreateBuyer createBuyer;
    private final CreateSeller createSeller;
    private final CreateListing createListing;

    public Creator(CreateBuyer createBuyer,
                   CreateSeller createSeller,
                   CreateListing createListing) {
        this.createBuyer = createBuyer;
        this.createSeller = createSeller;
        this.createListing = createListing;
    }

    public CreateBuyer getCreateBuyer() {
        return createBuyer;
    }

    public CreateListing getCreateListing() {
        return createListing;
    }

    public CreateSeller getCreateSeller() {
        return createSeller;
    }

    public void createNewBuyer(String username, String password){
        createBuyer.createNewBuyer(username, password);
    }

    public void createNewSeller(String username, String password){
        createSeller.createNewSeller(username, password);
    }

    public void createNewListing(String username, int civicAddress, String streetName, String city, String type, int bedrooms,
                                 int bathrooms, BigDecimal price){
        Listing listing = createListing.addListing(civicAddress, streetName, city, type, bedrooms, bathrooms, price);
        createListing.addListingToSeller(username, listing);
        createListing.addListingToCreatedListings(username, listing);
    }
}
