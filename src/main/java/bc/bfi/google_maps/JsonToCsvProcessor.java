package bc.bfi.google_maps;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

/**
 * Utility class that processes JSON files from a directory and generates
 * {@code google-maps.csv} using the {@link CsvStorage} class.
 */
public class JsonToCsvProcessor {

    private static final Logger LOGGER = Logger.getLogger(JsonToCsvProcessor.class.getName());
    private static final Path DEFAULT_JSON_DIR = Paths.get("json");
    private static final Path DEFAULT_CSV_FILE = Paths.get("google-maps.csv");

    private final Path jsonDirectory;
    private final Path csvFile;
    private final Parser parser = new Parser();
    private final CsvStorage csvStorage = new CsvStorage();

    public JsonToCsvProcessor() {
        this(DEFAULT_JSON_DIR, DEFAULT_CSV_FILE);
    }

    /**
     * Constructor used mainly for tests to override directories.
     */
    public JsonToCsvProcessor(Path jsonDirectory, Path csvFile) {
        this.jsonDirectory = jsonDirectory;
        this.csvFile = csvFile;
    }

    /**
     * Process all JSON files from the directory and regenerate CSV file.
     */
    public void process() {
        try {
            Files.deleteIfExists(csvFile);
        } catch (IOException ex) {
            LOGGER.log(Level.WARNING, "Cannot delete existing CSV file", ex);
        }

        csvStorage.createCsvFile();

        if (!Files.isDirectory(jsonDirectory)) {
            LOGGER.log(Level.WARNING, "JSON directory {0} does not exist", jsonDirectory);
            return;
        }

        try {
            Files.list(jsonDirectory)
                    .filter(path -> path.toString().endsWith(".json"))
                    .forEach(this::processFile);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failed to list JSON directory", ex);
        }
    }

    private void processFile(Path file) {
        try {
            String json = new String(Files.readAllBytes(file), StandardCharsets.UTF_8);
            String query = readQuery(json);
            List<Place> places = parser.parse(json, query);
            csvStorage.append(places);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Failed to read json file " + file, ex);
        }
    }

    private String readQuery(String json) {
        try (JsonReader reader = Json.createReader(new StringReader(json))) {
            JsonObject obj = reader.readObject();
            JsonObject params = obj.getJsonObject("searchParameters");
            if (params != null) {
                return params.getString("q", "");
            }
        } catch (Exception ignore) {
        }
        return "";
    }
}
