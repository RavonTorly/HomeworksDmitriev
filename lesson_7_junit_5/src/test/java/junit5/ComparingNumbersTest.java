package junit5;

import org.example.ComparingNumbers;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ComparingNumbersTest {
    @Test
    void testGreaterThan() {
        assertEquals("10 > 5", ComparingNumbers.compare(10, 5));
    }

    @Test
    void testLessThan() {
        assertEquals("5 < 10", ComparingNumbers.compare(5, 10));
    }

    @Test
    void testEqual() {
        assertEquals("10 = 10", ComparingNumbers.compare(10, 10));
    }

    @Test
    void testNegativeNumbers() {
        assertEquals("-5 > -10", ComparingNumbers.compare(-5, -10));
    }
}
