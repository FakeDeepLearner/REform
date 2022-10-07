package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;

public non-sealed class UpdateCivicAddress extends UpdateListing {

    public UpdateCivicAddress(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.CIVIC_ADDRESS, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        listing.setCivicAddress((Integer) data);
    }
}
