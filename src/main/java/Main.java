import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        runProgram();
    }

    private static void runProgram() {
        final String outFile = "out.json";
        JSONCountry jsonCountry = new JSONCountry("in.json", outFile);
        long timeout = jsonCountry.getTimeoutInSeconds();
        Country country = jsonCountry.getCountry();

        Set<City> finalFireBrigadeCities = getFinalFireBrigadeCities(timeout, country);

        saveToFile(jsonCountry, finalFireBrigadeCities);
        System.out.println("result: " + finalFireBrigadeCities + " \nSaved to: " + outFile);
    }

    public static Set<City> getFinalFireBrigadeCities(long timeout, Country country) {
        Set<City> finalFireBrigadeCities = new HashSet<>();
        long timeMeasureStart = System.currentTimeMillis();
        try {
            FireBrigadesThread fireBrigadesThread = new FireBrigadesThread(country);
            Thread thread = new Thread(fireBrigadesThread);
            thread.start(); // Here runs the main program, it will read file, calculate optimal result

            printProgressBar(timeout, timeMeasureStart);
            thread.interrupt();
            thread.join();

            finalFireBrigadeCities = fireBrigadesThread.getFinalFireBrigadeCities();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return finalFireBrigadeCities;
    }

    private static void saveToFile(JSONCountry jsonCountry, Set<City> finalFireBrigadeCities) {
        // converting Set<City> to Set<String>
        City[] cities = finalFireBrigadeCities.toArray(City[]::new);
        Set<String> names= new HashSet<>();
        for (City city: cities)
            names.add(city.getName());

        jsonCountry.saveJSON(names);
    }

    private static void printProgressBar(long timeout, long timeMeasureStart) {
        System.out.print("finding best answer");
        long currentTime = 0;
        while (System.currentTimeMillis() - timeMeasureStart < timeout * 1000) {
            long ct = System.currentTimeMillis();
            if (ct % 1000 == 0 && ct != currentTime) {
                System.out.append('.');
                currentTime = ct;
            }
        }
        System.out.println();
    }
}
