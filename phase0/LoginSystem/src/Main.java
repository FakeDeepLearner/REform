import java.io.IOException;
import java.util.Scanner;  //  import the Scanner class
import Controllers.*;
import Entities.*;
import Exceptions.*;
import databaseManagers.*;
import useCases.*;

public class Main{
    public static void main(String[] args) throws InterruptedException, IOException {
        /**
         * Temporarily commented out to test the UsernamePasswordFileManager class
         *
        // Initiate the classes
        UserNameAndPasswordContainer<String, User> users = new UserNameAndPasswordContainer<String, User>();
        // TODO: Load the database to <users>
        InterfaceManager manager = new InterfaceManager(users);
        UserInterface userInterface = new UserInterface(new InputHandler3());

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
         */

        UsernamePasswordFileManager manager = new UsernamePasswordFileManager();

        NonAdminUser u1 = new NonAdminUser("u1", "pw1");
        NonAdminUser u2 = new NonAdminUser("u2", "pw2");
        NonAdminUser u3 = new NonAdminUser("u3", "pw3");

        UserNameAndPasswordContainer c = new UserNameAndPasswordContainer();

        c.put("u1", u1);
        c.put("u2", u1);
        c.put("u3", u1);

        manager.createUsernamePasswordFile(c);

        NonAdminUser u4 = new NonAdminUser("AddedUser", "pw4");

        manager.addUserInfo(u4);





    }
}
