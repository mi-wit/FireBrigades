import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountryTest {
    private Country country;

    @Before
    public void makeEmptyCountry() {
        country = new Country(new HashMap<>());
        country.addCity("Katowice");
        country.addCity("Sosnowiec");
        country.addCity("Warszawa");
    }

    @Test
    public void addCityNoRoadsTest() {
        country.addCity("Katowice");
        Assert.assertEquals(new ArrayList<City>(), country.getNeighbourCities("Katowice"));
    }

    @Test
    public void addRoadTest() {
        country.addRoad("Katowice", "Sosnowiec", 10);
        ArrayList<City> expectedCity = new ArrayList();
        expectedCity.add(new City("Sosnowiec"));
        Assert.assertEquals(expectedCity, country.getNeighbourCities("Katowice"));
    }
    @Test
    public void addManyRoads() {
        country.addRoad("Katowice", "Sosnowiec", 10);
        country.addRoad("Katowice", "Warszawa", 20);
        country.addRoad("Sosnowiec", "Warszawa", 11);
        ArrayList<City> expectedCities = new ArrayList<>();
        expectedCities.add(new City("Sosnowiec"));
        expectedCities.add(new City("Warszawa"));
        Assert.assertEquals(expectedCities, country.getNeighbourCities("Katowice"));

    }
    @Test (expected = IllegalArgumentException.class)
    public void addTwoRoadsForTheSameCities() {
        country.addRoad("Katowice", "Sosnowiec", 10);
        country.addRoad("Katowice", "Sosnowiec", 11);
    }
}
