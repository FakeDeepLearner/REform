package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;

public non-sealed class UpdateUnitNumber extends UpdateListing {

    public UpdateUnitNumber(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.UNIT_NUMBER, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        listing.setUnitNumber((Integer) data);
    }
}
