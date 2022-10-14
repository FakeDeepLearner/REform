package contollers;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;
import useCases.listingUseCases.updates.*;

import java.util.ArrayList;
import java.util.List;

public class ListingUpdateManager {
    private final List<UpdateListing> updateListingList = new ArrayList<>();

    public ListingUpdateManager(ListingContainer<Integer, Listing> listings){

        updateListingList.add(new UpdateBathrooms(listings));
        updateListingList.add(new UpdateBedrooms(listings));
        updateListingList.add(new UpdateCity(listings));
        updateListingList.add(new UpdateCivicAddress(listings));
        updateListingList.add(new UpdateDescription(listings));
        updateListingList.add(new UpdateFloors(listings));
        updateListingList.add(new UpdatePrice(listings));
        updateListingList.add(new UpdateStreet(listings));
        updateListingList.add(new UpdateUnitNumber(listings));
    }

    public void updateListingAttribute(String updateType, Integer ID, Object newData){
        ListingEditable listingEditable = ListingEditable.fromString(updateType);
        for (UpdateListing updateListing : updateListingList){
            if (listingEditable.equals(updateListing.getEditType())){
                updateListing.update(ID, newData);
            }
        }
    }
}
