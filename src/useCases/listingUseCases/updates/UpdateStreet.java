package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;
import exceptions.ModificationErrorException;

public non-sealed class UpdateStreet extends UpdateListing {

    public UpdateStreet(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.STREET, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        try {
            listing.setStreetName((String) data);
        }
        catch(ClassCastException e){
            throw new ModificationErrorException();
        }
    }
}
