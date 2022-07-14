package RealEstate.gateways;
import RealEstate.useCases.FavoriteListing;

public class FavAndUnFavManager{
    private final FavoriteListing favoriteListing;


    public FavAndUnFavManager(FavoriteListing favoriteListing) {
        this.favoriteListing = favoriteListing;
    }

    public FavoriteListing getFavoriteListing() {
        return favoriteListing;
    }

    public void addToBuyerFavorites(String username, int listingID) {
        favoriteListing.addListingToBuyerFavorites(username, listingID);
        favoriteListing.addCreationToGeneratedFavorites(username, listingID);

    }

    public void removeFromBuyerFavorites(String username, int listingID) {
        favoriteListing.removeListingFromBuyerFavorites(username, listingID);
        favoriteListing.removeFromGeneratedFavorites(username, listingID);
    }

}
