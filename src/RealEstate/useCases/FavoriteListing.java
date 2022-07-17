package RealEstate.useCases;

import RealEstate.entities.Buyer;
import RealEstate.entities.BuyerContainer;

import java.util.ArrayList;
import java.util.HashMap;

public class FavoriteListing {
    private final BuyerContainer<String, Buyer> buyerContainer;

    private final HashMap<String, ArrayList<Integer>> generatedFavorites;

    public FavoriteListing(BuyerContainer<String, Buyer> buyerContainer) {
        this.buyerContainer = buyerContainer;
        this.generatedFavorites = new HashMap<>();
    }

    public HashMap<String, ArrayList<Integer>> getGeneratedFavorites() {
        return generatedFavorites;
    }

    public BuyerContainer<String, Buyer> getBuyerContainer() {
        return buyerContainer;
    }

    public void addListingToBuyerFavorites(String username, int listingID) {
        Buyer buyer = buyerContainer.get(username);
        if (!buyer.getFavorites().contains(listingID)) {
            buyer.addFavouriteListing(listingID);
        }

    }

    public void addCreationToGeneratedFavorites(String username, int listingID){
        if (generatedFavorites.containsKey(username)){
            if (!generatedFavorites.get(username).contains(listingID)){
                generatedFavorites.get(username).add(listingID);
            }
        }
        else{
            ArrayList<Integer> value = new ArrayList<>();
            value.add(listingID);
            generatedFavorites.put(username, value);
        }
    }

    public void removeListingFromBuyerFavorites(String username, int listingID) {
        Buyer buyer = buyerContainer.get(username);
        buyer.removeFavouriteListing(listingID);
    }

    public void removeFromGeneratedFavorites(String username, int listingID) {
        if (generatedFavorites.containsKey(username)){
            generatedFavorites.get(username).remove(listingID);
        }
    }
}
