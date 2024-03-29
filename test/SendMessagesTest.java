import entities.Message;
import entities.users.User;
import entities.containers.UserContainer;
import entities.users.Buyer;
import entities.containers.MessageContainer;
import entities.users.Seller;
import gateways.MessagesCSVController;
import useCases.messageUseCases.SendMessages;
import org.junit.Test;
import useCases.messageUseCases.ViewMessages;

import java.util.concurrent.TimeUnit;

public class SendMessagesTest {
    @Test
    public void testSendingMessage() {
        Buyer sender = new Buyer("u1", "pw1");
        Seller receiver = new Seller("u2", "pw2");

        UserContainer<String, User> uc = new UserContainer<>();
        MessageContainer<Integer, Message> mc = new MessageContainer<>();

        uc.put("u1", sender);
        uc.put("u2", receiver);

        SendMessages sm = new SendMessages(mc, uc, new MessagesCSVController(mc));
        ViewMessages viewMessages = new ViewMessages(uc);

        sm.sendMessage("u1", "u2", "testContent");

        System.out.println(viewMessages.getMessageInbox("u2"));
    }

    @Test
    public void testSendingMultipleMessages() throws InterruptedException {
        Buyer sender = new Buyer("u1", "pw1");
        Seller receiver = new Seller("u2", "pw2");

        UserContainer<String, User> uc = new UserContainer<>();
        MessageContainer<Integer, Message> mc = new MessageContainer<>();

        uc.put("u1", sender);
        uc.put("u2", receiver);

        SendMessages sm = new SendMessages(mc, uc, new MessagesCSVController(mc));
        ViewMessages viewMessages = new ViewMessages(uc);

        sm.sendMessage("u1", "u2", "testContent");
        TimeUnit.SECONDS.sleep(1);
        sm.sendMessage("u1", "u2", "testContent2");
        TimeUnit.SECONDS.sleep(1);
        sm.sendMessage("u1", "u2", "testContent3");

        System.out.println(viewMessages.getMessageInbox("u2"));
    }

    @Test
    public void testReceivingMultipleMessages() throws InterruptedException {
        Buyer sender = new Buyer("u1", "pw1");
        Seller receiver = new Seller("u2", "pw2");

        UserContainer<String, User> uc = new UserContainer<>();
        MessageContainer<Integer, Message> mc = new MessageContainer<>();

        uc.put("u1", sender);
        uc.put("u2", receiver);

        SendMessages sm = new SendMessages(mc, uc, new MessagesCSVController(mc));
        ViewMessages viewMessages = new ViewMessages(uc);

        sm.sendMessage("u1", "u2", "testContent");
        TimeUnit.SECONDS.sleep(1);
        sm.sendMessage("u1", "u2", "testContent2");
        TimeUnit.SECONDS.sleep(1);
        sm.sendMessage("u1", "u2", "testContent3");

        System.out.println(viewMessages.getMessageOutbox("u1"));
    }

}
