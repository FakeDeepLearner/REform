package entities;

public enum ListingEditable {
    PRICE,
    DESCRIPTION,
    BATHROOMS,
    FLOORS,
    BEDROOMS,
    UNIT_NUMBER,
    STREET,
    CITY,
    CIVIC_ADDRESS;

    public static ListingEditable fromString(String type){
        for (ListingEditable editable : ListingEditable.values()){
            if (editable.toString().equals(type.toUpperCase())){
                return editable;
            }
        }
        throw new IllegalArgumentException();
    }

}
