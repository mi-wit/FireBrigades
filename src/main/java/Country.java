import java.util.*;

public class Country {

    private final Map<City, List<Road>> connectedCities;
    private long maxDrivingTime;

    public Country(Map<City, List<Road>> connectedCities) {
        this.connectedCities = connectedCities;
    }

    public Map<City, List<Road>> getConnectedCities() {
        return connectedCities;
    }

    void addCityIfNotExist(String name) {
        connectedCities.putIfAbsent(new City(name), new ArrayList<>());
    }

    void addRoad(String firstCityName, String secondCityName, int drivingTime) {
        addCityIfNotExist(firstCityName);
        addCityIfNotExist(secondCityName);
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
        for (Road road : cityRoads)
            cities.add(road.getDestinationCity());
        return cities;
    }

    public Set<City> getApproximationOfCities() {
        ArrayList<City> cities = new ArrayList<>(connectedCities.keySet());
        Set<City> fireBrigadeCities = new HashSet<>();

        boolean threadInterrupted;
        do {
            City rndCity = cities.get(randomIndex(cities));
            cities.remove(rndCity);
            fireBrigadeCities.add(rndCity);
            removeCities(rndCity, 0, cities);

            threadInterrupted = Thread.currentThread().isInterrupted();
        } while (cities.size() > 0 && !threadInterrupted);

        if (threadInterrupted)
            return connectedCities.keySet();   // if the thread was interrupted return all cities
        else
            return fireBrigadeCities;

    }


    private void removeCities(City city, int drivingTime, ArrayList<City> cities) {
        int thisCityDrivingTime = drivingTime;
        Set<Road> visited = new HashSet<>();
        for (Road road : connectedCities.get(city)) {
            if (!visited.contains(road)) {
                visited.add(road);
                drivingTime += road.getDrivingTime();
                if (drivingTime <= maxDrivingTime) {
                    cities.remove(road.getDestinationCity());
                    removeCities(road.getDestinationCity(), drivingTime, cities);
                }
                drivingTime = thisCityDrivingTime;
            }
        }


    }

    private int randomIndex(ArrayList<City> list) {
        Random random = new Random();
        return random.nextInt(list.size());
    }

    public void setMaxDrivingTime(int maxDrivingTime) {
        this.maxDrivingTime = maxDrivingTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Country country = (Country) o;
        return maxDrivingTime == country.maxDrivingTime && connectedCities.equals(country.connectedCities);
    }

    @Override
    public int hashCode() {
        return Objects.hash(connectedCities, maxDrivingTime);
    }
}
