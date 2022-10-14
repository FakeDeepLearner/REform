package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;
import exceptions.ModificationErrorException;

public non-sealed class UpdateBathrooms extends UpdateListing {

    public UpdateBathrooms(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.BATHROOMS, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        try {
            listing.setBathrooms((Integer) data);
        }
        catch (ClassCastException e){
            throw new ModificationErrorException();
        }
    }
}
