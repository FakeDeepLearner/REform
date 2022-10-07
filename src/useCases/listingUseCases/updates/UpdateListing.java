package useCases.listingUseCases.updates;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;

public abstract sealed class UpdateListing permits
UpdateDescription, UpdateFloors, UpdatePrice, UpdateUnitNumber, UpdateBedrooms, UpdateCity,
UpdateStreet, UpdateBathrooms, UpdateCivicAddress{
    protected ListingEditable editType;
    protected ListingContainer<Integer, Listing> listings;

    protected UpdateListing(ListingEditable editType, ListingContainer<Integer, Listing> listings){
        this.editType = editType;
        this.listings = listings;
    }

    public ListingEditable getEditType() {
        return editType;
    }

    public abstract void update(Integer ID , Object data);
}
