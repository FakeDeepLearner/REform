package exceptions;

public class ListingAlreadyFavouritedException extends UnsupportedOperationException{
    public ListingAlreadyFavouritedException(){
        super("You have already added this listing to your favorites");
    }

}
