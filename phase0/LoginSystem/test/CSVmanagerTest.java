import databaseManagers.CSVmanager;
import org.junit.*;
import static org.junit.Assert.*;

public class CSVmanagerTest {


    // test methods
    @Test(timeout = 50)
    public void testFormatFilename() {
        assertEquals(CSVmanager.formatFilename("123"), "123.csv");
        assertEquals(CSVmanager.formatFilename("12345"), "12345.csv");
        assertEquals(CSVmanager.formatFilename("csv"), "csv.csv");
        assertEquals(CSVmanager.formatFilename("abcsv"), "abcsv.csv");
        assertEquals(CSVmanager.formatFilename("abc.csv"), "abc.csv");

    }


}
