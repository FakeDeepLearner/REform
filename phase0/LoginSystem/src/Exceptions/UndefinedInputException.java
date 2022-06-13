package Exceptions;

public class UndefinedInputException extends IllegalArgumentException {
    public UndefinedInputException() {
        super("Undefined input, please try again.");
    }
}
