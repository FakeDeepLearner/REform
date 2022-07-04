package RealEstate.useCases;

import RealEstate.entities.Listing;

import java.io.*;
import java.math.BigDecimal;

public class CreateListing {
    public void createListing(int id, int civicAddress, String streetName, String city, String type, int bedrooms,
                              int bathrooms, BigDecimal price) {
        Listing listing = new Listing(id, civicAddress, streetName, city, type, bedrooms, bathrooms, price);

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/Listings.csv", false));

            bw.append(String.valueOf(id));
            bw.append(",");
            bw.append(String.valueOf(civicAddress));
            bw.append(",");
            bw.append(streetName);
            bw.append(",");
            bw.append(city);
            bw.append(",");
            bw.append(type);
            bw.append(",");
            bw.append(String.valueOf(bedrooms));
            bw.append(",");
            bw.append(String.valueOf(civicAddress));
            bw.append(",");
            bw.append(String.valueOf(price));
            bw.append(",");
            bw.append("\n");

            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
