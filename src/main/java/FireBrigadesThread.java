
import java.util.*;

public class FireBrigadesThread implements Runnable {
    Set<City> bestSoFarFireBrigadesCities = new HashSet<>();
    Set<City> currentFireBrigadeCities = new HashSet<>();
    Country country;
    public FireBrigadesThread(Country country) {
        this.country = country;
    }

    @Override
    public void run() {
        // attempts to find best, minimal answer until interrupted from outside
        do {
            currentFireBrigadeCities = country.getApproximationOfCities();
            if (weGotABetterApproximation()) {
                bestSoFarFireBrigadesCities = currentFireBrigadeCities;
            }

        } while (!Thread.currentThread().isInterrupted());

    }

    private boolean weGotABetterApproximation() {
        return currentFireBrigadeCities.size() < bestSoFarFireBrigadesCities.size() || bestSoFarFireBrigadesCities.isEmpty();
    }

    public Set<City> getFinalFireBrigadeCities() {
        return bestSoFarFireBrigadesCities;
    }
}
