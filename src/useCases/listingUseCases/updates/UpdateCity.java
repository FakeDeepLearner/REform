package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;

public non-sealed class UpdateCity extends UpdateListing {

    public UpdateCity(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.CITY, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        listing.setCity((String) data);
    }
}
