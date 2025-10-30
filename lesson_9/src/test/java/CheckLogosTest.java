import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

public class CheckLogosTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLogos() {
        driver.get("https://www.mts.by");
        checkPaymentSystemLogos();
    }

    private void checkPaymentSystemLogos() {
        List<WebElement> logos = driver.findElements(By.cssSelector(".pay__partners img"));

        assertEquals(5, logos.size());

        // Проверяем ожидаемые платежные системы по атрибутам
        List<String> expectedPaymentSystems = Arrays.asList("visa", "mastercard", "belkart");
        int foundExpectedSystems = 0;

        for (WebElement logo : logos) {
            String src = logo.getAttribute("src").toLowerCase();
            String alt = logo.getAttribute("alt") != null ? logo.getAttribute("alt").toLowerCase() : "";

            for (String expectedSystem : expectedPaymentSystems) {
                if (src.contains(expectedSystem) || alt.contains(expectedSystem)) {
                    foundExpectedSystems++;
                    break;
                }
            }
        }

        assertTrue(foundExpectedSystems >= 4, "Should find at least 4 expected payment system logos. Found: " + foundExpectedSystems);
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


