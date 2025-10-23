package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class HomePage extends BasePage{

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

    public void open() {
        driver.get("https://www.mts.by");
        closeCookieBannerIfPresent();
    }

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
    public String getPaymentBlockTitle() {
        return getElementText(driver.findElement(paymentBlockTitle));
    }

    public List<WebElement> getPaymentPartnersLogos() {
        return driver.findElements(paymentPartnersLogos);
    }

    public WebElement getDetailsLink() {
        return driver.findElement(detailsLink);
    }

    // Методы для работы с селектом услуг
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

    public void selectCommunicationServices() {
        openServiceSelect();
        clickElement(driver.findElement(communicationServicesOption));
        waitForFormToBeVisible("pay-connection");
    }

    public void selectHomeInternet() {
        openServiceSelect();
        clickElement(driver.findElement(homeInternetOption));
        waitForFormToBeVisible("pay-internet");
    }

    public void selectInstallment() {
        openServiceSelect();
        clickElement(driver.findElement(installmentOption));
        waitForFormToBeVisible("pay-instalment");
    }

    public void selectDebt() {
        openServiceSelect();
        clickElement(driver.findElement(debtOption));
        waitForFormToBeVisible("pay-arrears");
    }

    private void waitForFormToBeVisible(String formId) {
        By formLocator = By.id(formId);
        waitForElementToBeVisible(driver.findElement(formLocator));
    }

    // Метод для получения текущего выбранного сервиса
    public String getCurrentSelectedService() {
        return getElementText(driver.findElement(currentSelectedService));
    }

    // Методы для получения placeholder текстов
    public String getPhonePlaceholder() {
        return driver.findElement(phoneInput).getAttribute("placeholder");
    }

    public String getAmountPlaceholder() {
        return driver.findElement(amountInput).getAttribute("placeholder");
    }

    public String getEmailPlaceholder() {
        return driver.findElement(emailInput).getAttribute("placeholder");
    }

    public String getInternetAccountPlaceholder() {
        waitForElementToBeVisible(driver.findElement(internetForm));
        return driver.findElement(internetAccountInput).getAttribute("placeholder");
    }

    public String getInstallmentContractPlaceholder() {
        waitForElementToBeVisible(driver.findElement(installmentForm));
        return driver.findElement(installmentContractInput).getAttribute("placeholder");
    }

    public String getDebtDocumentPlaceholder() {
        waitForElementToBeVisible(driver.findElement(debtForm));
        return driver.findElement(debtDocumentInput).getAttribute("placeholder");
    }

    // Методы для заполнения формы услуг связи
    public void enterPhoneNumber(String phone) {
        sendKeysToElement(driver.findElement(phoneInput), phone);
    }

    public void enterAmount(String amount) {
        sendKeysToElement(driver.findElement(amountInput), amount);
    }

    public void enterEmail(String email) {
        sendKeysToElement(driver.findElement(emailInput), email);
    }

    public void clickContinue() {
        clickElement(driver.findElement(continueButton));
    }

    public boolean isContinueButtonEnabled() {
        return driver.findElement(continueButton).isEnabled();
    }

    // Метод для проверки, что форма видима
    public boolean isFormVisible(String formId) {
        try {
            WebElement form = driver.findElement(By.id(formId));
            return form.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Метод для получения всех доступных опций
    public List<String> getAvailableServiceOptions() {
        openServiceSelect();
        List<WebElement> options = driver.findElements(serviceOptions);
        return options.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public boolean isCookieBannerDisplayed() {
        try {
            return driver.findElement(cookieBanner).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

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
