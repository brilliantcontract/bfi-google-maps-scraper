package bc.bfi.google_maps;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

public class CsvStorage {

    private static final String STORAGE_FILE = "google-maps.csv";
    private static final Logger LOGGER = Logger.getLogger(CsvStorage.class.getName());

    private static final String[] HEADERS = {
        "POSITION",
        "CID",
        "GOOGLE_PLACE_ID",
        "NAME",
        "FULL_ADDRESS",
        "LATITUDE",
        "LONGITUDE",
        "PERMANENTLY_CLOSED",
        "PHONE",
        "WEBSITE",
        "QUERY",
        "RATE",
        "RATE_COUNTER",
        "TYPE"
    };

    public CsvStorage() {
        if (!Files.exists(Paths.get("STORAGE_FILE"))) {
            try {
                createCsvFile();
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Cannot write to .csv file. " + ex.getMessage(), ex);
                System.exit(1);
            }
        }
    }

    public final void createCsvFile() {
        Path path = Paths.get(STORAGE_FILE);
        if (Files.exists(path)) {
            return;
        }

        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            CSVFormat format = CSVFormat.DEFAULT.withIgnoreHeaderCase();
            try (CSVPrinter printer = new CSVPrinter(writer, format)) {
                printer.printRecord((Object[]) HEADERS);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Cannot create .csv file", ex);
            System.exit(1);
        }
    }

    public void append(Place place) {
        List<String> record = new ArrayList<>();
        record.add(place.getPosition());
        record.add(place.getCid());
        record.add(place.getGooglePlaceId());
        record.add(place.getName());
        record.add(place.getFullAddress());
        record.add(place.getLatitude());
        record.add(place.getLongitude());
        record.add(place.getPermanentlyClosed());
        record.add(place.getPhone());
        record.add(place.getWebsite());
        record.add(place.getQuery());
        record.add(place.getRate());
        record.add(place.getRateCounter());
        record.add(place.getType());

        Path path = Paths.get(STORAGE_FILE);
        try (Writer writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, StandardOpenOption.APPEND)) {
            CSVFormat format = CSVFormat.DEFAULT;
            try (CSVPrinter printer = new CSVPrinter(writer, format)) {
                printer.printRecord(record);
            }
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, "Cannot write to .csv file. " + ex.getMessage(), ex);
            System.exit(1);
        }
    }

    public void append(final List<Place> places) {
        for (Place place : places) {
            append(place);
        }
    }
}
