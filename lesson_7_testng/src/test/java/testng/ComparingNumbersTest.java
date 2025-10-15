package testng;

import org.example.ComparingNumbers;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class ComparingNumbersTest {
    @Test
    public void testGreaterThan() {
        assertEquals(ComparingNumbers.compare(10, 5), "10 > 5");
    }

    @Test
    public void testLessThan() {
        assertEquals(ComparingNumbers.compare(5, 10), "5 < 10");
    }

    @Test
    public void testEqual() {
        assertEquals(ComparingNumbers.compare(10, 10), "10 = 10");
    }

    @Test
    public void testNegativeNumbers() {
        assertEquals(ComparingNumbers.compare(-5, -10), "-5 > -10");
    }
}
