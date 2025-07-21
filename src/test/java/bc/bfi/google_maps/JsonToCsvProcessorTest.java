package bc.bfi.google_maps;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class JsonToCsvProcessorTest {

    private Path jsonDir;
    private Path csvFile;

    @Before
    public void setUp() throws IOException {
        jsonDir = Paths.get("json");
        Files.createDirectories(jsonDir);
        csvFile = Paths.get("google-maps.csv");

        String sampleJson = "{\n" +
                "  \"searchParameters\": { \"q\": \"sample query\" },\n" +
                "  \"places\": [ { \"title\": \"Test\", \"address\": \"Addr\", \"position\": 1 } ]\n" +
                "}";
        Files.write(jsonDir.resolve("sample.json"), sampleJson.getBytes(StandardCharsets.UTF_8));
    }

    @After
    public void tearDown() throws IOException {
        if (Files.exists(csvFile)) {
            Files.delete(csvFile);
        }
        if (Files.exists(jsonDir)) {
            Files.walk(jsonDir).sorted((a,b) -> b.compareTo(a)).forEach(p -> {
                try { Files.deleteIfExists(p); } catch (IOException ignored) {}
            });
        }
    }

    @Test
    public void processCreatesCsvFromJson() throws IOException {
        JsonToCsvProcessor processor = new JsonToCsvProcessor();
        processor.process();

        assertThat(Files.exists(csvFile), is(true));

        List<String> lines = Files.readAllLines(csvFile, StandardCharsets.UTF_8);
        assertThat(lines.size(), is(2));
        assertThat(lines.get(1), containsString("Test"));
    }
}
