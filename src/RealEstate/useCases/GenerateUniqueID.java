package RealEstate.useCases;

import RealEstate.entities.Listing;
import RealEstate.entities.ListingContainer;
import RealEstate.entities.MessageContainer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateUniqueID {
    private ArrayList<Integer> existingIDs = new ArrayList<>();

    // 9-digits integer from 100000000 to 999999999
    private int minID = 100000000;
    private int maxID = 999999999;


    // New implementation of the class
    public int getUniqueID2() {
        int returned_id = -1;
        while (existingIDs.contains(returned_id) || returned_id == -1) {
            returned_id = ThreadLocalRandom.current().nextInt(minID, maxID + 1);
        }
        existingIDs.add(returned_id);
        return returned_id;
    }


    // Original implementation of the class
    // TODO: Decide which version will be used
    private ArrayList<Integer> existingListingIDs;
    private ArrayList<Integer> existingMessageIDs;

    /**
     * Create GenerateUniqueID with a ListingContainer
     *
     * @param l Listing Container
     */
    public GenerateUniqueID(ListingContainer<Integer, Listing> l) {
        this.existingListingIDs = this.getIDsFromListingContainer(l);
    }

    /**
     * Create GenerateUniqueID with a MessageContainer
     *
     * @param l Message Container
     */
    public GenerateUniqueID(MessageContainer<Integer, Listing> l) {
        this.existingMessageIDs = this.getIDsFromMessageContainer(l);
    }

    /**
     * Get a unique ID from a ListingContainer Alone
     *
     * @param l ListingContainer
     * @return A unique Listing ID
     */
    public int getUniqueID(ListingContainer<Integer, Listing> l) {
        int out = -1;
        while (this.existingListingIDs.contains(out) | out == -1) {
            out = ThreadLocalRandom.current().nextInt(minID, maxID + 1);
        }

        return out;
    }

    /**
     * Get a unique ID from a MessageContainer Alone
     *
     * @param l MessageContainer
     * @return A unique Message ID
     */
    public int getUniqueID(MessageContainer<Integer, Listing> l) {
        int out = -1;
        while (this.existingMessageIDs.contains(out) | out == -1) {
            out = ThreadLocalRandom.current().nextInt(minID, maxID + 1);
        }

        return out;
    }

    /**
     * Get a unique ID from a ListingIDs and MessageIDs
     *
     * @return An ID number unique to both Listings and Messages
     */
    public int getUniqueID() {
        int out = -1;
        while (this.existingListingIDs.contains(out) | this.existingMessageIDs.contains(out) | out == -1) {
            out = ThreadLocalRandom.current().nextInt(minID, maxID + 1);
        }
        return out;
    }


    /**
     * Get all the IDs from a ListingContainer
     *
     * @param l ListingContainer
     * @return An arraylist containing the IDs of Listings from ListingContainer
     */
    private ArrayList<Integer> getIDsFromListingContainer(ListingContainer<Integer, Listing> l) {

        return new ArrayList<Integer>(l.keySet());
    }

    /**
     * Get all the IDs from a MessageContainer
     *
     * @param l MessageContainer
     * @return An arraylist containing the IDs of Listings from MessageContainer
     */
    private ArrayList<Integer> getIDsFromMessageContainer(MessageContainer<Integer, Listing> l) {

        return new ArrayList<Integer>(l.keySet());
    }

    /**
     * Getter for maxID
     *
     * @return maxID
     */
    public int getMaxID() {
        return maxID;
    }

    /**
     * Getter for minID
     *
     * @return minID
     */
    public int getMinID() {
        return minID;
    }

    /**
     * Getter for existingListingIDs
     *
     * @return existingListingIDs
     */
    public ArrayList<Integer> getExistingListingIDs() {
        return existingListingIDs;
    }

    /**
     * Getter for existingMessageIDs
     *
     * @return existingMessageIDs
     */
    public ArrayList<Integer> getExistingMessageIDs() {
        return existingMessageIDs;
    }

    /**
     * Setter for MaxID
     *
     * @param maxID New MaxID
     */
    public void setMaxID(int maxID) {
        this.maxID = maxID;
    }

    /**
     * Setter for minID
     *
     * @param minID New minID
     */
    public void setMinID(int minID) {
        this.minID = minID;
    }

    /**
     * Setter for existingListingIDs
     *
     * @param existingListingIDs new existingListingIDs
     */
    public void setExistingListingIDs(ArrayList<Integer> existingListingIDs) {
        this.existingListingIDs = existingListingIDs;
    }

    /**
     * Setter for existingMessageIDs
     *
     * @param existingMessageIDs new existingMessageIDs
     */
    public void setExistingMessageIDs(ArrayList<Integer> existingMessageIDs) {
        this.existingMessageIDs = existingMessageIDs;
    }


}
