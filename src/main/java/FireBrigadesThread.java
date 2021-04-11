
import java.util.*;

public class FireBrigadesThread implements Runnable{
    Set<City> finalFireBrigadeCities = new HashSet<>();
    Set<City> currentFireBrigadeCities = new HashSet<>();
    @Override
    public void run(){
        JSONCountry countryReader = new JSONCountry("in.json", "out.json");
        Country country = new Country(new HashMap<>());
        country = countryReader.getCountry();
        do {
            currentFireBrigadeCities = country.getApproximationOfCities();
            if (currentFireBrigadeCities.size() < finalFireBrigadeCities.size() || finalFireBrigadeCities.isEmpty()) {
                finalFireBrigadeCities = currentFireBrigadeCities;
            }

        } while (!Thread.currentThread().isInterrupted());

    }

    public Set<City> getFinalFireBrigadeCities() {
        return finalFireBrigadeCities;
    }
}
