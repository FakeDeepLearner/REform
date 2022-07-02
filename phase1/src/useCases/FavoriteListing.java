package useCases;

import entities.User;
import entities.UserContainer;

import java.io.*;
import java.util.ArrayList;

public class FavoriteListing {
    private final UserContainer<String, User> userContainer;

    public FavoriteListing(UserContainer<String, User> userContainer) {
        this.userContainer = userContainer;
    }

    public UserContainer<String, User> getUserContainer() {
        return userContainer;
    }


    public void addListingToUserFavorites(String username, int listingID) {
        User user = userContainer.get(username);
        // TODO: Modify the User class so that it stores the listing, and then complete this method.
    }

    private static ArrayList<String> dumpUsernamesIntoArrayList() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/UsersAndFavorites.csv"));
        ArrayList<String> returnedArrayList = new ArrayList<>();
        String line;
        while((line = reader.readLine()) != null) {
            returnedArrayList.add((line.split(","))[0]);
        }
        return returnedArrayList;
    }

    private static ArrayList<String> dumpFileIntoArrayList() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/UsersAndFavorites.csv"));
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
            BufferedReader reader = new BufferedReader(new FileReader("src/UsersAndFavorites.csv"));
            BufferedWriter overWriter = new BufferedWriter(new FileWriter("src/UsersAndFavorites.csv", false));
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/UsersAndFavorites.csv", true));
            String line;
            while ((line = reader.readLine()) != null) { // Get all the usernames in the file
                usernamesInFile.add((line.split(","))[0]);
            }

            if (usernamesInFile.contains(username)) {
                addListingToUserFavorites(username, listingID);
                ArrayList<String> allUsers = dumpUsernamesIntoArrayList();
                ArrayList<String> fileContent = dumpFileIntoArrayList();
                int usernameIndex = allUsers.indexOf(username);
                fileContent.remove(usernameIndex);
                for (int i = 0; i < fileContent.size(); i++) {
                    if (i == 0) {
                        overWriter.append(fileContent.get(i));
                    }
                    else{
                        writer.append(fileContent.get((i)));
                    }
                }
                // TODO : Append the line that was initially removed, with the new listing ID now added.
            }
            else {
                addListingToUserFavorites(username, listingID);
                // TODO: Append the line to the file after the User entity has been updated.
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
