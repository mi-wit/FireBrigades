import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONWriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class JSONCountry {
    private final File inFile;
    private final File outFile;
    private JSONObject jo;
    private Country country;

    public JSONCountry(String inFileName, String outFileName) {
        this.inFile = new File(inFileName);
        this.outFile = new File(outFileName);
        String jsonText = readJSON();
        jo = new JSONObject(jsonText);
        country = new Country(new HashMap<>());
    }


    public Country getCountry() {
        addCities();
        addRoads();
        addMaxDrivingTime();
        return country;
    }

    private void addCities() {
        Set<String> cities = getCities(jo);
        for (String cityName : cities)
            country.addCityIfNotExist(cityName);
    }

    private void addRoads() {
        JSONArray roads = jo.getJSONArray("drogi");
        for (int i = 0; i < roads.length(); i++) {
            JSONArray roadEnds = roads.getJSONObject(i).getJSONArray("miasta");

            int drivingTime = roads.getJSONObject(i).getInt("czas_przejazdu");
            if (drivingTime < 1)
                throw new IllegalArgumentException("Driving time on road " + roadEnds.get(0) + " " + roadEnds.get(1) + "can't be less than one.");

            country.addRoad(String.valueOf(roadEnds.get(0)),
                    String.valueOf(roadEnds.get(1)),
                    drivingTime);
        }
    }

    private void addMaxDrivingTime() {
        int maxDrivingTime = jo.getInt("max_czas_przejazdu");
        if (maxDrivingTime < 1)
            throw new IllegalArgumentException("max_driving_time can't be less than one");
        country.setMaxDrivingTime(maxDrivingTime);
    }

    public int getTimeoutInSeconds() {
        int timeout = jo.getInt("timeout");
        if (timeout < 1)
            throw new IllegalArgumentException("timeout can't be less than one.");
        return timeout;
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
            jsonText = new Scanner(inFile).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return jsonText;
    }

    public void saveJSON(Set<String> list) {
        try {
            FileWriter fileWriter = new FileWriter(outFile);
            JSONWriter jsonWriter = new JSONWriter(fileWriter);

            jsonWriter.array();
            for (String name : list)
                jsonWriter.value(name);
            jsonWriter.endArray();

            fileWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
