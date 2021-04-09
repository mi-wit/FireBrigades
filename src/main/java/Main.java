import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        File file = new File("in.json");
        String jsonText = "";
        try {
            jsonText = new Scanner(file).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Country country = new Country(new HashMap<>());
        JSONObject jo = new JSONObject(jsonText);
        // czytanie i uzupełnianie miast
        for (Object miasto: jo.getJSONArray("miasta"))
            country.addCity(miasto.toString());

        // czytanie i uzupełnianie dróg
        JSONArray ja = jo.getJSONArray("drogi");
        for (int i = 0; i < ja.length(); i++) {
            String miasto1 = (String) ja.getJSONObject(i).getJSONArray("miasta").get(0);
            String miasto2 = (String) ja.getJSONObject(i).getJSONArray("miasta").get(1);
            int czasPrzejazdu = ja.getJSONObject(i).getInt("czas_przejazdu");
            country.addRoad(miasto1, miasto2, czasPrzejazdu);
        }

        System.out.println(country.getConnectedCities());
    }
}
