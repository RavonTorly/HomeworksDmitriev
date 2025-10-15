package testng;

import org.example.TringleArea;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class TringleAreaTest {
    @Test
    public void testArea() {
        assertEquals(TringleArea.calculateArea(5, 4), 10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testInvalidInput() {
        TringleArea.calculateArea(-1, 5);
    }
}
