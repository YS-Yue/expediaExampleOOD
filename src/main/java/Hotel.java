public class Hotel implements Item{
    Location location;
    Float rating;

    public Hotel(Location location, Float rating) {
        this.location = location;
        this.rating = rating;
    }

    @Override
    public Location getLocation() {
        return location;
    }

    @Override
    public Float getRating() {
        return rating;
    }
}
