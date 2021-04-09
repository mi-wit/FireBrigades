import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Country {

    private final Map<City, List<Road>> connectedCities;

    public Country(Map<City, List<Road>> connectedCities) {
        this.connectedCities = connectedCities;
    }

    public Map<City, List<Road>> getConnectedCities() {
        return connectedCities;
    }

    void addCity(String name) {
        connectedCities.putIfAbsent(new City(name), new ArrayList<>());
    }

    void addRoad(String firstCityName, String secondCityName, int drivingTime) {
        // TODO only one road for two cities
        // może dodawać misato jak nie istnieje?
        City city1 = new City(firstCityName);
        City city2 = new City(secondCityName);
        if (connectedCities.get(city1).contains(new Road(city2, drivingTime)))
            throw new IllegalArgumentException("This road already exist");

        connectedCities.get(city1).add(new Road(city2, drivingTime));
        connectedCities.get(city2).add(new Road(city1, drivingTime));
    }

    List<City> getNeighbourCities(String cityName) {
        List<Road> cityRoads = connectedCities.get(new City(cityName));
        List<City> cities = new ArrayList<>();
        for (Road road: cityRoads)
            cities.add(road.getDestinationCity());
        return cities;
    }
}
