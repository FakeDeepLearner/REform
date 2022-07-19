package LoginSystem.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class InputHandler {
    private final Scanner sc;
    private final UserInterface ui;

    public InputHandler(UserInterface ui) {
        this.ui = ui;

        sc = new Scanner(System.in);
    }

    /**
     * Gets any input integer from the terminal
     *
     * @return the input int from the terminal
     */
    public int intInput() {
        int input = 0;
        do {
            String choice = sc.next();
            try {
                input = Integer.parseInt(choice);

            } catch (NumberFormatException e) {
                ui.printInvalidInput();
            }
        } while (input == 0);

        return input;
    }

    /**
     * Gets a valid input integer from the terminal
     *
     * @param allowedInputs is a list of possible input int values
     * @return a valid input int from the terminal
     */
    public int intInput(List<Integer> allowedInputs) {
        int input = 0;
        do {
            String choice = sc.next();
            try {
                input = Integer.parseInt(choice);

                if (!allowedInputs.contains(input)) {
                    ui.printInvalidInput();
                }
            } catch (NumberFormatException e) {
                ui.printInvalidInput();
            }
        } while (input == 0 || !allowedInputs.contains(input));

        return input;
    }

    /**
     * Gets a valid input integer from the terminal
     *
     * @param min is the smallest (inclusive) possible int
     * @param max is the largest (inclusive) possible int
     * @return an input int from the terminal between min and max
     */
    public int intInput(int min, int max) {
        int input = 0;
        do {
            String choice = sc.next();
            try {
                input = Integer.parseInt(choice);

                if (!(input >= min && input <= max)) {
                    ui.printInvalidInput();
                }
            } catch (NumberFormatException e) {
                ui.printInvalidInput();
            }
        } while (input == 0 || !(input >= min && input <= max));

        return input;
    }

    /**
     * Gets any input BigDecimal from the terminal
     *
     * @return an input BigDecimal from the terminal
     */
    public BigDecimal bigDecimalInput() {
        double input = 0.0;
        do {
            String choice = sc.next();
            try {
                input = Double.parseDouble(choice);

            } catch (NumberFormatException e) {
                ui.printInvalidInput();
            }
        } while (input == 0.0);

        return BigDecimal.valueOf(input);
    }

    /**
     * Gets any input String from the terminal
     *
     * @return an input String from the terminal
     */
    public String strInput() {
        return sc.next();
    }

    /**
     * Gets a valid input String from the terminal
     *
     * @param allowedInputs is a list of possible input (case-sensitive) values
     * @return a valid input String from the terminal
     */
    public String strInput(List<String> allowedInputs) {
        String input;
        do {
            input = sc.next();

            if (!allowedInputs.contains(input)) {
                ui.printInvalidInput();
            }
        } while (!allowedInputs.contains(input));

        return input;
    }

    /**
     * Gets a valid input String from the terminal
     *
     * @param allowedInputs is a list of possible input values
     * @param isCaseInsensitive determines whether the check for a valid input is case-insensitive
     * @return a valid input String from the terminal
     */
    public String strInput(List<String> allowedInputs, boolean isCaseInsensitive) {
        if (isCaseInsensitive) {
            allowedInputs.replaceAll(String::toLowerCase);
        }

        String input;
        do {
            input = isCaseInsensitive ? sc.next().toLowerCase() : sc.next();

            if (!allowedInputs.contains(input)) {
                ui.printInvalidInput();
            }
        } while (!allowedInputs.contains(input));

        return input;
    }
}
