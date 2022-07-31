package gateways;

import entities.Listing;
import useCases.listingUseCases.CreateListing;
import useCases.CSVUseCases.DatabaseFilePath;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;

public class ListingsCSVController implements CsvInterface {

    private final CreateListing createListing;

    private final static DatabaseFilePath filepath = new DatabaseFilePath("Listings.csv");

    public ListingsCSVController(CreateListing createListing) {
        this.createListing = createListing;
    }

    @Override
    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filepath.getFilePath()));
        String line;
        while((line = reader.readLine()) != null){
            String[] splitLine = line.split(",");
            if (splitLine.length == 11) {
                String username = splitLine[0];
                int ID = Integer.parseInt(splitLine[1]);
                int unitNumber = Integer.parseInt(splitLine[2]);
                int civicAddress = Integer.parseInt(splitLine[3]);
                String streetName = splitLine[4];
                String city = splitLine[5];
                String type = splitLine[6];
                int bedrooms = Integer.parseInt(splitLine[7]);
                int bathrooms = Integer.parseInt(splitLine[8]);
                int floors = Integer.parseInt(splitLine[9]);
                BigDecimal price = new BigDecimal(splitLine[10]);
                Listing listing = createListing.addListing(ID, unitNumber, civicAddress, streetName, city, type, bedrooms,
                        bathrooms, floors, price);
                createListing.addListingToSeller(username, listing);
                createListing.addListingToCreatedListings(username, listing);
            }
            else if (splitLine.length == 9){
                String username = splitLine[0];
                int ID = Integer.parseInt(splitLine[1]);
                int civicAddress = Integer.parseInt(splitLine[2]);
                String streetName = splitLine[3];
                String city = splitLine[4];
                String type = splitLine[5];
                int bedrooms = Integer.parseInt(splitLine[6]);
                int bathrooms = Integer.parseInt(splitLine[7]);
                BigDecimal price = new BigDecimal(splitLine[8]);
                Listing listing = createListing.addListing(ID, civicAddress, streetName, city, type, bedrooms,
                        bathrooms, price);
                createListing.addListingToSeller(username, listing);
                createListing.addListingToCreatedListings(username, listing);
            }
        }
        reader.close();
    }

    @Override
    public void write() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath.getFilePath(), false));
        HashMap<String, ArrayList<Listing>> createdListings = createListing.getCreatedListings();
        for (String username : createdListings.keySet()){
            for (Listing listing : createdListings.get(username)){
                int numAttributes = listing.getNumAttributes();
                if (numAttributes == 11) {
                    int ID = listing.getId();
                    int unitNumber = listing.getUnitNumber();
                    int civicAddress = listing.getCivicAddress();
                    String streetName = listing.getStreetName();
                    String city = listing.getCity();
                    String type = listing.getType();
                    int bedrooms = listing.getBedrooms();
                    int bathrooms = listing.getBathrooms();
                    int floors = listing.getFloors();
                    BigDecimal price = listing.getPrice();
                    String lineToWrite = username + "," + ID + "," + unitNumber + "," + civicAddress + "," + streetName +
                            "," + city + "," + type + "," + bedrooms + "," + bathrooms + "," + floors + "," + price;
                    writer.append(lineToWrite).append("\n");
                }
                else if (numAttributes == 9){
                    int ID = listing.getId();
                    int civicAddress = listing.getCivicAddress();
                    String streetName = listing.getStreetName();
                    String city = listing.getCity();
                    String type = listing.getType();
                    int bedrooms = listing.getBedrooms();
                    int bathrooms = listing.getBathrooms();
                    BigDecimal price = listing.getPrice();
                    String lineToWrite = username + "," + ID + "," + civicAddress + "," + streetName +
                            "," + city + "," + type + "," + bedrooms + "," + bathrooms + "," + price;
                    writer.append(lineToWrite).append("\n");
                }
            }
        }
        writer.close();
    }
}

