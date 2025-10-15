package junit5;

import org.example.ArithmeticOperations;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ArithmeticOperationsTest {
    @Test
    void testPlus() {
        assertEquals(15, ArithmeticOperations.plus(10, 5));
        assertEquals(-5, ArithmeticOperations.plus(-10, 5));
    }

    @Test
    void testSubtract() {
        assertEquals(5, ArithmeticOperations.subtract(10, 5));
        assertEquals(-15, ArithmeticOperations.subtract(-10, 5));
    }

    @Test
    void testMultiply() {
        assertEquals(50, ArithmeticOperations.multiply(10, 5));
        assertEquals(-50, ArithmeticOperations.multiply(10, -5));
    }

    @Test
    void testDivide() {
        assertEquals(2.0, ArithmeticOperations.divide(10, 5));
        assertEquals(3.333, ArithmeticOperations.divide(10, 3), 0.001);
    }

    @Test
    void testDivideByZero() {
        assertThrows(ArithmeticException.class, () -> ArithmeticOperations.divide(10, 0));
    }
}
