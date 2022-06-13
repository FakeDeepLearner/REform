package Controllers;

import Controllers.InputHandler;
import Controllers.InputHandler2;
import Controllers.InputHandler3;
import Entities.User;
import Entities.UserNameAndPasswordContainer;
import Exceptions.UserNotFoundException;

import java.util.ArrayList;
import java.util.Collections;

public class InterfaceManager {
    private UserNameAndPasswordContainer<String, User> interfaceUsers;
    private InputHandler3 inputHandler3;

    public InterfaceManager(UserNameAndPasswordContainer<String, User> interfaceUsers, InputHandler3 inputHandler3) {
        this.interfaceUsers = interfaceUsers;
        this.inputHandler3 = inputHandler3;
    }


    public UserNameAndPasswordContainer<String, User> getInterfaceUsers() {
        return interfaceUsers;
    }

    public void menuSelector(InputHandler inputHandler) {
        ArrayList<Integer> allowedInputs = new ArrayList<Integer>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4);

        int menuSelect = inputHandler.intInput(allowedInputs);

        if (menuSelect == 1) {
            String username = inputHandler.strInput();

        } else if (menuSelect == 2) {
            String username = inputHandler.strInput();

            try {
                interfaceUsers.get(username);
            } catch (UserNotFoundException e) {
                // TODO: Create user
            }
        }
    }


    /**
     * Temporary method for testing
     */
    public void menuSelector(InputHandler2 handler) {
        ArrayList<Integer> allowedInputs = new ArrayList<Integer>();
        Collections.addAll(allowedInputs, 1, 2, 3, 4);

        int menuSelect = handler.getIntInput();

        if (menuSelect == 1) {
            String username = handler.getStringInput();

        } else if (menuSelect == 2) {
            String username = handler.getStringInput();

            try {
                interfaceUsers.get(username);
            } catch (UserNotFoundException e) {
                // TODO: Create user
            }
        }
    }


}
