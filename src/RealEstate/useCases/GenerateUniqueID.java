package RealEstate.useCases;

import RealEstate.entities.Listing;
import RealEstate.entities.ListingContainer;
import RealEstate.entities.MessageContainer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateUniqueID {

    private ArrayList<Integer> existingListingIDs;
    private ArrayList<Integer> getExistingMessageIDs;

    // 9-digits integer from 100000000 to 999999999
    private int minID = 100000000;
    private int maxID = 999999999;


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

        ArrayList<Integer> out = new ArrayList<Integer>();
        for (Integer id : l.keySet()) {
            out.add(id);
        }

        return out;
    }

    private ArrayList<Integer> getIDsFromMessageContainer(MessageContainer<Integer, Listing> l) {

        ArrayList<Integer> out = new ArrayList<Integer>();
        for (Integer id : l.keySet()) {
            out.add(id);
        }

        return out;
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


}
