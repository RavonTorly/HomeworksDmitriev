package junit5;

import org.example.Factorial;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class FactorialTest {
    @Test
    void testFactorial() {
        assertEquals(1, Factorial.factorial(0));
        assertEquals(1, Factorial.factorial(1));
        assertEquals(120, Factorial.factorial(5));
    }

    @Test
    void testNegativeFactorial() {
        assertThrows(IllegalArgumentException.class, () -> Factorial.factorial(-1));
    }
}
