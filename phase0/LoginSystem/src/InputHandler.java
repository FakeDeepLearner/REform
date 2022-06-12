import java.util.Scanner;

public class InputHandler {
    private InterfaceManager manager;
    private Scanner sc;

    public InputHandler(InterfaceManager manager) {
        this.manager = manager;
        Scanner sc = new Scanner(System.in);
    }

    public String loginInput() {
        return sc.next();
    }

    public String signUpInput() {
        return sc.next();
    }
}
