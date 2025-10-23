package org.example;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import io.qameta.allure.*;
import java.util.List;

public class PageModal extends BasePage {

    // Локатор для iframe
    private By bepaidIframe = By.cssSelector(".bepaid-iframe");

    // Локаторы внутри iframe
    private By paymentAmount = By.cssSelector(".pay-description__cost span");
    private By paymentInfo = By.cssSelector(".pay-description__text span");


    // Локаторы для полей карты
    private By cardNumberInput = By.cssSelector("input[formcontrolname='creditCard']");
    private By expiryDateInput = By.cssSelector("input[formcontrolname='expirationDate']");
    private By cvvInput = By.cssSelector("input[formcontrolname='cvc']");
    private By cardHolderInput = By.cssSelector("input[formcontrolname='holder']");

    // Локаторы для лейблов
    private By cardNumberLabel = By.xpath("//label[contains(text(), 'Номер карты')]");
    private By expiryDateLabel = By.xpath("//label[contains(text(), 'Срок действия')]");
    private By cvvLabel = By.xpath("//label[contains(text(), 'CVC')]");
    private By cardHolderLabel = By.xpath("//label[contains(text(), 'Имя и фамилия на карте')]");


    public PageModal(WebDriver driver) {
        super(driver);
        switchToIframe();
    }

    @Step("Переключиться в iframe платежного виджета")
    private void switchToIframe() {
        try {
            // Ждем появления iframe и переключаемся в него
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(bepaidIframe));
            System.out.println("Успешно переключились в iframe bePaid");

            // Ждем загрузки контента внутри iframe
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='app-wrapper']//section[contains(text()]")));

        } catch (Exception e) {
            System.out.println("Ошибка при переключении в iframe: " + e.getMessage());
        }
    }

    // Метод для возврата к основному контенту
    @Step("Вернуться к основному контенту")
    public void switchToDefaultContent() {
        driver.switchTo().defaultContent();
    }

    // Методы для проверки данных платежа
    @Step("Получить сумму платежа")
    public String getPaymentAmount() {
        try {
            WebElement amountElement = driver.findElement(paymentAmount);
            return amountElement.getText();
        } catch (Exception e) {
            System.out.println("Amount element not found in iframe");
            return "";
        }
    }

    @Step("Получить информацию о платеже")
    public String getPaymentInfo() {
        try {
            WebElement infoElement = driver.findElement(paymentInfo);
            return infoElement.getText();
        } catch (Exception e) {
            System.out.println("Payment info element not found in iframe");
            return "";
        }
    }

    @Step("Получить номер телефона из информации о платеже")
    public String getPhoneNumber() {
        String info = getPaymentInfo();
        if (info.contains("375297777777")) {
            return info;
        }
        return "";
    }

    @Step("Получить тип услуги из информации о платеже")
    public String getServiceType() {
        String info = getPaymentInfo();
        if (info.contains("Услуги связи")) {
            return info;
        }
        return "";
    }


    // Методы для проверки полей карты
    @Step("Проверить отображение поля номера карты")
    public boolean isCardNumberInputDisplayed() {
        try {
            return driver.findElement(cardNumberInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить отображение поля срока действия карты")
    public boolean isExpiryDateInputDisplayed() {
        try {
            return driver.findElement(expiryDateInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить отображение поля CVC/CVV")
    public boolean isCvvInputDisplayed() {
        try {
            return driver.findElement(cvvInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить отображение поля владельца карты")
    public boolean isCardHolderInputDisplayed() {
        try {
            return driver.findElement(cardHolderInput).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Методы для получения placeholder'ов
    @Step("Получить placeholder поля номера карты")
    public String getCardNumberPlaceholder() {
        return getInputAttribute(cardNumberInput, "placeholder");
    }

    @Step("Получить placeholder поля срока действия")
    public String getExpiryDatePlaceholder() {
        return getInputAttribute(expiryDateInput, "placeholder");
    }

    @Step("Получить placeholder поля CVC")
    public String getCvvPlaceholder() {
        return getInputAttribute(cvvInput, "placeholder");
    }

    @Step("Получить placeholder поля владельца карты")
    public String getCardHolderPlaceholder() {
        return getInputAttribute(cardHolderInput, "placeholder");
    }

    private String getInputAttribute(By locator, String attribute) {
        try {
            WebElement input = driver.findElement(locator);
            return input.getAttribute(attribute);
        } catch (Exception e) {
            return "";
        }
    }

    // Методы для проверки лейблов
    @Step("Получить текст лейбла номера карты")
    public String getCardNumberLabel() {
        return getLabelText(cardNumberLabel);
    }

    @Step("Получить текст лейбла срока действия")
    public String getExpiryDateLabel() {
        return getLabelText(expiryDateLabel);
    }

    @Step("Получить текст лейбла CVC")
    public String getCvvLabel() {
        return getLabelText(cvvLabel);
    }

    @Step("Получить текст лейбла владельца карты")
    public String getCardHolderLabel() {
        return getLabelText(cardHolderLabel);
    }

    private String getLabelText(By locator) {
        try {
            WebElement label = driver.findElement(locator);
            return label.getText();
        } catch (Exception e) {
            return "";
        }
    }


    // Метод для проверки что iframe загружен
    @Step("Проверить загрузку iframe платежного виджета")
    public boolean isIframeLoaded() {
        try {
            // Проверяем наличие основных элементов внутри iframe
            boolean hasAppWrapper = !driver.findElements(By.cssSelector(".app-wrapper__content")).isEmpty();
            boolean hasPaymentInfo = !getPaymentInfo().isEmpty();
            return hasAppWrapper && hasPaymentInfo;
        } catch (Exception e) {
            return false;
        }
    }

    // Метод для отладки
    @Step("Вывести отладочную информацию о платежном виджете")
    public void debugIframeInfo() {
        System.out.println("=== PAYMENT IFRAME DEBUG INFO ===");
        System.out.println("Iframe loaded: " + isIframeLoaded());
        System.out.println("Amount: " + getPaymentAmount());
        System.out.println("Payment Info: " + getPaymentInfo());
        System.out.println("Phone: " + getPhoneNumber());
        System.out.println("Service Type: " + getServiceType());

        System.out.println("Card inputs displayed: " +
                isCardNumberInputDisplayed() + ", " +
                isExpiryDateInputDisplayed() + ", " +
                isCvvInputDisplayed() + ", " +
                isCardHolderInputDisplayed());
    }

}
