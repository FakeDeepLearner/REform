package gateways;

import entities.Listing;
import entities.Seller;
import entities.User;
import entities.containers.UserContainer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;

public non-sealed class ListingsCSVController extends CSVController {
    private final UserContainer<String, User> users;

    public ListingsCSVController(UserContainer<String, User> users) {
        super("Listings.csv");

        this.users = users;
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), false));
        for (String username : users.getAllSellers().keySet()){
            Seller u = users.getSeller(username);
            for (Listing listing : u.getListings()) {
                int ID = listing.getId();
                int civicAddress = listing.getCivicAddress();
                String streetName = listing.getStreetName();
                String city = listing.getCity();
                String type = listing.getType();
                int bedrooms = listing.getBedrooms();
                int bathrooms = listing.getBathrooms();
                BigDecimal price = listing.getPrice();
                String info = listing.getInfo();

                boolean isUnit = listing.getIsUnit();
                String lineToWrite;
                if (isUnit) {
                    int unitNumber = listing.getUnitNumber();
                    lineToWrite = username + "," + ID + "," + civicAddress + "," + streetName + "," +  city + "," +
                            type + "," + bedrooms + "," + bathrooms + "," + price + "," + info + "," + true + "," + unitNumber;
                }
                else {
                    int floors = listing.getFloors();
                    lineToWrite = username + "," + ID + "," + civicAddress + "," + streetName + "," + city + "," + type +
                            "," + bedrooms + "," + bathrooms + "," + price + "," + info + "," + false + "," + floors;
                }
                writer.append(lineToWrite).append("\n");
            }
        }
        writer.close();
    }
}

