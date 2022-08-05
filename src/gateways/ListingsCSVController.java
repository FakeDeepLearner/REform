package gateways;

import entities.Listing;
import entities.Seller;
import entities.User;
import entities.containers.UserContainer;
import useCases.CSVUseCases.DatabaseFilePath;
import useCases.DataInterface;
import useCases.listingUseCases.CreateListing;

import java.io.*;
import java.math.BigDecimal;

public class ListingsCSVController implements DataInterface {
    private final UserContainer<String, User> users;
    private final CreateListing createListing;
    private final static DatabaseFilePath filepath = new DatabaseFilePath("Listings.csv");

    public ListingsCSVController(UserContainer<String, User> users, CreateListing createListing) {
        this.users = users;
        this.createListing = createListing;
    }

    @Override
    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath.getFilePath()));
        String line;
        while((line = reader.readLine()) != null){
            String[] splitLine = line.split(",");

            String username = splitLine[0];
            int ID = Integer.parseInt(splitLine[1]);
            int civicAddress = Integer.parseInt(splitLine[2]);
            String streetName = splitLine[3];
            String city = splitLine[4];
            String type = splitLine[5];
            int bedrooms = Integer.parseInt(splitLine[6]);
            int bathrooms = Integer.parseInt(splitLine[7]);
            BigDecimal price = new BigDecimal(splitLine[8]);
            String info = splitLine[9];

            boolean isUnit = Boolean.parseBoolean(splitLine[10]);
            int unitNumberFloor = Integer.parseInt(splitLine[11]);
            if (isUnit) {
                Listing listing = createListing.addListing(ID, unitNumberFloor, civicAddress, streetName, city, type,
                        bedrooms, bathrooms, price, info);
                createListing.addListingToSeller(username, listing);
                createListing.addListingToCreatedListings(username, listing);
            }
            else {
                Listing listing = createListing.addListing(ID, civicAddress, streetName, city, type, bedrooms,
                        unitNumberFloor, bathrooms, price, info);
                createListing.addListingToSeller(username, listing);
                createListing.addListingToCreatedListings(username, listing);
            }
        }
        reader.close();
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

