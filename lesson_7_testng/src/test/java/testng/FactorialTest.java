package testng;

import org.example.Factorial;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FactorialTest {
    @Test
    public void testFactorial() {
        assertEquals(Factorial.factorial(0), 1);
        assertEquals(Factorial.factorial(5), 120);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeFactorial() {
        Factorial.factorial(-1);
    }
}
