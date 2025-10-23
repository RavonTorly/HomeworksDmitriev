import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.HomePage;
import org.example.PageModal;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsOnlinePaymentTest {
    private WebDriver driver;
    private HomePage mtsHomePage;
    private PageModal paymentConfirmationPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mtsHomePage = new HomePage(driver);
    }

    @Test
    public void testCookieBannerIsClosed() {
        mtsHomePage.open();
        assertFalse(mtsHomePage.isCookieBannerDisplayed(), "Cookie banner should be closed");
    }

    @Test
    public void testOnlinePaymentBlock() {
        mtsHomePage.open();

        // 1. Проверить название блока
        String titleText = mtsHomePage.getPaymentBlockTitle();
        assertTrue(titleText.contains("Онлайн пополнение") && titleText.contains("без комиссии"));

        // 2. Проверить логотипы платежных систем
        List<WebElement> logos = mtsHomePage.getPaymentPartnersLogos();
        assertTrue(logos.size() >= 3);

        // 3. Проверка ссылки "Подробнее о сервисе"
        WebElement link = mtsHomePage.getDetailsLink();
        assertTrue(link.isDisplayed());
    }

    @Test
    public void testEmptyFieldPlaceholdersForAllPaymentOptions() {
        mtsHomePage.open();

        // Проверка placeholder'ов для услуг связи (выбрано по умолчанию)
        assertEquals("Услуги связи", mtsHomePage.getCurrentSelectedService());
        assertEquals("Номер телефона", mtsHomePage.getPhonePlaceholder());
        assertEquals("Сумма", mtsHomePage.getAmountPlaceholder());
        assertEquals("E-mail для отправки чека", mtsHomePage.getEmailPlaceholder());

        // Проверка placeholder'ов для домашнего интернета
        mtsHomePage.selectHomeInternet();
        assertEquals("Домашний интернет", mtsHomePage.getCurrentSelectedService());
        String internetPlaceholder = mtsHomePage.getInternetAccountPlaceholder();
        assertTrue(internetPlaceholder.contains("Номер абонента") || internetPlaceholder.contains("номер"));

        // Проверка placeholder'ов для рассрочки
        mtsHomePage.selectInstallment();
        assertEquals("Рассрочка", mtsHomePage.getCurrentSelectedService());
        String installmentPlaceholder = mtsHomePage.getInstallmentContractPlaceholder();
        assertTrue(installmentPlaceholder.contains("Номер счета на 44") || installmentPlaceholder.contains("договор"));

        // Проверка placeholder'ов для задолженности
        mtsHomePage.selectDebt();
        assertEquals("Задолженность", mtsHomePage.getCurrentSelectedService());
        String debtPlaceholder = mtsHomePage.getDebtDocumentPlaceholder();
        assertTrue(debtPlaceholder.contains("Номер счета на 2073") || debtPlaceholder.contains("документ"));
    }

    @Test
    public void testCommunicationServicesPaymentFlow() {
        mtsHomePage.open();

        assertEquals("Услуги связи", mtsHomePage.getCurrentSelectedService());

        mtsHomePage.enterPhoneNumber("297777777");
        mtsHomePage.enterAmount("10");
        mtsHomePage.enterEmail("test@example.com");

        assertTrue(mtsHomePage.isContinueButtonEnabled(), "Кнопка 'Продолжить' должна быть активна");

        PageModal paymentModal = mtsHomePage.clickContinueAndOpenPaymentIframe();
        assertTrue(paymentModal.isIframeLoaded(), "Должен загрузиться платежный iframe bePaid");
        paymentModal.debugIframeInfo();

        // 1. Проверяем корректность отображения суммы
        String amount = paymentModal.getPaymentAmount();
        assertTrue(amount.contains("10.00 BYN"), "Сумма должна содержать '10.00 BYN'. Фактическое значение: " + amount);

        // 2. Проверяем корректность отображения номера телефона
        String phone = paymentModal.getPhoneNumber();
        assertTrue(phone.contains("375297777777"), "Номер телефона должен содержать '375297777777'. Фактическое значение: " + phone);

        // 3. Проверяем тип услуги
        String serviceType = paymentModal.getServiceType();
        assertTrue(serviceType.contains("Услуги связи"), "Тип услуги должен быть 'Услуги связи'. Фактическое значение: " + serviceType);

        // 4. Проверяем наличие полей для ввода реквизитов карты
        assertTrue(paymentModal.isCardNumberInputDisplayed(), "Должно отображаться поле для номера карты");
        assertTrue(paymentModal.isExpiryDateInputDisplayed(), "Должно отображаться поле для срока действия");
        assertTrue(paymentModal.isCvvInputDisplayed(), "Должно отображаться поле для CVC");
        assertTrue(paymentModal.isCardHolderInputDisplayed(), "Должно отображаться поле для имени владельца карты");

        // 5. Проверяем надписи (лейблы или placeholder'ы)
        String cardNumberLabel = paymentModal.getCardNumberLabel();
        String cardNumberPlaceholder = paymentModal.getCardNumberPlaceholder();
        assertTrue(!cardNumberLabel.isEmpty() || !cardNumberPlaceholder.isEmpty(), "Должна быть надпись или placeholder для номера карты");

        String expiryDateLabel = paymentModal.getExpiryDateLabel();
        String expiryDatePlaceholder = paymentModal.getExpiryDatePlaceholder();
        assertTrue(!expiryDateLabel.isEmpty() || !expiryDatePlaceholder.isEmpty(), "Должна быть надпись или placeholder для срока действия");

        String cvvLabel = paymentModal.getCvvLabel();
        String cvvPlaceholder = paymentModal.getCvvPlaceholder();
        assertTrue(!cvvLabel.isEmpty() || !cvvPlaceholder.isEmpty(), "Должна быть надпись или placeholder для CVC");


        System.out.println("Все проверки платежного iframe пройдены успешно!");

        paymentModal.switchToDefaultContent();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testServiceOptionsAvailable() {
        mtsHomePage.open();

        List<String> options = mtsHomePage.getAvailableServiceOptions();
        assertTrue(options.contains("Услуги связи"));
        assertTrue(options.contains("Домашний интернет"));
        assertTrue(options.contains("Рассрочка"));
        assertTrue(options.contains("Задолженность"));
        assertEquals(4, options.size());
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
