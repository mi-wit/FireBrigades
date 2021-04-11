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
    JSONObject jo;

    public JSONCountry(String inFileName, String outFileName) {
        this.inFile = new File(inFileName);
        this.outFile = new File(outFileName);
        String jsonText = readJSON();
        jo = new JSONObject(jsonText);
    }


    public Country getCountry() {
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

    public int getTimeoutInSeconds() {
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
