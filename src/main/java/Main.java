import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        //TODO zakresy zmiennych większe niż 0
        JSONCountry jsonCountry = new JSONCountry("in.json", "out.json");
        Set<City> finalFireBrigadeCities;
        long timeout = jsonCountry.getTimeoutInSeconds();
        finalFireBrigadeCities = getFinalFireBrigadeCities(timeout);

        saveToFile(jsonCountry, finalFireBrigadeCities);
        System.out.println(finalFireBrigadeCities);
    }

    private static void saveToFile(JSONCountry jsonCountry, Set<City> finalFireBrigadeCities) {
        City[] cities = finalFireBrigadeCities.toArray(City[]::new);
        Set<String> names= new HashSet<>();
        for (City city: cities)
            names.add(city.getName());

        jsonCountry.saveJSON(names);
    }

    private static Set<City> getFinalFireBrigadeCities(long timeout) {
        Set<City> finalFireBrigadeCities = new HashSet<>();
        long start = System.currentTimeMillis();
        try {
            FireBrigadesThread fireBrigadesThread = new FireBrigadesThread();
            Thread thread = new Thread(fireBrigadesThread);
            thread.start();

            printProgressBar(timeout, start);
            thread.interrupt();
            thread.join();

            finalFireBrigadeCities = fireBrigadesThread.getFinalFireBrigadeCities();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return finalFireBrigadeCities;
    }

    private static void printProgressBar(long timeout, long start) {
        System.out.print("working");
        long currentTime = 0;
        while (System.currentTimeMillis() - start < timeout * 1000) {
            long ct = System.currentTimeMillis();
            if (ct % 1000 == 0 && ct != currentTime) {
                System.out.append('.');
                currentTime = ct;
            }
        }
        System.out.println();
    }
}
