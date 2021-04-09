import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;
import org.junit.Test;

public class CityTest {
    @Test
    public void equalsHashCodeTest() {

        EqualsVerifier.forClass(City.class).suppress(Warning.NONFINAL_FIELDS).verify();
    }
}
