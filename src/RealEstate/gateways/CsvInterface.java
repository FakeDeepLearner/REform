package RealEstate.gateways;

import java.io.BufferedReader;
import java.io.BufferedWriter;

public interface CsvInterface {
    void read(BufferedReader br);

    void write(BufferedWriter bw);


}
