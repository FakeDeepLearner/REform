package entities;

import java.math.BigDecimal;

public class Listing {
    private final int id;

    /* Components of an address taken from Canada Post
     * https://www.canadapost-postescanada.ca/cpc/en/support/articles/addressing-guidelines/civic-address.page
     */
    // Integer class used to allow for variable to be assigned a null value
    private final Integer unitNumber;
    private final int civicAddress;
    private final String streetName;
    private final String city;
    private final int bedrooms;
    private final int bathrooms;
    private final Integer floors;
    private BigDecimal price;
    private final String info;
    private boolean isSold;

    enum ListingType {
        APARTMENT,
        HOUSE,
        TOWNHOUSE
    }
    private final ListingType type;

    private final boolean isUnit;

    /**
     * Creates a listing without a unit number
     *
     * @param id           the unique id associated with the listing
     * @param civicAddress the civic address of the listing
     * @param streetName   the street name of the listing
     * @param city         the city the listing is located
     * @param type         the type of housing of the listing
     * @param bedrooms     the number of bedrooms in the listing
     * @param bathrooms    the number of bathrooms in the listing
     * @param floors       the number of floors in the listing
     * @param price        the price of the listing
     * @param info         additional information about the listing
     */
    public Listing(int id, int civicAddress, String streetName, String city, String type, int bedrooms, int bathrooms,
                   int floors, BigDecimal price, String info) {
        this.id = id;
        this.civicAddress = civicAddress;
        this.streetName = streetName;
        this.city = city;
        if (isStringValidEnum(type)) {
            this.type = Enum.valueOf(ListingType.class, type.toUpperCase());
        } else {
            throw new IllegalArgumentException();
        }
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        this.floors = floors;
        this.price = price;
        this.info = info;

        unitNumber = null;
        isSold = false;
        isUnit = false;
    }

    /**
     * Creates a listing with a unit number
     *
     * @param id           the unique id associated with the listing
     * @param unitNumber   the unit number of the listing
     * @param civicAddress the civic address of the listing
     * @param streetName   the street name of the listing
     * @param city         the city the listing is located
     * @param type         the type of housing of the listing
     * @param bedrooms     the number of bedrooms in the listing
     * @param bathrooms    the number of bathrooms in the listing
     * @param price        the price of the listing
     * @param info         additional information about the listing
     */
    public Listing(int id, int unitNumber, int civicAddress, String streetName, String city, String type, int bedrooms,
                   int bathrooms, BigDecimal price, String info) {
        this.id = id;
        this.unitNumber = unitNumber;
        this.civicAddress = civicAddress;
        this.streetName = streetName;
        this.city = city;
        if (isStringValidEnum(type)) {
            this.type = Enum.valueOf(ListingType.class, type.toUpperCase());
        } else {
            throw new IllegalArgumentException();
        }
        this.bedrooms = bedrooms;
        this.bathrooms = bathrooms;
        floors = null;
        this.price = price;
        this.info = info;
        isSold = false;
        isUnit = true;
    }

    /**
     * Checks whether a string is a valid value in ListingType
     *
     * @param input the string to be checked
     * @return whether input is a valid value in ListingType
     */
    private boolean isStringValidEnum(String input) {
        try {
            Enum.valueOf(ListingType.class, input.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    /**
     * @return the id of the listing
     */
    public int getId() {
        return id;
    }

    /**
     * @return the unit number of the listing
     */
    public Integer getUnitNumber() {
        return unitNumber;
    }

    /**
     * @return the civic address of the listing
     */
    public int getCivicAddress() {
        return civicAddress;
    }

    /**
     * @return the street name of the listing
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * @return the city where the listing is located
     */
    public String getCity() {
        return city;
    }

    /**
     * @return the type of housing of the listing
     */
    public String getType() {
        return type.toString();
    }

    /**
     * Returns the number of bedrooms in a listing
     *
     * @return number of bedrooms in the listing
     */
    public int getBedrooms() {
        return bedrooms;
    }

    /**
     * @return number of bathrooms in the listing
     */
    public int getBathrooms() {
        return bathrooms;
    }

    /**
     * @return number of floors in the listing
     */
    public Integer getFloors() {
        return floors;
    }

    /**
     * @param price the new price to be set
     */
    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    /**
     * @return the price of the listing
     */
    public BigDecimal getPrice() {
        return price;
    }

    /**
     * @return additional information about the listing
     */
    public String getInfo() {
        return info;
    }

    /**
     * @return the status of the listing
     */
    public boolean getIsSold() {
        return isSold;
    }

    /**
     * @return whether the listing has a unit number
     */
    public boolean getIsUnit() {
        return isUnit;
    }

    /**
     * Sells a listing by updating its isSold status
     */
    public void sell() {
        isSold = true;
    }

    /**
     * @return the full address of the listing
     */
    public String toString() {
        String address = (unitNumber == null ? "" : unitNumber + " - ") + civicAddress + " " + streetName + ", " + city;
        return (isSold ? "SOLD" : "AVAILABLE") + ": " + address + "\n" + info;
    }
}
