package exceptions;

public class SellerNotFoundException extends IllegalArgumentException{
    public SellerNotFoundException() {
        super("Seller not found, please try again.");
    }
}
