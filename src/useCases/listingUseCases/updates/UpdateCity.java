package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;
import exceptions.ModificationErrorException;

public non-sealed class UpdateCity extends UpdateListing {

    public UpdateCity(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.CITY, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        try {
            listing.setCity((String) data);
        }
        catch(ClassCastException e){
            throw new ModificationErrorException();
        }
    }
}
