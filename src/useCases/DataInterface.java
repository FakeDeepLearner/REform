package useCases;
import java.io.IOException;

public interface DataInterface {

    /**
     * Reads the associated csv file, and populates the necessary containers.
     * This function is called when the program starts, before any other action is taken.
     */

    void read() throws IOException;
    /**
     * Reads through the associated entities that have been created in this session,
     * and writes them to the associated csv file.
     * This function is called when the program ends, as the very last step.
     * */
    void write() throws IOException;


}
