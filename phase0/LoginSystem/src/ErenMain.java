import Controllers.InputHandler3;
import Controllers.InterfaceManager;
import Controllers.UserInterface;
import Entities.User;
import Entities.UserNameAndPasswordContainer;

public class ErenMain {
    public static void main(String[] args) throws InterruptedException {
        UserNameAndPasswordContainer<String, User> container = new UserNameAndPasswordContainer<String, User>();
        InputHandler3 inputHandler3 = new InputHandler3();
        UserInterface userInterface = new UserInterface(inputHandler3);
        InterfaceManager interfaceManager = new InterfaceManager(container);
        inputHandler3.setInterfaceManager(interfaceManager);
        inputHandler3.setUserInterface(userInterface);
        userInterface.printWelcomeMessage();
    }

}
