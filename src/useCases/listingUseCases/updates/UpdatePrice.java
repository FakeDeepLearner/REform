package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;

import java.math.BigDecimal;

public non-sealed class UpdatePrice extends UpdateListing {

    public UpdatePrice(ListingContainer<Integer, Listing> listingContainer){
        super(ListingEditable.PRICE, listingContainer);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        listing.setPrice((BigDecimal) data);
    }
}
