import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;


public class CountryTest {
    private Country country;

    @Before
    public void makeEmptyCountry() {
        country = new Country(new HashMap<>());
        country.addCity("A");
        country.addCity("B");
        country.addCity("C");
    }

    @Test
    public void addCityNoRoadsTest() {
        country.addCity("A");
        Assert.assertEquals(new ArrayList<City>(), country.getNeighbourCities("A"));
    }

    @Test
    public void addRoadTest() {
        country.addRoad("A", "B", 10);
        ArrayList<City> expectedCity = new ArrayList<>();
        expectedCity.add(new City("B"));
        Assert.assertEquals(expectedCity, country.getNeighbourCities("A"));
    }
    @Test
    public void addManyRoads() {
        country.addRoad("A", "B", 10);
        country.addRoad("A", "C", 20);
        country.addRoad("B", "C", 11);
        ArrayList<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City("B"));
        expectedCities.add(new City("C"));
        Assert.assertEquals(expectedCities, country.getNeighbourCities("A"));

    }

    @Test (expected = IllegalArgumentException.class)
    public void addTwoRoadsForTheSameCities() {
        country.addRoad("A", "B", 10);
        country.addRoad("A", "B", 11);
    }

    @Ignore
    @Test
    public void getFireBrigadeCitiesTest() {
        country.addCity("D");
        country.addCity("E");
        country.addRoad("A", "B", 2);
        country.addRoad("A", "C",3);
        country.addRoad("A", "D", 4);
        country.addRoad("A", "E", 1);
        int maxDrivingTime = 10;
        int timeout = 5;

        ArrayList<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City("A"));
        expectedCities.add(new City("D"));

       // Assert.assertEquals(expectedCities, country.getFireBrigadeCities(maxDrivingTime, timeout));
    }
}
