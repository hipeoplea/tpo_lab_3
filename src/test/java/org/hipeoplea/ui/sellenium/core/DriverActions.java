package org.hipeoplea.ui.sellenium.core;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

final class DriverActions {

    private final WebDriver driver;
    private final WebDriverWait wait;

    DriverActions(WebDriver driver, Duration defaultTimeout) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, defaultTimeout);
    }

    void openUrl(String url) {
        driver.get(url);
        wait.until(pageIsFullyLoaded());
    }

    boolean waitUntil(Function<WebDriver, Boolean> condition, Duration timeout) {
        try {
            new WebDriverWait(driver, timeout).until(condition::apply);
            return true;
        } catch (TimeoutException exception) {
            return false;
        }
    }

    WebElement waitForAnyVisible(Duration timeout, By... locators) {
        return new WebDriverWait(driver, timeout).until(currentDriver -> findFirstDisplayed(currentDriver, locators));
    }

    WebElement waitForAnyClickable(Duration timeout, By... locators) {
        return new WebDriverWait(driver, timeout).until(currentDriver -> {
            WebElement element = findFirstDisplayed(currentDriver, locators);
            if (element != null && element.isEnabled()) {
                return element;
            }
            return null;
        });
    }

    boolean isAnyElementVisible(By... locators) {
        return findFirstDisplayed(driver, locators) != null;
    }

    String waitForNonBlankText(Duration timeout, By... locators) {
        return new WebDriverWait(driver, timeout).until(currentDriver -> {
            String text = readFirstVisibleText(locators);
            return text.isBlank() ? null : text;
        });
    }

    String waitForTextChange(Duration timeout, String previousText, By... locators) {
        return new WebDriverWait(driver, timeout).until(currentDriver -> {
            String currentText = readFirstVisibleText(locators);
            if (currentText.isBlank() || Objects.equals(currentText, previousText)) {
                return null;
            }
            return currentText;
        });
    }

    String readInputValue(By... locators) {
        WebElement element = findFirstDisplayed(driver, locators);
        if (element == null) {
            return "";
        }
        return normalizeText(element.getAttribute("value"));
    }

    String readFirstVisibleText(By... locators) {
        WebElement element = findFirstDisplayed(driver, locators);
        if (element == null) {
            return "";
        }
        return normalizeText(element.getText());
    }

    void clearAndType(WebElement element, String value) {
        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();
        element.sendKeys(selectAllShortcut());
        element.sendKeys(Keys.DELETE);
        element.clear();
        element.sendKeys(value);
    }

    void safeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (ElementClickInterceptedException | TimeoutException | StaleElementReferenceException exception) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }

    private ExpectedCondition<Boolean> pageIsFullyLoaded() {
        return currentDriver -> {
            assert currentDriver != null;
            return Objects.equals(
                    "complete",
                    ((JavascriptExecutor) currentDriver).executeScript("return document.readyState")
            );
        };
    }

    private WebElement findFirstDisplayed(WebDriver currentDriver, By... locators) {
        for (By locator : locators) {
            List<WebElement> elements = currentDriver.findElements(locator);
            for (WebElement element : elements) {
                try {
                    if (element.isDisplayed()) {
                        return element;
                    }
                } catch (StaleElementReferenceException ignored) {
                }
            }
        }
        return null;
    }

    private String selectAllShortcut() {
        if (System.getProperty("os.name", "").toLowerCase().contains("mac")) {
            return Keys.chord(Keys.COMMAND, "a");
        }
        return Keys.chord(Keys.CONTROL, "a");
    }

    private String normalizeText(String value) {
        if (value == null) {
            return "";
        }
        return value.trim().replace('\n', ' ');
    }
}
