import entities.containers.UserContainer;
import entities.Buyer;
import entities.containers.MessageContainer;
import entities.Seller;
import useCases.messageUseCases.SendMessages;
import org.junit.Test;

public class SendMessagesTest {

    @Test
    public void testSendingMessage() {

        Buyer sender = new Buyer("u1", "pw1");
        Seller receiver = new Seller("u2", "pw2");

        UserContainer uc = new UserContainer();

        uc.put("u1", sender);
        uc.put("u2", receiver);

        MessageContainer mc = new MessageContainer();


        SendMessages sm = new SendMessages(mc, uc);

        sm.sendMessage("u1", "u2", "testContent");

        System.out.println(sm.getMessageInbox("u2"));

    }

    @Test
    public void testSendingMultipleMessages() {

        Buyer sender = new Buyer("u1", "pw1");
        Seller receiver = new Seller("u2", "pw2");

        UserContainer uc = new UserContainer();

        uc.put("u1", sender);
        uc.put("u2", receiver);

        MessageContainer mc = new MessageContainer();


        SendMessages sm = new SendMessages(mc, uc);

        sm.sendMessage("u1", "u2", "testContent");
        sm.sendMessage("u1", "u2", "testContent2");
        sm.sendMessage("u1", "u2", "testContent3");

        System.out.println(sm.getMessageInbox("u2"));


    }

    @Test
    public void testReceivingMultipleMessages() {

        Buyer sender = new Buyer("u1", "pw1");
        Seller receiver = new Seller("u2", "pw2");

        UserContainer uc = new UserContainer();

        uc.put("u1", sender);
        uc.put("u2", receiver);

        MessageContainer mc = new MessageContainer();


        SendMessages sm = new SendMessages(mc, uc);

        sm.sendMessage("u1", "u2", "testContent");
        sm.sendMessage("u1", "u2", "testContent2");
        sm.sendMessage("u1", "u2", "testContent3");

        System.out.println(sm.getMessageOutbox("u1"));


    }

}
