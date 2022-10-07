package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;

public non-sealed class UpdateBedrooms extends UpdateListing {

    public UpdateBedrooms(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.BEDROOMS, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        listing.setBedrooms((Integer) data);
    }
}
