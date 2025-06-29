package bc.bfi.google_maps;

public class Place {

    private String fullAddress = ""; // In JSON is Address
    private String latitude = "";
    private String longitude = "";
    private String name = ""; // In JSON is Title
    private String permanentlyClosed = "";
    private String phone = ""; // In JSON is PhoneNumber
    private String query = "";
    private String rate = ""; // In JSON is Rating
    private String rateCounter = ""; // In JSON is RatingCount
    private String type = "";
    private String cid = "";
    private String website = "";
    private String position = "";
    private String googlePlaceId = ""; // In JSON is PlaceId

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermanentlyClosed() {
        return permanentlyClosed;
    }

    public void setPermanentlyClosed(String permanentlyClosed) {
        this.permanentlyClosed = permanentlyClosed;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRateCounter() {
        return rateCounter;
    }

    public void setRateCounter(String rateCounter) {
        this.rateCounter = rateCounter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getGooglePlaceId() {
        return googlePlaceId;
    }

    public void setGooglePlaceId(String googlePlaceId) {
        this.googlePlaceId = googlePlaceId;
    }

    public void print() {
        System.out.println("Position: " + position);
        System.out.println("Full address: " + fullAddress);
        System.out.println("Latitude: " + latitude);
        System.out.println("Longitude: " + longitude);
        System.out.println("Name: " + name);
        System.out.println("Permanently closed: " + permanentlyClosed);
        System.out.println("Phone: " + phone);
        System.out.println("Query: " + query);
        System.out.println("Rate: " + rate);
        System.out.println("Rate counter: " + rateCounter);
        System.out.println("Type: " + type);
        System.out.println("CID: " + cid);
        System.out.println("Google Place ID: " + googlePlaceId);
        System.out.println("Website: " + website);
    }

}
