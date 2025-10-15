package junit5;

import org.example.TringleArea;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TringleAreaTest {
    @Test
    void testArea() {
        assertEquals(2.0, TringleArea.calculateArea(2, 2));
        assertEquals(13.5, TringleArea.calculateArea(3, 9));
    }

    @Test
    void testInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> TringleArea.calculateArea(0, 5));
    }
}
