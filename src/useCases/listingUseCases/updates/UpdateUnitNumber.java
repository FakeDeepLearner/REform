package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;
import exceptions.ModificationErrorException;

public non-sealed class UpdateUnitNumber extends UpdateListing {

    public UpdateUnitNumber(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.UNIT_NUMBER, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        try {
            listing.setUnitNumber((Integer) data);
        }
        catch(ClassCastException | NullPointerException e){
            throw new ModificationErrorException();
        }
    }
}
