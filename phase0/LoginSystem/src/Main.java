import java.util.Scanner;  //  import the Scanner class
public class Main{
    public static void main(String[] args) {
        // Initiate the classes
        UserNameAndPasswordContainer<String, User> users = new UserNameAndPasswordContainer<String, User>();
        // TODO: Load the database to <users>
        InterfaceManager manager = new InterfaceManager(users);
        UserInterface userInterface = new UserInterface();

        InputHandler2 inputHandler = new InputHandler2();

        userInterface.printWelcomeMessage();

        // Scanner has to be called in Main.java
        Scanner sc = new Scanner(System.in);

        boolean RUN = true;
        while(RUN) {
            String terminalInput = sc.nextLine();   // takes user input
            inputHandler.takeInput(terminalInput);  // stores in input

            if (inputHandler.isInt()){              // input is an integer
                // TODO: <manager> preforms appropriate action based on the integer input
            }
            else{
                // TODO: <manager> preforms appropriate action based on the input
            }
        }





    }
}
