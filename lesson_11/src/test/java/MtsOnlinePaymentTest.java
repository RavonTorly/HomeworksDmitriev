import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.HomePage;
import org.example.PageModal;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.qameta.allure.*;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Epic("Онлайн оплата МТС")
@Feature("Оплата услуг связи")
@Story("Проверка процесса оплаты услуг связи через платежный виджет")
public class MtsOnlinePaymentTest {
    private WebDriver driver;
    private HomePage mtsHomePage;
    private PageModal paymentConfirmationPage;

    @BeforeEach
    @Step("Инициализация браузера и страницы")
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        mtsHomePage = new HomePage(driver);
    }

    @Test
    @DisplayName("Проверка закрытия Cookie баннера")
    @Description("Тест проверяет, что cookie баннер закрывается при открытии сайта")
    @Severity(SeverityLevel.MINOR)
    @Owner("Тестировщик MTS")
    public void testCookieBannerIsClosed() {
        mtsHomePage.open();
        assertFalse(mtsHomePage.isCookieBannerDisplayed(), "Cookie banner should be closed");
    }

    @Test
    @DisplayName("Проверка блока онлайн оплаты")
    @Description("Тест проверяет основные элементы блока онлайн оплаты")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Тестировщик MTS")
    @Step("Проверка названия блока, логотипов платежных систем, ссылки 'Подробнее о сервисе'")
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
    @DisplayName("Проверка placeholder'ов для всех вариантов оплаты")
    @Description("Тест проверяет placeholder'ы полей для всех типов услуг")
    @Severity(SeverityLevel.NORMAL)
    @Owner("Тестировщик MTS")
    @Step("Проверить placeholder'ы для типа услуг")
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
    @DisplayName("Проверка оплаты услуг связи с корректными данными")
    @Description("Тест проверяет весь процесс оплаты услуг связи: заполнение формы, переход в платежный виджет и проверку данных")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Тестировщик MTS")
    @Step("Проверка формы Услуги связи")
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
    @DisplayName("Проверка доступных опций услуг")
    @Description("Тест проверяет, что в выпадающем списке доступны все опции услуг")
    @Severity(SeverityLevel.MINOR)
    @Owner("Тестировщик MTS")
    @Step("Получить список доступных опций услуг")
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
    @Step("Закрытие браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
