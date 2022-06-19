import useCases.CsvEditor;
import org.junit.*;
import static org.junit.Assert.*;

public class CsvEditorTest {
    // test methods
    @Test(timeout = 50)
    public void testFormatFilename() {
        assertEquals(CsvEditor.formatFilename("123"), "123.csv");
        assertEquals(CsvEditor.formatFilename("12345"), "12345.csv");
        assertEquals(CsvEditor.formatFilename("csv"), "csv.csv");
        assertEquals(CsvEditor.formatFilename("abcsv"), "abcsv.csv");
        assertEquals(CsvEditor.formatFilename("abc.csv"), "abc.csv");
    }
}
