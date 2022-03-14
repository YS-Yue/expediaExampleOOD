import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FilterTest {
    List<Item> allHotels = new ArrayList<>();;
    List<Item> allActivityItem = new ArrayList<>();
    List<Item> allAirport = new ArrayList<>();
    List<Activity> allActivities = new ArrayList<>();
    Location location1;
    Location location2;
    Location location3;
    Location location0;

    Hotel hotel1;
    Item activity1;
    Item activity2;
    Item activity3;

    @BeforeEach
    void setUp() {
        location0 = new Location(-122.1830, 47.6670);
        location1 = new Location(-122.1500, 47.6800);
        location2 = new Location(-123.1830, 48.6670);
        location3 = new Location(-122.1831, 60.6671);

        hotel1 = new Hotel(location0, 4.5F);
        activity1 = new Activity(location1, 4.9F, 20D, 75D);
        activity2 = new Activity(location2, 4.7F, 30D, 75D);
        activity3 = new Activity(location3, 3.0F, 40D, 75D);

        allActivities.add((Activity) activity1);
        allActivities.add((Activity)activity2);
        allActivities.add((Activity)activity3);

        allActivityItem.add(activity1);
        allActivityItem.add(activity2);
        allActivityItem.add(activity3);

    }

    @Test
    void ratingFilter() {
        allHotels.add(hotel1);
        allHotels.add(new Hotel(location1, 5.0F));
        allHotels.add(new Hotel(location2, 3.0F));
        allHotels.add(new Hotel(location3, 3.5F));

        Assertions.assertEquals(2, Filter.ratingFilter(allHotels, 3.2F, 4.9F).size());

    }

    @Test
    void locationFilter() {
        System.out.println("Distance between hotel1 and activity1: " + Filter.calculateDistance(hotel1,activity1));
        System.out.println("Distance between hotel1 and activity2: " + Filter.calculateDistance(hotel1,activity2));
        System.out.println("Distance between hotel1 and activity3: " + Filter.calculateDistance(hotel1,activity3));
        System.out.println(Filter.locationFilter(allActivityItem,hotel1,300));

        Assertions.assertEquals(2,Filter.locationFilter(allActivityItem,hotel1,300).size());
    }

    @Test
    void priceFilter() {
        List<Activity> activityList = new ArrayList<>();
        for (Item item : allActivityItem) {
            activityList.add((Activity) item);
        }
        Assertions.assertEquals(1, Filter.priceFilter(activityList,25,35).size());
    }
}