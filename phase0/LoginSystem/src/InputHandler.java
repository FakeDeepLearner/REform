import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private Scanner sc;

    public InputHandler() {
        Scanner sc = new Scanner(System.in);
    }

    public int intInput() {
        String choice = sc.next();

        int input;
        try {
            input = Integer.parseInt(choice);
        } catch (NumberFormatException e) {
            throw new UndefinedInputException();
        }

        return input;
    }

    public int intInput(List<Integer> allowedInputs) {
        String choice = sc.next();

        int input;
        try {
            input = Integer.parseInt(choice);

            if (!allowedInputs.contains(input)) {
                throw new UndefinedInputException();
            }
        } catch (NumberFormatException e) {
            throw new UndefinedInputException();
        }

        return input;
    }

    public String strInput() {
        return sc.next();
    }
}
