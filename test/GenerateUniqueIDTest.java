import entities.Listing;
import entities.containers.ListingContainer;
import useCases.miscellaneous.GenerateUniqueID;
import org.junit.*;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.Assert.*;


public class GenerateUniqueIDTest {
    @Test
    public void testGenerateUniqueID() {
        ListingContainer<Integer, Listing> l = new ListingContainer<>();
        l.put(2, new Listing(2, 16, 225, "St. George St.", "Toronto", "HOUSE", 1, 1, new BigDecimal("100.00"), "test"));
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
