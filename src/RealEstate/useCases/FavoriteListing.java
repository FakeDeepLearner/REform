package RealEstate.useCases;

import RealEstate.entities.Buyer;
import RealEstate.entities.BuyerContainer;

public class FavoriteListing {
    private final BuyerContainer<String, Buyer> buyerContainer;

    public FavoriteListing(BuyerContainer<String, Buyer> buyerContainer) {
        this.buyerContainer = buyerContainer;
    }

    public BuyerContainer<String, Buyer> getBuyerContainer() {
        return buyerContainer;
    }

    public void addListingToBuyerFavorites(String username, int listingID) {
        Buyer buyer = buyerContainer.get(username);
        buyer.addFavouriteListing(listingID);
    }

    public void removeListingFromBuyerFavorites(String username, int listingID) {
        Buyer buyer = buyerContainer.get(username);
        buyer.removeFavouriteListing(listingID);
    }



    /*
    private static ArrayList<String> dumpUsernamesIntoArrayList() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        ArrayList<String> returnedArrayList = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            returnedArrayList.add((line.split(","))[0]);
        }
        return returnedArrayList;
    }

    private static ArrayList<String> dumpFileIntoArrayList() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        ArrayList<String> returnedArrayList = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            returnedArrayList.add(line);
        }

        return returnedArrayList;
    }
    public void updateUserFavoriteEntry(String username, int listingID) {
        ArrayList<String> usernamesInFile =  new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            BufferedWriter overWriter = new BufferedWriter(new FileWriter(filePath, false));
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true));
            String line;
            while ((line = reader.readLine()) != null) { // Get all the usernames in the file
                usernamesInFile.add((line.split(","))[0]);
            }

            if (usernamesInFile.contains(username)) {
                addListingToBuyerFavorites(username, listingID);
                ArrayList<String> allUsers = dumpUsernamesIntoArrayList();
                ArrayList<String> fileContent = dumpFileIntoArrayList();
                int usernameIndex = allUsers.indexOf(username);
                String removedLine = fileContent.remove(usernameIndex);
                for (int i = 0; i < fileContent.size(); i++) {
                    if (i == 0) {
                        overWriter.append(fileContent.get(i));
                    }
                    else{
                        writer.append(fileContent.get((i)));
                    }
                }
                String newLine = removedLine + " " + listingID;
                writer.append(newLine);
            }
            else {
                addListingToBuyerFavorites(username, listingID);
                usernamesInFile.add(username);
                StringBuilder stringBuilder = new StringBuilder(" ");
                stringBuilder.append(username);
                stringBuilder.append(", ");
                Buyer buyer = buyerContainer.get(username);
                ArrayList<Integer> buyerFavorites = buyer.getFavList();
                for (Integer value : buyerFavorites){
                    stringBuilder.append(value.toString()).append(", ");
                }
                writer.append(stringBuilder.toString());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    */
}
