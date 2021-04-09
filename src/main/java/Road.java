import java.util.Objects;

public class Road {
    private final City destinationCity;
    private final int drivingTime;

    public Road(City destinationCity, int drivingTime) {
        this.destinationCity = destinationCity;
        this.drivingTime = drivingTime;
    }

    public City getDestinationCity() {
        return destinationCity;
    }

    public int getDrivingTime() {
        return drivingTime;
    }

    // zakładam, że dwie drogi są takie same jeśli tylko łączą dwa miasta
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return destinationCity.equals(road.destinationCity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destinationCity, drivingTime);
    }

    @Override
    public String toString() {
        return "Road{" +
                "destinationCity=" + destinationCity +
                ", drivingTime=" + drivingTime +
                '}';
    }
}
