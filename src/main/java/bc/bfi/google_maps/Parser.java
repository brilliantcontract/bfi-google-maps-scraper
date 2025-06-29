package bc.bfi.google_maps;

import java.util.ArrayList;
import java.util.List;
import javax.json.*;
import java.io.StringReader;

class Parser {

    List<Place> parse(String json, String query) {
        List<Place> places = new ArrayList<>();

        // Parse JSON
        JsonObject jsonObject;
        try (JsonReader jsonReader = Json.createReader(new StringReader(json))) {
            jsonObject = jsonReader.readObject();
        }

        // Convert to a list of JSON objects
        JsonArray placesArray = jsonObject.getJsonArray("places");
        for (JsonValue value : placesArray) {
            Place place = new Place();
            JsonObject jsonPlace = value.asJsonObject();

            place.setName(readString("title", jsonPlace));
            place.setFullAddress(readString("address", jsonPlace));
            place.setLatitude(readNumber("latitude", jsonPlace));
            place.setPosition(readNumber("position", jsonPlace));
            place.setLongitude(readNumber("longitude", jsonPlace));
            place.setRate(readNumber("rating", jsonPlace));
            place.setRateCounter(readNumber("ratingCount", jsonPlace));
            place.setType(readString("type", jsonPlace));
            place.setWebsite(readString("website", jsonPlace));
            place.setCid(readString("cid", jsonPlace));
            place.setGooglePlaceId(readString("placeId", jsonPlace));
            place.setPhone(readString("phoneNumber", jsonPlace));
            place.setQuery(query);

            places.add(place);
        }

        return places;
    }

    private String readString(String key, JsonObject jsonPlace) {
        String value = "";

        try {
            value = jsonPlace.getString(key);
        } catch (Exception ignore) {
        }

        return value;
    }

    private String readNumber(String key, JsonObject jsonPlace) {
        String value = "";

        try {
            value = jsonPlace
                    .getJsonNumber(key)
                    .bigDecimalValue()
                    .toPlainString();
        } catch (Exception ignore) {
        }

        return value;
    }
}
