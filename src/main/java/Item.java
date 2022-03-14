abstract class Item {
    Location location;
    float rating;

    public Item(Location location, Float rating) {
        this.location = location;
        this.rating = rating;
    }

    public Location getLocation() {
        return location;
    }

    public Float getRating() {
        return rating;
    }
}
