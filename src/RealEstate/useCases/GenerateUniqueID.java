package RealEstate.useCases;

import RealEstate.entities.Listing;
import RealEstate.entities.ListingContainer;
import RealEstate.entities.MessageContainer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateUniqueID {
    private final static ArrayList<Integer> existingIDs = new ArrayList<>();

    // 9-digits integer from 100000000 to 999999999
    private final static int minID = 100000000;
    private final static int maxID = 999999999;

    public static int getUniqueId() {
        int returned_id = -1;
        while (existingIDs.contains(returned_id) || returned_id == -1 ) {
            returned_id = ThreadLocalRandom.current().nextInt(minID, maxID + 1);
        }
        existingIDs.add(returned_id);
        return returned_id;
    }

    /*

    private ArrayList<Integer> existingListingIDs;
    private ArrayList<Integer> getExistingMessageIDs;

    public GenerateUniqueID(ListingContainer<Integer, Listing> l) {
        this.existingListingIDs = this.getIDsFromListingContainer(l);
    }

    public GenerateUniqueID(MessageContainer<Integer, Listing> l) {
        this.getExistingMessageIDs = this.getIDsFromMessageContainer(l);
    }

    public int getUniqueID(ListingContainer<Integer, Listing> l) {
        int out = -1;
        while (this.existingListingIDs.contains(out) | out == -1) {
            out = ThreadLocalRandom.current().nextInt(minID, maxID + 1);
        }

        return out;
    }

    public int getUniqueID(MessageContainer<Integer, Listing> l) {
        int out = -1;
        while (this.getExistingMessageIDs.contains(out) | out == -1) {
            out = ThreadLocalRandom.current().nextInt(minID, maxID + 1);
        }

        return out;
    }

    public int getUniqueID() {
        int out = -1;
        while (this.existingListingIDs.contains(out) | this.existingListingIDs.contains(out) | out == -1) {
            out = ThreadLocalRandom.current().nextInt(minID, maxID + 1);
        }
        return out;
    }


    private ArrayList<Integer> getIDsFromListingContainer(ListingContainer<Integer, Listing> l) {

        return new ArrayList<Integer>(l.keySet());
    }

    private ArrayList<Integer> getIDsFromMessageContainer(MessageContainer<Integer, Listing> l) {

        return new ArrayList<Integer>(l.keySet());
    }

    public int getMaxID() {
        return maxID;
    }

    public int getMinID() {
        return minID;
    }

    public ArrayList<Integer> getExistingListingIDs() {
        return existingListingIDs;
    }

    public ArrayList<Integer> getGetExistingMessageIDs() {
        return getExistingMessageIDs;
    }

    public void setMaxID(int maxID) {
        this.maxID = maxID;
    }

    public void setMinID(int minID) {
        this.minID = minID;
    }

    public void setExistingListingIDs(ArrayList<Integer> existingListingIDs) {
        this.existingListingIDs = existingListingIDs;
    }

    public void setGetExistingMessageIDs(ArrayList<Integer> getExistingMessageIDs) {
        this.getExistingMessageIDs = getExistingMessageIDs;
    }
    */
}
