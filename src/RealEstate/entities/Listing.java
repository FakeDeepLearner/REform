package RealEstate.entities;

import java.math.BigDecimal;

public class Listing {
    private final int id;

    /* Components of an address taken from Canada Post
     * https://www.canadapost-postescanada.ca/cpc/en/support/articles/addressing-guidelines/civic-address.page
     */
    // Integer class used to allow for it to be assigned null
    private final Integer unitNumber;
    private final int civicAddress;
    private final String streetName;
    private final String city;
    private final int bedrooms;
    private final int bathrooms;
    private final Integer floors;
    private BigDecimal price;

    private boolean isSold;

    enum ListingType {
        APARTMENT,
        HOUSE,
        TOWNHOUSE
    }
    private final ListingType type;

    public Listing(int id, int civicAddress, String streetName, String city, String type, int bedrooms, int bathrooms,
                   BigDecimal price) {
        this.id = id;
        this.civicAddress = civicAddress;
        this.streetName = streetName;
        this.city = city;
        if (isStringValidEnum(type)) {
            this.type = Enum.valueOf(ListingType.class, type);
        } else {
            throw new IllegalArgumentException();
        }
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.price = price;

        unitNumber = null;
        floors = null;
        isSold = false;
    }

    public Listing(int id, int unitNumber, int civicAddress, String streetName, String city, String type, int bedrooms,
                   int bathrooms, int floors, BigDecimal price) {
        this.id = id;
        this.unitNumber = unitNumber;
        this.civicAddress = civicAddress;
        this.streetName = streetName;
        this.city = city;
        if (isStringValidEnum(type)) {
            this.type = Enum.valueOf(ListingType.class, type);
        } else {
            throw new IllegalArgumentException();
        }
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.floors = floors;
        this.price = price;
        isSold = false;
    }

    private boolean isStringValidEnum(String input){
        try {
            Enum.valueOf(ListingType.class, input);
            return true;
        } catch(IllegalArgumentException e) {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public Integer getUnitNumber() {
        return unitNumber;
    }

    public int getCivicAddress() {
        return civicAddress;
    }

    public String getStreetName() {
        return streetName;
    }

    public String getCity() {
        return city;
    }

    public String getType() {
        return type.toString();
    }

    public int getBedrooms() {
        return bedrooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public Integer getFloors() {
        return floors;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public boolean getIsSold() {
        return isSold;
    }

    public void sell() {
        isSold = true;
    }

    public String toString() {
        String address = (unitNumber == null ? "" : unitNumber + " - ") + civicAddress + " " + streetName + ", " + city;
        String completeAddress;
        if(isSold){
            completeAddress = address + ", SOLD";
        }
        else{
            completeAddress = address + ", AVAILABLE";
        }
        return completeAddress;
    }
}
