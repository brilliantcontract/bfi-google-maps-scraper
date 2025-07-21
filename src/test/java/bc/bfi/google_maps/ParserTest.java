package bc.bfi.google_maps;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

public class ParserTest {

    @Test
    public void test() throws IOException {
        Parser parser = new Parser();
        String jsonString = "{\n" +
                "  \"places\": [ { \"title\": \"Test\", \"address\": \"Addr\", \"position\": 1 } ]\n" +
                "}";
        List<Place> places = parser.parse(jsonString, "churches in Rhode Island");

        for (Place place : places) {
            place.print();
            System.out.println("\n---------------------------------\n");
        }
    }

}
