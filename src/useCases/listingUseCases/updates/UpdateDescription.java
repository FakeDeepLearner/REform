package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;
import exceptions.ModificationErrorException;

public non-sealed class UpdateDescription extends UpdateListing{

    public UpdateDescription(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.DESCRIPTION, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        try {
            listing.setInfo((String) data);
        }
        catch(ClassCastException e){
            throw new ModificationErrorException();
        }
    }
}
