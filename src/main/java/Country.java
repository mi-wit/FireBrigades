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

    public long getMaxDrivingTime() {
        return maxDrivingTime;
    }

    public void setMaxDrivingTime(int maxDrivingTime) {
        this.maxDrivingTime = maxDrivingTime;
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

    public Set<City> getFireBrigadeCities(long timeout) {

        Set<City> finalFireBrigadeCities = new HashSet<>();
        Set<City> currentFireBrigadeCities;
        long start = System.currentTimeMillis();
        long timeElapsed;
        long timesSameApproximation = timeout * 100;
        do {
            currentFireBrigadeCities = getApproximationOfCities();
            if (currentFireBrigadeCities.size() < finalFireBrigadeCities.size() || finalFireBrigadeCities.isEmpty()) {
                finalFireBrigadeCities = currentFireBrigadeCities;
                timesSameApproximation = timeout * 100;
            }

            timesSameApproximation--;
            timeElapsed = System.currentTimeMillis() - start;
        } while (timeElapsed < timeout * 1000);

        return finalFireBrigadeCities;
    }

    private Set<City> getApproximationOfCities() {
        Random random = new Random();
        ArrayList<City> cities = new ArrayList<>(connectedCities.keySet());
        Set<City> fireBrigadeCities = new HashSet<>();

        do {
            City rndCity = cities.get(random.nextInt(cities.size()));
            cities.remove(rndCity);
            fireBrigadeCities.add(rndCity);
            removeCities(rndCity, 0, cities);

        } while (cities.size() > 0);

        return fireBrigadeCities;
    }

    private void removeCities(City city, int drivingTime, ArrayList<City> cities) {
        int thisCityDrivingTime = drivingTime;
        for (Road road : connectedCities.get(city)) {
            drivingTime += road.getDrivingTime();
            if (drivingTime <= maxDrivingTime) {
                cities.remove(road.getDestinationCity());
                removeCities(road.getDestinationCity(), drivingTime, cities);
            }
            drivingTime = thisCityDrivingTime;
        }


    }
}
