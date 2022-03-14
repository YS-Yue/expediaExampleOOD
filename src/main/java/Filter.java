import java.util.List;
import java.util.stream.Collectors;

public class Filter {
    public static List<Item> ratingFilter(List<Item> inputItems, float minRating, float maxRating) {
        return inputItems
                .stream()
                .filter(item -> item.getRating() >= minRating && maxRating >= item.getRating())
                .collect(Collectors.toList());
    }

    public static List<Item> locationFilter(List<Item> inputItems, Item dest,float maxDistance) {
        return inputItems
                .stream()
                .filter(item -> Filter.calculateDistance(item, dest) <= maxDistance)
                .collect(Collectors.toList());
    }

    public static List<Activity> priceFilter(List<Activity> inputActivities, double minPrice, double maxPrice) {
        return inputActivities
                .stream()
                .filter(activity ->activity.getPrice() >= minPrice && activity.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    public static double calculateDistance(Item src, Item dest) {
        double lat1 = src.getLocation().getLatitude();
        double lat2 = dest.getLocation().getLatitude();
        double lon1 = src.getLocation().getLongitude();
        double lon2 = dest.getLocation().getLongitude();

        // The math module contains a function
        // named toRadians which converts from
        // degrees to radians.
        lon1 = Math.toRadians(lon1);
        lon2 = Math.toRadians(lon2);
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);

        // Haversine formula
        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;
        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers. Use 3956
        // for miles
        double r = 6371;

        // calculate the result
        return (c * r);
    }
}
