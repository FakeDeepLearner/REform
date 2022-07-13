package RealEstate.gateways;
import java.io.IOException;

public interface CsvInterface {

    void read() throws IOException;
    void write() throws IOException;


}
