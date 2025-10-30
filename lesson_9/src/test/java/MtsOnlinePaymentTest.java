import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


public class MtsOnlinePaymentTest {
    WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testOnlinePaymentBlock() {
        driver.get("https://www.mts.by");

        WebElement phoneInput = driver.findElement(By.cssSelector(".pay__form input[placeholder*='Номер телефона']"));
        phoneInput.sendKeys("297777777");

        WebElement amountInput = driver.findElement(By.cssSelector(".pay__form input[placeholder*='Сумма']"));
        amountInput.sendKeys("10");

        WebElement emailInput = driver.findElement(By.cssSelector(".pay__form input[type='text']"));
        emailInput.sendKeys("test@example.com");

        WebElement continueBtn = driver.findElement(By.xpath("//div[@class='pay__form']//button[contains(text(), 'Продолжить')]"));
        assertTrue(continueBtn.isEnabled());

        continueBtn.click();
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
