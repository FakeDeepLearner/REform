package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;
import exceptions.ModificationErrorException;

public non-sealed class UpdateFloors extends UpdateListing{

    public UpdateFloors(ListingContainer<Integer, Listing> listings){
        super(ListingEditable.FLOORS, listings);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        try {
            listing.setFloors((Integer) data);
        }
        catch(ClassCastException | NullPointerException e){
            throw new ModificationErrorException();
        }
    }
}
