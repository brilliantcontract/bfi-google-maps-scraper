package bc.bfi.google_maps;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SerperScraper {

    private static final Logger LOGGER = Logger.getLogger(SerperScraper.class.getName());

    private final HttpDownloader downloader = new HttpDownloader();
    private final CsvStorage csvStorage = new CsvStorage();
    private final JsonStorage jsonStorage = new JsonStorage();
    private final Parser parser = new Parser();
    private final Queries queries;

    public SerperScraper(Queries queries) {
        this.queries = queries;
    }

    public void startScrape() {
        LOGGER.info("Scraping strat");
        csvStorage.createCsvFile();

        for (String location : this.queries.generateQueries()) {
            String[] chunks = location.split("\\|");
            String query = chunks[0];
            String coordinates = chunks[1];

            scrapeLocation(query, coordinates);
        }
    }

    private void scrapeLocation(String query, String coordinates) {
        LOGGER.log(Level.INFO, "Scrape: {0}", query);

        List<Place> places = new ArrayList<>();
        Integer pageNumber = 1;
        while (true) {
            //String jsonResponse = downloader.searchPlaces(query, pageNumber, "Norway", "no", "no");
            String jsonResponse = downloader.searchPlaces(query, pageNumber, "en", coordinates);
            jsonStorage.save(jsonResponse);
            places = parser.parse(jsonResponse, query);
            csvStorage.append(places);

            if (places.size() != 20) {
                break;
            }
            if(pageNumber >= 10) {
                break;
            }
            pageNumber += 1;
        }
    }

}
