import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.*;


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

    @Test(expected = IllegalArgumentException.class)
    public void addTwoRoadsForTheSameCities() {
        country.addRoad("A", "B", 10);
        country.addRoad("A", "B", 11);
    }


    @Test
    public void getFireBrigadeCitiesStarGraphTest() {
        country.addCity("D");
        country.addCity("E");
        country.addRoad("A", "B", 2);
        country.addRoad("A", "C", 3);
        country.addRoad("A", "D", 4);
        country.addRoad("A", "E", 1);
        int maxDrivingTime = 10;
        int timeout = 5;

//        ArrayList<City> expectedCities = new ArrayList<>();
//        expectedCities.add(new City("A"));

        HashSet<City> actual = country.getFireBrigadeCities(maxDrivingTime, timeout);
//        Assert.assertEquals(expectedCities, actual);
        Assert.assertTrue(
                Collections.singleton(new City("A")).equals(actual) ||
                        Collections.singleton(new City("B")).equals(actual) ||
                        Collections.singleton(new City("C")).equals(actual) ||
                        Collections.singleton(new City("D")).equals(actual) ||
                        Collections.singleton(new City("E")).equals(actual)
        );
    }

    @Test
    public void getFireBrigadeCitiesLongGraphTest() {
        country.addCity("D");
        country.addRoad("A", "B", 11);
        country.addRoad("A", "D", 8);
        country.addRoad("D", "C", 1);
        int maxDrivingTime = 8;
        int timeout = 5;

        HashSet<City> actual = country.getFireBrigadeCities(maxDrivingTime, timeout);
        HashSet<City> expected = new HashSet<>();
        expected.add(new City("B"));
        expected.add(new City("D"));

        Assert.assertEquals(expected, actual);

    }

    @Test
    public void getFireBrigadeCitiesStraightGraphTest() {
        country.addCity("D");
        country.addCity("E");
        country.addCity("F");
        country.addRoad("A", "B", 1);
        country.addRoad("B", "C", 1);
        country.addRoad("C", "D", 1);
        country.addRoad("D", "E", 1);
        country.addRoad("E", "F", 1);
        int maxDrivingTime = 5;
        int timeout = 5;

        HashSet<City> actual = country.getFireBrigadeCities(maxDrivingTime, timeout);

        Assert.assertTrue(
                Collections.singleton(new City("A")).equals(actual) ||
                        Collections.singleton(new City("B")).equals(actual) ||
                        Collections.singleton(new City("C")).equals(actual) ||
                        Collections.singleton(new City("D")).equals(actual) ||
                        Collections.singleton(new City("E")).equals(actual) ||
                        Collections.singleton(new City("F")).equals(actual)
        );
    }
}
