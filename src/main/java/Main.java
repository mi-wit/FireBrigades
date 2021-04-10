
public class Main {
    public static void main(String[] args) {
        JSONCountryReader countryReader = new JSONCountryReader("in.json");
        Country poland = countryReader.getCountry();

        System.out.println(poland.getConnectedCities());

    }
}
