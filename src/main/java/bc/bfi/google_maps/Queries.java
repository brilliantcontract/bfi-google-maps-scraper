package bc.bfi.google_maps;

import java.util.ArrayList;
import java.util.List;

public class Queries {

    private final List<String> locations = new ArrayList<>();

    public void loadLocations(String locations) {
        this.locations.clear();

        for (String location : locations.trim().split("\n")) {
            this.locations.add(location.trim());
        }
    }

    public List<String> getLocations() {
        return locations;
    }

    /**
     * Convert location to search query for Google.
     *
     * @param location
     * @return
     */
    private String prepareQuery(String location) {
        String query = "";

        query = "churches " + location;

        return query;
    }

    public Iterable<String> generateQueries() {
        final List<String> queries = new ArrayList<>();

        for (String location : this.locations) {
            queries.add(prepareQuery(location));
        }

        return queries;
    }

}
