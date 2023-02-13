import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class BlackScholesTest {
    @Test
    public void testCallPrice() {
        double S = 100.0;
        double K = 100.0;
        double r = 0.05;
        double v = 0.2;
        double T = 1.0;

        double expectedCallPrice = 7.9655;
        double delta = 0.0001;
        double actualCallPrice = BlackScholes.call_price(S, K, r, v, T);

        assertEquals(expectedCallPrice, actualCallPrice, delta);
    }
}
