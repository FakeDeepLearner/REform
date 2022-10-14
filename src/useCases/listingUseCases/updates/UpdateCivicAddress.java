package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;
import exceptions.ModificationErrorException;

public non-sealed class UpdateCivicAddress extends UpdateListing {

    public UpdateCivicAddress(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.CIVIC_ADDRESS, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        try {
            listing.setCivicAddress((Integer) data);
        }
        catch(ClassCastException e){
            throw new ModificationErrorException();
        }
    }
}
