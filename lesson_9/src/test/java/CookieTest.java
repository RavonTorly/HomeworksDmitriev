import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CookieTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testCookieBaner() {
        driver.get("https://www.mts.by");
        handleCookiesPopup();
    }

    private void handleCookiesPopup() {
        try {
            // Ждем появления cookie окна
            WebElement cookiePopup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cookie.show")));

            // Кликаем только кнопку "Принять" по ID
            WebElement acceptCookiesBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("cookie-agree")));
            acceptCookiesBtn.click();

            // Ждем исчезновения cookie окна
            wait.until(ExpectedConditions.invisibilityOf(cookiePopup));

            System.out.println("Cookie popup accepted successfully");

        } catch (Exception e) {
            System.out.println("Cookie popup not found or already closed: " + e.getMessage());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
