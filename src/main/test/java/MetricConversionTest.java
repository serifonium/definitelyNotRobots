import com.example.definitelynotrobots.FoodTypesEnum;
import com.example.definitelynotrobots.GroceryItem;
import com.example.definitelynotrobots.MetricConversion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MetricConversionTest {
    private MetricConversion metricConversion;

    @BeforeEach
    public void setUp() {
        metricConversion = new MetricConversion("kg", "g", 1000d);
    }

    @Test
    public void testIsApplicableForBoth() { assertEquals(Boolean.TRUE, metricConversion.isApplicableTypes("kg", "g")); }
    @Test
    public void testIsApplicableForBothInverted() { assertEquals(Boolean.TRUE, metricConversion.isApplicableTypes("g", "kg")); }
    @Test
    public void testIsApplicableForA() { assertEquals(Boolean.FALSE, metricConversion.isApplicableTypes("kg", "m")); }
    @Test
    public void testIsApplicableForB() { assertEquals(Boolean.FALSE, metricConversion.isApplicableTypes("cm", "g")); }
    @Test
    public void testIsApplicableForNone() { assertEquals(Boolean.FALSE, metricConversion.isApplicableTypes("cm", "ft")); }

    @Test
    public void testAddCompatibleKG() { assertEquals(2d, metricConversion.addValues(1d, 1000d, "kg")); }
    @Test
    public void testAddCompatibleG() { assertEquals(1100d, metricConversion.addValues(1d, 100d, "g")); }
    @Test
    public void testAddCompatible1() { assertEquals(51d, metricConversion.addValues(50.50d, 500d, "kg")); }
    @Test
    public void testAddCompatible2() { assertEquals(20030d, metricConversion.addValues(20d, 30d, "g")); }

    // Error Cases
    @Test
    public void testAddIncompatible() { assertEquals(0d, metricConversion.addValues(1d, 100d, "km")); }
}