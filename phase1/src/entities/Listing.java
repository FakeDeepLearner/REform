package entities;

public class Listing {
    /* Components of an address taken from Canada Post
     * https://www.canadapost-postescanada.ca/cpc/en/support/articles/addressing-guidelines/civic-address.page
     */
    private final int unitNumber;
    private final int civicAddress;
    private final String streetName;
    private final String city;

    public Listing(int civicAddress, String streetName, String city) {
        this.civicAddress = civicAddress;
        this.streetName = streetName;
        this.city = city;

        // int cannot be assigned null so -1 will be treated as null
        unitNumber = 0;
    }

    public Listing(int unitNumber, int civicAddress, String streetName, String city) {
        this.unitNumber = unitNumber;
        this.civicAddress = civicAddress;
        this.streetName = streetName;
        this.city = city;
    }

    public String getAddress() {
        return (unitNumber == 0 ? "" : unitNumber + " - ") + civicAddress + " " + streetName + ", " + city;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }
}
