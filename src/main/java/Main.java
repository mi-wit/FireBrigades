import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        File file = new File("in.json");
        String jsonText = null;
        try {
            jsonText = new Scanner(file).useDelimiter("\\Z").next();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JSONObject jo = new JSONObject(jsonText);
        System.out.println(jo.get("miasta"));
    }
}
