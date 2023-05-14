package contollers;

import entities.Listing;
import entities.ListingEditable;
import entities.containers.ListingContainer;
import useCases.listingUseCases.UpdateListingAvailability;
import useCases.listingUseCases.updates.*;

import java.util.ArrayList;
import java.util.List;

public class ListingUpdateAndSellManager {
    private final List<UpdateListing> updateListingList = new ArrayList<>();

    private final ListingContainer<Integer, Listing> listings;
    private final UpdateListingAvailability updateListingAvailability;

    public ListingUpdateAndSellManager(ListingContainer<Integer, Listing> listings){
        this.listings = listings;

        updateListingList.add(new UpdateBathrooms(listings));
        updateListingList.add(new UpdateBedrooms(listings));
        updateListingList.add(new UpdateCity(listings));
        updateListingList.add(new UpdateCivicAddress(listings));
        updateListingList.add(new UpdateDescription(listings));
        updateListingList.add(new UpdateFloors(listings));
        updateListingList.add(new UpdatePrice(listings));
        updateListingList.add(new UpdateStreet(listings));
        updateListingList.add(new UpdateUnitNumber(listings));
        this.updateListingAvailability = new UpdateListingAvailability();
    }

    public void updateListingAttribute(String updateType, Integer ID, Object newData){
        ListingEditable listingEditable = ListingEditable.fromString(updateType);
        for (UpdateListing updateListing : updateListingList){
            if (listingEditable.equals(updateListing.getEditType())){
                updateListing.update(ID, newData);
            }
        }
    }

    public void updateListingStatus(Integer listingID){
        Listing listing = listings.get(listingID);
        if(listing.getIsSold()){
            updateListingAvailability.markListingAsAvailable(listing);
        }
        else{
            updateListingAvailability.markListingAsSold(listing);
        }
    }

}
