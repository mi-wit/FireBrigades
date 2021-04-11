//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Ignore;
//import org.junit.Test;
//
//import java.util.*;
//
//
//public class CountryTest {
//    private Country country;
//
//    @Before
//    public void makeEmptyCountry() {
//        country = new Country(new HashMap<>());
//        country.addCityIfNotExist("A");
//        country.addCityIfNotExist("B");
//        country.addCityIfNotExist("C");
//
//    }
//
//    @Test
//    public void addCityNoRoadsTest() {
//        country.addCityIfNotExist("A");
//        Assert.assertEquals(new ArrayList<City>(), country.getNeighbourCities("A"));
//    }
//
//    @Test
//    public void addRoadTest() {
//        country.addRoad("A", "B", 10);
//        ArrayList<City> expectedCity = new ArrayList<>();
//        expectedCity.add(new City("B"));
//        Assert.assertEquals(expectedCity, country.getNeighbourCities("A"));
//    }
//
//    @Test
//    public void addManyRoads() {
//        country.addRoad("A", "B", 10);
//        country.addRoad("A", "C", 20);
//        country.addRoad("B", "C", 11);
//        ArrayList<City> expectedCities = new ArrayList<>();
//        expectedCities.add(new City("B"));
//        expectedCities.add(new City("C"));
//        Assert.assertEquals(expectedCities, country.getNeighbourCities("A"));
//
//    }
//
//    @Test(expected = IllegalArgumentException.class)
//    public void addTwoRoadsForTheSameCities() {
//        country.addRoad("A", "B", 10);
//        country.addRoad("A", "B", 11);
//    }
//
//
//    @Test
//    public void getFireBrigadeCitiesStarGraphTest() {
//        country.addCityIfNotExist("D");
//        country.addCityIfNotExist("E");
//        country.addRoad("A", "B", 2);
//        country.addRoad("A", "C", 3);
//        country.addRoad("A", "D", 4);
//        country.addRoad("A", "E", 1);
//        country.setMaxDrivingTime(10);
//        country.setTimeout(5);
//
//        Set<City> actual = country.getFireBrigadeCities();
//        Assert.assertTrue(
//                Collections.singleton(new City("A")).equals(actual) ||
//                        Collections.singleton(new City("B")).equals(actual) ||
//                        Collections.singleton(new City("C")).equals(actual) ||
//                        Collections.singleton(new City("D")).equals(actual) ||
//                        Collections.singleton(new City("E")).equals(actual)
//        );
//    }
//
//    @Test
//    public void getFireBrigadeCitiesLongGraphTest() {
//        country.addCityIfNotExist("D");
//        country.addRoad("A", "B", 11);
//        country.addRoad("A", "D", 8);
//        country.addRoad("D", "C", 1);
//        country.setMaxDrivingTime(8);
//        country.setTimeout(5);
//
//        Set<City> actual = country.getFireBrigadeCities();
//        Set<City> expected = new HashSet<>();
//        expected.add(new City("B"));
//        expected.add(new City("D"));
//
//        Assert.assertEquals(expected, actual);
//
//    }
//
//    @Test
//    public void getFireBrigadeCitiesStraightGraphTest() {
//        country.addCityIfNotExist("D");
//        country.addCityIfNotExist("E");
//        country.addCityIfNotExist("F");
//        country.addRoad("A", "B", 1);
//        country.addRoad("B", "C", 1);
//        country.addRoad("C", "D", 1);
//        country.addRoad("D", "E", 1);
//        country.addRoad("E", "F", 1);
//        country.setMaxDrivingTime(5);
//        country.setTimeout(5);
//
//        Set<City> actual = country.getFireBrigadeCities();
//
//        Assert.assertTrue(
//                Collections.singleton(new City("A")).equals(actual) ||
//                        Collections.singleton(new City("B")).equals(actual) ||
//                        Collections.singleton(new City("C")).equals(actual) ||
//                        Collections.singleton(new City("D")).equals(actual) ||
//                        Collections.singleton(new City("E")).equals(actual) ||
//                        Collections.singleton(new City("F")).equals(actual)
//        );
//    }
//    @Ignore("Too big for now")
//    @Test
//    public void getFireBrigadeCitiesBigCountryTest() {
//        Random random = new Random();
//        long numberOfCities = 999999;
//        for (int i = 0; i < numberOfCities; i++)
//            country.addCityIfNotExist(String.valueOf(i));
//
//        ArrayList<City> cities;
//        for (int i = 0; i < numberOfCities / 100; i++) {
//            cities = new ArrayList<>(country.getConnectedCities().keySet());
//            String rndCity1 = cities.get(random.nextInt(cities.size())).getName();
//            String rndCity2 = cities.get(random.nextInt(cities.size())).getName();
//
//            try {
//                if (!rndCity1.equals(rndCity2))
//                    country.addRoad(rndCity1, rndCity2, random.nextInt(100));
//            } catch (IllegalArgumentException ignored) {
//            }
//        }
//
//        country.setMaxDrivingTime(50);
////        long timeout = 10;
//
////       Set<City> actual = country.getFireBrigadeCities(timeout);
//    }
//
//    @Test
//    public void getFireBrigadeCitiesSpreadCountryTest() {
//        country.addCityIfNotExist("D");
//        country.addCityIfNotExist("E");
//        country.addCityIfNotExist("F");
//        country.addCityIfNotExist("G");
//        country.addCityIfNotExist("H");
//        country.addRoad("A", "B", 11);
//        country.addRoad("A", "C", 11);
//        country.addRoad("A", "D", 11);
//        country.addRoad("A", "E", 10);
//        country.addRoad("E", "F", 1);
//        country.addRoad("F", "G", 1);
//        country.addRoad("G", "H", 1);
//        country.setMaxDrivingTime(10);
//        country.setTimeout(5);
//
//        Set<City> actual = country.getFireBrigadeCities();
//        Set<City> expected = new HashSet<>();
//        expected.add(new City("B"));
//        expected.add(new City("C"));
//        expected.add(new City("D"));
//        expected.add(new City("E"));
//
//        Assert.assertEquals(expected, actual);
//
//    }
//    @Test
//    public void getFireBrigadeCitiesCircleCountryTest() {
//        country.addCityIfNotExist("D");
//        country.addCityIfNotExist("E");
//        country.addRoad("A", "B", 2);
//        country.addRoad("B", "C", 2);
//        country.addRoad("C", "D", 2);
//        country.addRoad("D", "E", 2);
//        country.addRoad("E", "A", 2);
//        country.setMaxDrivingTime(10);
//        country.setTimeout(5);
//
//        Set<City> actual = country.getFireBrigadeCities();
//
//        Assert.assertTrue(
//                Collections.singleton(new City("A")).equals(actual) ||
//                        Collections.singleton(new City("B")).equals(actual) ||
//                        Collections.singleton(new City("C")).equals(actual) ||
//                        Collections.singleton(new City("D")).equals(actual) ||
//                        Collections.singleton(new City("E")).equals(actual) ||
//                        Collections.singleton(new City("F")).equals(actual)
//        );
//    }
//}
