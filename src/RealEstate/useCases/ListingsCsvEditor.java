package RealEstate.useCases;

import RealEstate.entities.Listing;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListingsCsvEditor {

    private DatabaseFilePath filePath;

    /**
     * Create a ListingsCsvEditor class
     */
    public ListingsCsvEditor() {
        this.filePath = new DatabaseFilePath("Listings.csv");
    }

    /**
     * Add a Listing to the CSV file
     *
     * @param l Listing to be added
     * @throws IOException
     */
    public void addListingToCSV(Listing l) throws IOException {
        FileWriter fw = new FileWriter(filePath.getFilePath(), true);
        BufferedWriter output = new BufferedWriter(fw);

        String line = l.getListingInfos();

        output.append("\n").append(line);
        output.close();
    }

    /**
     * Static method to add the first line (naming the columns) as
     * "id","unitNumber","civicAddress","streetName","city","bedrooms","bathroomsfloors","price","isSold"
     *
     * @throws IOException
     */

    public static void addFirstLine() throws IOException {
        FileWriter fw = new FileWriter(DatabaseFilePath.getDatabasePath() + "Listings.csv");
        BufferedWriter output = new BufferedWriter(fw);
        output.write("id,unitNumber,civicAddress,streetName,city,bedrooms,bathroomsfloors,price,isSold");
        output.close();
    }


    ///////////////////////////////////////////////////////////////////////////
    // Reading the CSV file will be done in ListingsCSVController

//    /**
//     * Read the CSV file and get the Listings
//     *
//     * @return Array list of Listings
//     * @throws IOException
//     */
//    public ArrayList<Listing> getListingsFromCSV() throws IOException {
//        ArrayList<Listing> out = new ArrayList<>();
//
//        FileReader fr = new FileReader(filePath.getFilePath());
//
//        String line;
//        BufferedReader br = new BufferedReader(fr);
//        br.readLine();
//
//        while ((line = br.readLine()) != null) {
//            List<Object> infos = Arrays.asList(line.split(","));
//
//        }
//
//        return out;
//
//
//    }
////////////////////////////////////////////////////////////////////////////////

}
