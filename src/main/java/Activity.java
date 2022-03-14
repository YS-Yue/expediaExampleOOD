public class Activity implements Item{
    Location location;
    Float rating;
    double price;
    double avgStayTimeInMinute;

    public Activity(Location location, Float rating, double price, double avgStayTimeInMinute) {
        this.location = location;
        this.rating = rating;
        this.price = price;
        this.avgStayTimeInMinute = avgStayTimeInMinute;
    }

    public double getPrice() {
        return price;
    }

    public double getAvgStayTimeInMinute() {
        return avgStayTimeInMinute;
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
