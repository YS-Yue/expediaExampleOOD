import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class FilterTest {

    List<Item> allActivityItemList = new ArrayList<>();
    List<Activity> allActivities = new ArrayList<>();

    List<Item> allAirportItemList = new ArrayList<>();
    List<Airport> allAirports = new ArrayList<>();

    List<Item> allHotelsItemList = new ArrayList<>();
    List<Hotel> allHotels = new ArrayList<>();

    @BeforeEach
    void setUp() {
        // Define some Location objects
        Location location0 = new Location(-122.1830, 47.6670);
        Location location1 = new Location(-122.1500, 47.6800);
        Location location2 = new Location(-123.1830, 48.6670);
        Location location3 = new Location(-122.1831, 60.6671);

        Hotel hotel1 = new Hotel(location0, 4.5F);
        Hotel hotel2 = new Hotel(location1, 5.0F);
        Hotel hotel3 = new Hotel(location2, 3.0F);
        Hotel hotel4 = new Hotel(location3, 3.5F);

        Activity activity1 = new Activity(location1, 4.9F, 20D);
        Activity activity2 = new Activity(location2, 4.7F, 30D);
        Activity activity3 = new Activity(location3, 3.0F, 40D);

        Airport airport1 = new Airport(location1, 3.1F);
        Airport airport2 = new Airport(location2, 3.5F);
        Airport airport3 = new Airport(location3, 3.9F);

        // Populating List<Item> of hotels
        allHotelsItemList.addAll(Arrays.asList(hotel1,hotel2,hotel3,hotel4));
        // Populating List<Hotel>
        allHotels.addAll(Arrays.asList(hotel1,hotel2,hotel3,hotel4));

        // Populating List<Item> of activities
        allActivityItemList.addAll(Arrays.asList(activity1,activity2,activity3));
        // Populating List<Activity> of activities
        allActivities.addAll(Arrays.asList(activity1,activity2,activity3));

        // Populating List<Item> of airports
        allAirportItemList.addAll(Arrays.asList(airport1, airport2,airport3));
        // Populating List<Airport> of airports
        allAirports.addAll(Arrays.asList(airport1, airport2,airport3));

    }

    @Test
    void ratingFilter() {

        // Hotels Rating Filter
        Assertions.assertEquals(2, Filter.ratingFilter(allHotelsItemList, 3.2F, 4.9F).size());
        // allHotels need to be converted to a List<Item> first
        Assertions.assertEquals(2, Filter.ratingFilter(new ArrayList<>(allHotels), 3.2F, 4.9F).size());

        // Activities Rating Filter
        Assertions.assertEquals(2, Filter.ratingFilter(allActivityItemList, 2.2F, 4.8F).size());
        Assertions.assertEquals(2, Filter.ratingFilter(new ArrayList<>(allActivities), 2.2F, 4.8F).size());

        // Airports Rating Filter
        Assertions.assertEquals(0, Filter.ratingFilter(allAirportItemList, 4F, 5F).size());
        Assertions.assertEquals(0, Filter.ratingFilter(new ArrayList<>(allAirports), 4F, 5F).size());

    }

    @Test
    void locationFilter() {
        Assertions.assertEquals(2,Filter.locationFilter(allActivityItemList, new Hotel(new Location(-122.1830, 47.6670), 4.5F),300).size());
    }

    @Test
    void priceFilter() {

        // The input and output of Filter.priceFilter method are both List<Activity> instead of List<Item>
        List<Activity> filteredByPriceActivities = Filter.priceFilter(allActivities,18,35);
        Assertions.assertEquals(2, filteredByPriceActivities.size());

        // For the case that multiple filters be selected, we need to give the correct type of input List
        List<Item> filteredByRatingActivities = Filter.ratingFilter(new ArrayList<>(filteredByPriceActivities), 4.8F, 4.9F);
        Assertions.assertEquals(1, filteredByRatingActivities.size());
    }
}