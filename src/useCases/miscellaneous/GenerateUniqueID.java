package useCases.miscellaneous;

import entities.Listing;
import entities.containers.ListingContainer;
import entities.Message;
import entities.containers.MessageContainer;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateUniqueID {

    // 9-digits integer from 100000000 to 999999999
    private int minID = 100000000;
    private int maxID = 999999999;

    private final ArrayList<Integer> existingListingIDs;
    private final ArrayList<Integer> existingMessageIDs;

    /**
     * Create GenerateUniqueID with a ListingContainer
     *
     * @param l Listing Container
     */
    public GenerateUniqueID(ListingContainer<Integer, Listing> l) {
        this.existingListingIDs = this.getIDsFromListingContainer(l);
        this.existingMessageIDs = new ArrayList<>();
    }

    /**
     * Create GenerateUniqueID with a MessageContainer
     *
     * @param l Message Container
     */
    public GenerateUniqueID(MessageContainer<Integer, Message> l) {
        this.existingMessageIDs = this.getIDsFromMessageContainer(l);
        this.existingListingIDs = new ArrayList<>();
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

        return new ArrayList<>(l.keySet());
    }

    /**
     * Get all the IDs from a MessageContainer
     *
     * @param l MessageContainer
     * @return An arraylist containing the IDs of Listings from MessageContainer
     */
    private ArrayList<Integer> getIDsFromMessageContainer(MessageContainer<Integer, Message> l) {

        return new ArrayList<>(l.keySet());
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
}
