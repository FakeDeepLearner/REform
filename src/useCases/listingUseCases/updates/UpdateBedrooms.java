package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;
import exceptions.ModificationErrorException;

public non-sealed class UpdateBedrooms extends UpdateListing {

    public UpdateBedrooms(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.BEDROOMS, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        try {
            listing.setBedrooms((Integer) data);
        }
        catch (ClassCastException e){
            throw new ModificationErrorException();
        }
    }
}
