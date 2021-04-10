import java.util.Objects;

public class City {
    private String name;
    private boolean fireBrigade = true;

    City(String name) {
        this.name = name;
    }

    public boolean isFireBrigaded() {
        return fireBrigade;
    }

    public void setFireBrigade(boolean fireBrigade) {
        this.fireBrigade = fireBrigade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return name.equals(city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                '}';
    }
}
