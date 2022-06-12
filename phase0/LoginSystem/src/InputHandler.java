import java.util.Scanner;

public class InputHandler {
    public void LoginInput() {
        // relay(input);
        Scanner myInput = new Scanner(System.in);
        String scanned = myInput.next();
        int input = Integer.parseInt(scanned);

        try {
            if (input == 1) {
                triggerUserSignUp();
            } else if (input == 2) {
                triggerUserLogIn();
            } else {
                throw new UndefinedInputException();
            }
        }

        catch (Exception exception){
            System.out.println(exception.getMessage());
            Thread.sleep(3000);
            this.printWelcomeMessage();
        }
    }

    // relay(input);
    private void triggerUserSignUp(){
        System.out.println("Enter your username: ");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        try{
            User user = manager.get_interface_users().get(username);
        }
        catch(UserNotFoundException exception){
            System.out.println(exception.getMessage());
            triggerUserSignUp();
        }
    }

    // relay(input);
    private void triggerUserLogIn(){
        System.out.println("Log in triggered");

    }
}
