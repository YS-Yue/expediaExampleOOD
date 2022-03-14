public class Airport implements Item{
    Location location;
    Float rating;

    public Airport(Location location, Float rating) {
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
