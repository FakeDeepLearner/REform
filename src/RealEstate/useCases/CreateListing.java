package RealEstate.useCases;

import RealEstate.entities.Listing;
import RealEstate.entities.ListingContainer;

import java.io.*;
import java.math.BigDecimal;

public class CreateListing {
    private final ListingContainer<Integer, Listing> listings;

    public CreateListing(ListingContainer<Integer, Listing> listingsInterface) {
        listings = listingsInterface;
    }

    public void addListing(int civicAddress, String streetName, String city, String type, int bedrooms,
                              int bathrooms, BigDecimal price) {
        int uniqueID = GenerateUniqueID.getUniqueID();
        Listing listing = new Listing(uniqueID, civicAddress, streetName, city, type, bedrooms, bathrooms, price);
        listings.put(uniqueID, listing);
    }

    public void readListings() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/UserHistories.csv"));
            String line;

            while ((line = br.readLine()) != null) {
                Object[] contents = line.split(",");
                Listing l = new Listing((int) contents[0], (int) contents[1], (String) contents[2],
                        (String) contents[3], (String) contents[4], (int) contents[5], (int) contents[6],
                        (BigDecimal) contents[7]);
                listings.put((int) contents[0], l);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeListings() {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Listings.csv", true));

            for (Listing l : listings.values()) {
                bw.append(String.valueOf(l.getId()));
                bw.append(",");
                bw.append(String.valueOf(l.getCivicAddress()));
                bw.append(",");
                bw.append(l.getStreetName());
                bw.append(",");
                bw.append(l.getCity());
                bw.append(",");
                bw.append(l.getType());
                bw.append(",");
                bw.append(String.valueOf(l.getBedrooms()));
                bw.append(",");
                bw.append(String.valueOf(l.getCivicAddress()));
                bw.append(",");
                bw.append(String.valueOf(l.getPrice()));

                bw.append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
