package useCases.listingUseCases;

import entities.Listing;

public class UpdateListingAvailability {

    public void markListingAsSold(Listing listing){
        listing.setSold(true);
    }

    public void markListingAsAvailable(Listing listing){
        listing.setSold(false);
    }

}
