package RealEstate.exceptions;

public class ListingNotFoundException extends IllegalArgumentException{
    public ListingNotFoundException(){
        super("No Such Listing with given id");
    }
}