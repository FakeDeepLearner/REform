import entities.containers.UserContainer;
import entities.Buyer;
import entities.containers.MessageContainer;
import entities.Seller;
import useCases.messageUseCases.MessageChat;
import useCases.messageUseCases.SendMessages;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

public class MessageChatTest {


    @Test
    public void testPrintChatHistory() throws InterruptedException {

        Buyer sender = new Buyer("u1", "pw1");
        Seller receiver = new Seller("u2", "pw2");
        Buyer irrelevantUser = new Buyer("u3", "pw3");

        UserContainer uc = new UserContainer();

        uc.put("u1", sender);
        uc.put("u2", receiver);
        uc.put("u3", irrelevantUser);

        MessageContainer mc = new MessageContainer();

        SendMessages sm = new SendMessages(mc, uc);

        sm.sendMessage("u1", "u2", "testContent1");
        sm.sendMessage("u2", "u1", "testContent2");
        sm.sendMessage("u1", "u2", "testContent3");
        sm.sendMessage("u3", "u2", "XXXXX");
        sm.sendMessage("u2", "u3", "XXXXX");
        sm.sendMessage("u2", "u1", "testContent4");

        MessageChat messageChat = new MessageChat(mc);

        messageChat.printChatHistory(sender, receiver);


    }


}
