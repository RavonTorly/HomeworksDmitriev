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

import static org.junit.jupiter.api.Assertions.*;

public class ServiceLink {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void serviceLink() {
        driver.get("https://www.mts.by");
        serviceDetail();
    }

    private void serviceDetail() {
        WebElement link = driver.findElement(By.cssSelector(".pay a"));

        assertTrue(link.isDisplayed(), "Link should be visible");
        String linkHref = link.getAttribute("href");
        assertTrue(linkHref.contains("/help/poryadok-oplaty-i-bezopasnost-internet-platezhey"), "Link should point to correct path. Actual: " + linkHref);

        // Сохраняем текущий URL
        String originalUrl = driver.getCurrentUrl();

        // Кликаем на ссылку
        link.click();

        // Ждем загрузки новой страницы
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));

        // Ждем изменения URL
        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));
    }

}
