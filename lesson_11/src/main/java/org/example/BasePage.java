package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 10);
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void clickElement(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
    }

    protected void sendKeysToElement(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
    }

    protected String getElementText(WebElement element) {
        waitForElementToBeVisible(element);
        return element.getText();
    }

    protected void closeCookieBanner() {
        try {
            // Попробуем найти и закрыть cookie-баннер
            WebElement cookieBanner = driver.findElement(By.cssSelector(".cookie.show"));
            WebElement closeButton = cookieBanner.findElement(By.cssSelector(".cookie__close, .close, button"));
            clickElement(closeButton);
            // Ждем пока баннер скроется
            wait.until(ExpectedConditions.invisibilityOf(cookieBanner));
        } catch (Exception e) {
            // Если баннера нет или не удалось закрыть - продолжаем выполнение
            System.out.println("Cookie banner not found or already closed");
        }
    }
}