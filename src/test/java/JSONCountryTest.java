import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class JSONCountryTest {
    final static String testIN = ".\\src\\test\\java\\testIN.json";
    final static String testOUT = ".\\src\\test\\java\\testOUT.json";
    private JSONCountry jsonCountry;

    @Before
    public void before() {
        jsonCountry = new JSONCountry(testIN, testOUT);
    }

    @Test
    public void countryReadTest() {
        Country country = new Country(new HashMap<>());
        country.addCityIfNotExist("A");
        country.addCityIfNotExist("B");
        country.addCityIfNotExist("C");
        country.addCityIfNotExist("D");
        country.addRoad("A", "B", 11);
        country.addRoad("A", "D", 8);
        country.addRoad("D", "C", 1);
        country.setMaxDrivingTime(8);

        Assert.assertEquals(country, jsonCountry.getCountry());
    }

    @Test
    public void timeoutReadTest() {
        Assert.assertEquals(5, jsonCountry.getTimeoutInSeconds());
    }
    @Test
    public void saveJSONTest() {
        Set<String> cities = new HashSet<>();
        cities.add("A");
        cities.add("B");
        cities.add("C");
        cities.add("D");
        cities.add("E");
        cities.add("F");
        jsonCountry.saveJSON(cities);


        String actual = "";
        File file = new File(testOUT);
        try {
            Scanner scanner = new Scanner(file);
            actual = scanner.nextLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("[\"A\",\"B\",\"C\",\"D\",\"E\",\"F\"]", actual);
    }
}
