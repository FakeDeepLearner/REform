package Controllers;

import Entities.User;
import Exceptions.UndefinedInputException;

import java.util.List;
import java.util.Scanner;

public class InputHandler {
    private Scanner sc;
    private UserInterface ui;

    public InputHandler(UserInterface ui) {
        this.ui = ui;

        sc = new Scanner(System.in);
    }

    public int intInput(List<Integer> allowedInputs) {
        int input = -1;
        while (!allowedInputs.contains(input)) {
            String choice = sc.next();
            try {
                input = Integer.parseInt(choice);
            } catch (NumberFormatException e) {
                ui.HandleNumberFormatException();
            }

            if (!allowedInputs.contains(input)) {
                System.out.println("Please enter a valid number.");
            }
        }

        return input;
    }

    public String strInput() {
        return sc.next();
    }
}
