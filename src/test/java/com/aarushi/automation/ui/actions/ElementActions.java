
package com.aarushi.automation.ui.actions;
import com.aarushi.automation.ui.base.DriverFactory;
import com.aarushi.automation.ui.utilities.ConfigReader;
import com.aarushi.automation.ui.utilities.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;


public class ElementActions {

    private WebDriver driver;
    private int defaultTimeout;
    private static final Logger log = LogManager.getLogger(ElementActions.class);

    public ElementActions() {
        this.driver = DriverFactory.getDriver();
        this.defaultTimeout = getTimeoutFromConfig();
    }

    /**
     * Reads default timeout from config.properties or uses fallback
     */
    private int getTimeoutFromConfig() {
        try {
            String timeoutStr = ConfigReader.get("explicit.wait.timeout");
            if (timeoutStr != null && !timeoutStr.isEmpty()) {
                return Integer.parseInt(timeoutStr);
            }
        } catch (Exception e) {
            //ExtentLogger.warning("Failed to read timeout from config, using default: 10s");
        }
        return 10; // default fallback
    }

    /**
     * Get WebDriverWait for given timeout
     */
    private WebDriverWait getWait(int timeoutInSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
    }

    /**
     * Clicks on element with default timeout
     */
    public void click(WebElement element, String elementName) {
        click(element, elementName, defaultTimeout);
    }

    /**
     * Clicks on element with custom timeout
     */
    public void click(WebElement element, String elementName, int timeoutInSeconds) {
        try {
            getWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(element)).click();
            //ExtentLogger.pass("Clicked on: " + elementName);
        } catch (ElementClickInterceptedException e) {
            //ExtentLogger.warning("Standard click failed on: " + elementName + ". Trying JS click.");
            jsClick(element, elementName);
        } catch (Exception e) {
            handleFailure("Click failed on: " + elementName, e);
        }
    }

    /**
     * Send keys with default timeout
     */
    public void sendKeys(WebElement element, String text, String elementName) {
        sendKeys(element, text, elementName, defaultTimeout);
    }

    /**
     * Send keys with custom timeout
     */
    public void sendKeys(WebElement element, String text, String elementName, int timeoutInSeconds) {
        try {
            WebElement el = getWait(timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
            el.clear();
            el.sendKeys(text);
            //ExtentLogger.pass("Entered text '" + text + "' in: " + elementName);
        } catch (Exception e) {
            handleFailure("Failed to send text to: " + elementName, e);
        }
    }

    /**
     * Get text with default timeout
     */
    public String getText(WebElement element, String elementName) {
        return getText(element, elementName, defaultTimeout);
    }

    /**
     * Get text with custom timeout
     */
    public String getText(WebElement element, String elementName, int timeoutInSeconds) {
        try {
            String text = getWait(timeoutInSeconds).until(ExpectedConditions.visibilityOf(element)).getText();
            //ExtentLogger.pass("Fetched text from: " + elementName + " => " + text);
            return text;
        } catch (Exception e) {
            handleFailure("Failed to get text from: " + elementName, e);
            return "";
        }
    }

    /**
     * Check if element is displayed
     */
    public boolean isDisplayed(WebElement element, String elementName) {
        try {
            boolean displayed = getWait(defaultTimeout).until(ExpectedConditions.visibilityOf(element)).isDisplayed();
            //ExtentLogger.pass(elementName + " is displayed: " + displayed);
            return displayed;
        } catch (TimeoutException e) {
            //ExtentLogger.info(elementName + " is NOT visible.");
            return false;
        }
    }

    /**
     * JS Click fallback
     */
    private void jsClick(WebElement element, String elementName) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
            //ExtentLogger.pass("JS clicked on: " + elementName);
        } catch (Exception e) {
            handleFailure("JS click failed on: " + elementName, e);
        }
    }

    /**
     * Retry click with custom timeout
     */
    public void safeClick(WebElement element, String elementName, int retryCount) {
        int attempts = 0;
        while (attempts < retryCount) {
            try {
                click(element, elementName);
                return;
            } catch (Exception e) {
                //ExtentLogger.warning("Retrying click on: " + elementName + " | Attempt: " + (attempts + 1));
            }
            attempts++;
        }
        handleFailure("All retry attempts failed for: " + elementName, null);
    }

    /**
     * Failure handler
     */
    private void handleFailure(String message, Exception e) {
        //ExtentLogger.fail(message);
        ScreenshotUtils.captureScreenshotBase64(message.replace(" ", "_"));

        throw new RuntimeException(message, e);
    }
}



