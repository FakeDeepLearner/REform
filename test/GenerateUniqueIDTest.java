import RealEstate.entities.ListingContainer;
import RealEstate.useCases.GenerateUniqueID;
import org.junit.*;

import java.util.ArrayList;

import static org.junit.Assert.*;


public class GenerateUniqueIDTest {

    @Test
    public void testGenerateUniqueID(){
        ListingContainer l = new ListingContainer();
        l.put(2, "key");
        GenerateUniqueID a = new GenerateUniqueID(l);

        a.setMinID(1);
        a.setMaxID(4);

        ArrayList<Integer> allowedInts = new ArrayList<>();
        allowedInts.add(1);
        allowedInts.add(3);
        allowedInts.add(4);
        int id = a.getUniqueID();

        System.out.println(id);

        assertTrue(allowedInts.contains(id));

    }
}
