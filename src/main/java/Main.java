
public class Main {
    public static void main(String[] args) {
        JSONCountryReader countryReader = new JSONCountryReader("in.json");
        Country poland = countryReader.getCountry();

        //TODO zakresy zmiennych większe niż 0
        System.out.println(poland.getConnectedCities());

    }
}
