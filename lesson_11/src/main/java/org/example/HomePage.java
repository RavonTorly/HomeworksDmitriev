package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

import io.qameta.allure.*;


public class HomePage extends BasePage {

    private By cookieBanner = By.cssSelector(".cookie.show");
    private By cookieCloseButton = By.cssSelector(".cookie__close, .cookie button, .close");

    private By paymentBlockTitle = By.cssSelector(".pay h2");
    private By paymentPartnersLogos = By.cssSelector(".pay__partners img");
    private By detailsLink = By.xpath("//a[contains(text(), 'Подробнее о сервисе')]");

    // Локаторы для кастомного селекта
    private By serviceSelectButton = By.cssSelector(".select__header");
    private By serviceSelectList = By.cssSelector(".select__list");
    private By serviceOptions = By.cssSelector(".select__option");

    // Конкретные опции в выпадающем списке
    private By communicationServicesOption = By.xpath("//p[@class='select__option' and text()='Услуги связи']");
    private By homeInternetOption = By.xpath("//p[@class='select__option' and text()='Домашний интернет']");
    private By installmentOption = By.xpath("//p[@class='select__option' and text()='Рассрочка']");
    private By debtOption = By.xpath("//p[@class='select__option' and text()='Задолженность']");

    // Текущее выбранное значение
    private By currentSelectedService = By.cssSelector(".select__now");

    // Локаторы для формы услуг связи
    private By phoneInput = By.id("connection-phone");
    private By amountInput = By.id("connection-sum");
    private By emailInput = By.id("connection-email");
    private By continueButton = By.xpath("//form[@id='pay-connection']//button[contains(text(), 'Продолжить')]");

    // Локаторы для других форм
    private By internetForm = By.id("pay-internet");
    private By installmentForm = By.id("pay-instalment");
    private By debtForm = By.id("pay-arrears");

    // Локаторы полей для других услуг
    private By internetAccountInput = By.cssSelector("#pay-internet input[placeholder*='Номер абонента']");
    private By installmentContractInput = By.cssSelector("#pay-instalment input[placeholder*='Номер счета на 44']");
    private By debtDocumentInput = By.cssSelector("#pay-arrears input[placeholder*='Номер счета на 2073']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть сайт MTS")
    public void open() {
        driver.get("https://www.mts.by");
        closeCookieBannerIfPresent();
    }

    @Step("Закрыть cookie баннер если присутствует")
    public void closeCookieBannerIfPresent() {
        try {
            WebElement cookieBannerElement = driver.findElement(cookieBanner);
            if (cookieBannerElement.isDisplayed()) {
                try {
                    // Пробуем найти кнопку закрытия
                    WebElement closeBtn = driver.findElement(cookieCloseButton);
                    clickElement(closeBtn);
                    // Ждем скрытия баннера
                    wait.until(ExpectedConditions.invisibilityOf(cookieBannerElement));
                    System.out.println("Cookie banner closed successfully");
                } catch (Exception e) {
                    System.out.println("Could not find cookie close button, trying JavaScript click");
                    // Пробуем клик через JavaScript
                    WebElement closeBtn = driver.findElement(cookieCloseButton);
                    ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", closeBtn);
                }
            }
        } catch (Exception e) {
            System.out.println("Cookie banner not found or already closed");
        }
    }


    // Методы для проверки основного блока
    @Step("Получить заголовок блока оплаты")
    public String getPaymentBlockTitle() {
        return getElementText(driver.findElement(paymentBlockTitle));
    }

    @Step("Получить логотипы платежных систем")
    public List<WebElement> getPaymentPartnersLogos() {
        return driver.findElements(paymentPartnersLogos);
    }

    @Step("Получить ссылку 'Подробнее о сервисе'")
    public WebElement getDetailsLink() {
        return driver.findElement(detailsLink);
    }

    // Методы для работы с селектом услуг
    @Step("Открыть выпадающий список услуг")
    public void openServiceSelect() {
        // Убедимся, что cookie-баннер не мешает
        closeCookieBannerIfPresent();

        // Прокрутим к элементу чтобы убедиться что он видим
        WebElement selectButton = driver.findElement(serviceSelectButton);
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectButton);

        // Ждем и кликаем
        waitForElementToBeClickable(selectButton);
        clickElement(selectButton);

        // Ждем пока список станет видимым
        waitForElementToBeVisible(driver.findElement(serviceSelectList));
    }

    @Step("Выбрать услугу: Услуги связи")
    public void selectCommunicationServices() {
        openServiceSelect();
        clickElement(driver.findElement(communicationServicesOption));
        waitForFormToBeVisible("pay-connection");
    }

    @Step("Выбрать услугу: Домашний интернет")
    public void selectHomeInternet() {
        openServiceSelect();
        clickElement(driver.findElement(homeInternetOption));
        waitForFormToBeVisible("pay-internet");
    }

    @Step("Выбрать услугу: Рассрочка")
    public void selectInstallment() {
        openServiceSelect();
        clickElement(driver.findElement(installmentOption));
        waitForFormToBeVisible("pay-instalment");
    }

    @Step("Выбрать услугу: Задолженность")
    public void selectDebt() {
        openServiceSelect();
        clickElement(driver.findElement(debtOption));
        waitForFormToBeVisible("pay-arrears");
    }

    @Step("Дождаться отображения формы: {formId}")
    private void waitForFormToBeVisible(String formId) {
        By formLocator = By.id(formId);
        waitForElementToBeVisible(driver.findElement(formLocator));
    }

    // Метод для получения текущего выбранного сервиса
    @Step("Получить текущий выбранный сервис")
    public String getCurrentSelectedService() {
        return getElementText(driver.findElement(currentSelectedService));
    }

    // Методы для получения placeholder текстов
    @Step("Получить placeholder поля номера телефона")
    public String getPhonePlaceholder() {
        return driver.findElement(phoneInput).getAttribute("placeholder");
    }

    @Step("Получить placeholder поля суммы")
    public String getAmountPlaceholder() {
        return driver.findElement(amountInput).getAttribute("placeholder");
    }

    @Step("Получить placeholder поля email")
    public String getEmailPlaceholder() {
        return driver.findElement(emailInput).getAttribute("placeholder");
    }

    @Step("Получить placeholder поля номера абонента (интернет)")
    public String getInternetAccountPlaceholder() {
        waitForElementToBeVisible(driver.findElement(internetForm));
        return driver.findElement(internetAccountInput).getAttribute("placeholder");
    }

    @Step("Получить placeholder поля номера счета (рассрочка)")
    public String getInstallmentContractPlaceholder() {
        waitForElementToBeVisible(driver.findElement(installmentForm));
        return driver.findElement(installmentContractInput).getAttribute("placeholder");
    }

    @Step("Получить placeholder поля номера документа (задолженность)")
    public String getDebtDocumentPlaceholder() {
        waitForElementToBeVisible(driver.findElement(debtForm));
        return driver.findElement(debtDocumentInput).getAttribute("placeholder");
    }

    // Методы для заполнения формы услуг связи
    @Step("Ввести номер телефона: {phone}")
    public void enterPhoneNumber(String phone) {
        sendKeysToElement(driver.findElement(phoneInput), phone);
    }

    @Step("Ввести сумму: {amount}")
    public void enterAmount(String amount) {
        sendKeysToElement(driver.findElement(amountInput), amount);
    }

    @Step("Ввести email: {email}")
    public void enterEmail(String email) {
        sendKeysToElement(driver.findElement(emailInput), email);
    }

    @Step("Нажать кнопку 'Продолжить'")
    public void clickContinue() {
        clickElement(driver.findElement(continueButton));
    }

    @Step("Проверить активна ли кнопка 'Продолжить'")
    public boolean isContinueButtonEnabled() {
        return driver.findElement(continueButton).isEnabled();
    }

    // Метод для проверки, что форма видима
    @Step("Проверить отображение формы: {formId}")
    public boolean isFormVisible(String formId) {
        try {
            WebElement form = driver.findElement(By.id(formId));
            return form.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Метод для получения всех доступных опций
    @Step("Получить список доступных опций услуг")
    public List<String> getAvailableServiceOptions() {
        openServiceSelect();
        List<WebElement> options = driver.findElements(serviceOptions);
        return options.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    @Step("Проверить отображение cookie баннера")
    public boolean isCookieBannerDisplayed() {
        try {
            return driver.findElement(cookieBanner).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Нажать 'Продолжить' и открыть платежный iframe")
    public PageModal clickContinueAndOpenPaymentIframe() {
        clickElement(driver.findElement(continueButton));

        // Ждем появления iframe
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".bepaid-iframe")));
            System.out.println("Iframe bePaid обнаружен");
        } catch (Exception e) {
            System.out.println("Iframe не появился: " + e.getMessage());
        }

        return new PageModal(driver);
    }

}
