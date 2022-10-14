package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;
import exceptions.ModificationErrorException;

import java.math.BigDecimal;

public non-sealed class UpdatePrice extends UpdateListing {

    public UpdatePrice(ListingContainer<Integer, Listing> listingContainer){
        super(ListingEditable.PRICE, listingContainer);
    }

    @Override
    public void update(Integer ID, Object data) {
        Listing listing = listings.get(ID);
        try {
            listing.setPrice((BigDecimal) data);
        }
        catch(ClassCastException e){
            throw new ModificationErrorException();
        }
    }
}
