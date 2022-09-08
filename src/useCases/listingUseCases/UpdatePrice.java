package useCases.listingUseCases;

import entities.Listing;
import entities.containers.ListingContainer;

import java.math.BigDecimal;

public class UpdatePrice {
    private final ListingContainer<Integer, Listing> listingContainer;

    public UpdatePrice(ListingContainer<Integer, Listing> listingContainer){
        this.listingContainer = listingContainer;
    }

    public void changePrice(Integer id, BigDecimal newPrice){
        Listing listing = listingContainer.get(id);
        listing.setPrice(newPrice);
    }


}
