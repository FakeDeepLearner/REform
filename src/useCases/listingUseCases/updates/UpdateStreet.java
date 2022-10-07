package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;

public non-sealed class UpdateStreet extends UpdateListing {

    public UpdateStreet(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.STREET, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        listing.setStreetName((String) data);
    }
}
