package Controllers;

/**
 * This is a temporary class to test out alternative implementation of the Controllers.InputHandler.
 **/

public class InputHandler2 {

    private int intInput;
    private String stringInput;
    private boolean isInt;

    public void takeInput(String input){

        if (InputHandler2.isNumeric(input)){
            this.intInput = Integer.parseInt(input);
            this.isInt = true;
        }
        else{
            this.stringInput = input;
            this.isInt = false;
        }
    }

    public static boolean isNumeric(String input) {
        int n;

        try {
            n = Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
        }

        return false;
    }

    public int getIntInput() {
        return intInput;
    }

    public String getStringInput() {
        return stringInput;
    }

    public boolean isInt() {
        return isInt;
    }

}
