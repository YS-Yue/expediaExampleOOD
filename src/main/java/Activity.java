public class Activity extends Item{
    double price;

    public Activity(Location location, Float rating, double price) {
        super(location, rating);
        this.price = price;
    }

    public double getPrice() {
        return price;
    }
}
