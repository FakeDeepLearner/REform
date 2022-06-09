import java.util.HashMap;
import java.util.Scanner;

public class Main extends HashMap<String ,String> {
    public static void main(String[] args) throws InterruptedException {
        UserNameAndPasswordContainer<String> users = new UserNameAndPasswordContainer<String>();
        InterfaceManager manager = new InterfaceManager(users);
        UserInterface userInterface = new UserInterface(manager);
        userInterface.printWelcomeMessage();
    }

}
