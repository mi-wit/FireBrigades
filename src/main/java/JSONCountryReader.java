import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class JSONCountryReader {
    private final File file;

    public JSONCountryReader(String fileName) {
        this.file = new File(fileName);
    }


    public Country getCountry() {
        String jsonText = readJSON();
        JSONObject jo = new JSONObject(jsonText);

        Country country = new Country(new HashMap<>());
        // adding cities
        Set<String> cities = getCities(jo);
        for (String cityName : cities)
            country.addCityIfNotExist(cityName);

        // adding roads
        JSONArray roads = jo.getJSONArray("drogi");
        for (int i = 0; i < roads.length(); i++) {
            JSONArray roadEnds = roads.getJSONObject(i).getJSONArray("miasta");
            int drivingTime = roads.getJSONObject(i).getInt("czas_przejazdu");

            country.addRoad(String.valueOf(roadEnds.get(0)),
                    String.valueOf(roadEnds.get(1)),
                    drivingTime);
        }

        country.setTimeout(jo.getInt("timeout"));
        country.setMaxDrivingTime(jo.getInt("max_czas_przejazdu"));

        return country;
    }

    public int getTimeout() {
        String jsonText = readJSON();
        JSONObject jo = new JSONObject(jsonText);
        return jo.getInt("timeout");
    }

    private Set<String> getCities(JSONObject jo) {
        Set<String> cities = new HashSet<>();
        for (Object city : jo.getJSONArray("miasta"))
            cities.add(city.toString());
        return cities;
    }


    private String readJSON() {
        String jsonText = "";
        try {
            jsonText = new Scanner(file).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jsonText;
    }
}
