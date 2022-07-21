import LoginSystem.entities.UserContainer;
import RealEstate.entities.Buyer;
import RealEstate.entities.Message;
import RealEstate.entities.MessageContainer;
import RealEstate.entities.Seller;
import RealEstate.useCases.SendMessages;
import org.junit.Test;
import static org.junit.Assert.*;

public class SendMessagesTest {

    @Test
    public void testSendingMessage(){

        Buyer sender = new Buyer("u1", "pw1");
        Seller receiver = new Seller("u2", "pw2");

        UserContainer uc = new UserContainer();

        uc.put("u1", sender);
        uc.put("u2", receiver);

        MessageContainer mc = new MessageContainer();


        SendMessages sm = new SendMessages(mc,uc);

        sm.sendMessage("u1", "u2", "testContent");

        System.out.println(sm.getMessageInbox("u2"));

    }

    @Test
    public void testSendingMultipleMessages(){

        Buyer sender = new Buyer("u1", "pw1");
        Seller receiver = new Seller("u2", "pw2");

        UserContainer uc = new UserContainer();

        uc.put("u1", sender);
        uc.put("u2", receiver);

        MessageContainer mc = new MessageContainer();


        SendMessages sm = new SendMessages(mc,uc);

        sm.sendMessage("u1", "u2", "testContent");
        sm.sendMessage("u1", "u2", "testContent2");
        sm.sendMessage("u1", "u2", "testContent3");

        System.out.println(sm.getMessageInbox("u2"));

        // TODO: Messages are NOT showing in the chronological order
        // TODO: Is is printing the message in the desired format? (commas seem to be off)

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

        // TODO: Messages are NOT showing in the chronological order

        // TODO: Should we add the sent / received time of the message as well?

    }

}
