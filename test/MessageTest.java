import entities.Message;
import entities.users.Buyer;
import entities.users.Seller;
import org.junit.Test;

public class MessageTest {

    @Test
    public void testMessageDateTime() {
        Buyer sender = new Buyer("u1", "pw1");
        Seller receiver = new Seller("u2", "pw2");

        String testString = "03-08-2022 22:52:35";

        Message m = new Message(sender, receiver, 123, "content", testString);

        System.out.println(m);
    }


}
