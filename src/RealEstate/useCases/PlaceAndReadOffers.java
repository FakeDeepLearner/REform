package RealEstate.useCases;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.Scanner;

public class PlaceAndReadOffers {

    /**
     * Writes a new message to csv file.
     *
     * @param sendingUsername the sending user's username.
     * @param receivingUsername the receiving user's username.
     */
    public void updateMessageHistory(String sendingUsername, String receivingUsername, String message) {

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("src/UserMessages.csv", true));
            Scanner sc = new Scanner("src/UserMessages.csv");

            while (sc.hasNext()) {
                sc.next();
            }

            bw.append(sendingUsername);
            bw.append(",");
            bw.append(receivingUsername);
            bw.append(",");
            bw.append(message);
            bw.append("\n");

            bw.flush();
            bw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads the user's message history from the csv file.
     *
     * @param username this user's username.
     * @return an arraylist of the received messages that include sender's username in the Strings.
     */
    public ArrayList<String> readUserMessageHistory(String username) {
        ArrayList<String> userMessageHistory = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("src/UserMessages.csv"));
            String line;

            while ((line = br.readLine()) != null) {
                ArrayList<String> contents = new ArrayList<>(Arrays.asList(line.split(",")));

                if (username.equals(contents.get(1))) {
                    String message = String.join(",", contents.subList(2, contents.size()));
                    userMessageHistory.add(contents.get(0) + ": " + message);
                }
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userMessageHistory;
    }
}