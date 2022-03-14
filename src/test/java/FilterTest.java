import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class FilterTest {

    List<Item> allActivityItemList = new ArrayList<>();
    List<Item> allAirportItemList = new ArrayList<>();
    List<Item> allHotelsItemList = new ArrayList<>();

    Location location1;
    Location location2;
    Location location3;
    Location location0;
    Hotel hotel1;
    Activity activity1;
    Activity activity2;
    Activity activity3;

    @BeforeEach
    void setUp() {
        // Define some Location objects
        location0 = new Location(-122.1830, 47.6670);
        location1 = new Location(-122.1500, 47.6800);
        location2 = new Location(-123.1830, 48.6670);
        location3 = new Location(-122.1831, 60.6671);


        // Populating List<Item> of hotels
        hotel1 = new Hotel(location0, 4.5F);
        allHotelsItemList.add(hotel1);
        allHotelsItemList.add(new Hotel(location1, 5.0F));
        allHotelsItemList.add(new Hotel(location2, 3.0F));
        allHotelsItemList.add(new Hotel(location3, 3.5F));

        // Populating List<Item> of activities
        activity1 = new Activity(location1, 4.9F, 20D, 75D);
        activity2 = new Activity(location2, 4.7F, 30D, 75D);
        activity3 = new Activity(location3, 3.0F, 40D, 75D);
        allActivityItemList.add(activity1);
        allActivityItemList.add(activity2);
        allActivityItemList.add(activity3);

        // Populating List<Item> of airports
        allAirportItemList.add(new Airport(location1, 3.1F));
        allAirportItemList.add(new Airport(location2, 3.5F));
        allAirportItemList.add(new Airport(location3, 3.9F));

    }

    @Test
    void ratingFilter() {

        // Hotels Rating Filter
        Assertions.assertEquals(2, Filter.ratingFilter(allHotelsItemList, 3.2F, 4.9F).size());

        // Activities Rating Filter
        Assertions.assertEquals(2, Filter.ratingFilter(allActivityItemList, 2.2F, 4.8F).size());

        // Airports Rating Filter
        Assertions.assertEquals(0, Filter.ratingFilter(allAirportItemList, 4F, 5F).size());

    }

    @Test
    void locationFilter() {
        System.out.println("Distance between hotel1 and activity1: " + Filter.calculateDistance(hotel1,activity1));
        // Distance between hotel1 and activity1: 2.8626150035471425

        System.out.println("Distance between hotel1 and activity2: " + Filter.calculateDistance(hotel1,activity2));
        // Distance between hotel1 and activity2: 133.65510383822868

        System.out.println("Distance between hotel1 and activity3: " + Filter.calculateDistance(hotel1,activity3));
        // Distance between hotel1 and activity3: 1445.5451658861589

        Assertions.assertEquals(2,Filter.locationFilter(allActivityItemList,hotel1,300).size());
    }

    @Test
    void priceFilter() {
        List<Activity> activityList = new ArrayList<>();
        for (Item item : allActivityItemList) {
            activityList.add((Activity) item);
        }
        // The input and output of Filter.priceFilter method are both List<Activity> instead of List<Item>
        Assertions.assertEquals(1, Filter.priceFilter(activityList,25,35).size());
    }
}