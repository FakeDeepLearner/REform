
import Controllers.InputHandler;

import Controllers.InterfaceManager;
import Controllers.UserInterface;
import Entities.User;
import Entities.UserNameAndPasswordContainer;

import java.util.Scanner;  //  import the Scanner class
import Controllers.*;
import Entities.*;
import Exceptions.*;
import useCases.*;

public class ErenMain{
    public static void main(String[] args) {
        // Initiate the classes
        UserNameAndPasswordContainer<String, User> users = new UserNameAndPasswordContainer<>();
        // TODO: Load the database to <users>

        InterfaceManager manager = new InterfaceManager();

        while (true) {
            User u = manager.menuSelector();

            if (u == null) {
                continue;
            }

            while (u.isLoggedIn()) {
                if (!u.getAdmin()) {
                    manager.NonAdminScreen(u);
                } else {
                    manager.AdminScreen(u);
                }
            }
        }
    }
}
