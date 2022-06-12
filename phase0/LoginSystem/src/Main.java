import java.util.HashMap;
import java.util.Scanner;

public class Main extends HashMap<String ,String> {
    public static void main(String[] args) throws InterruptedException {
        UserNameAndPasswordContainer<String, User> users = new UserNameAndPasswordContainer<String, User>();
        InterfaceManager manager = new InterfaceManager(users);
        UserInterface userInterface = new UserInterface(manager);
        userInterface.printWelcomeMessage();
    }
}
