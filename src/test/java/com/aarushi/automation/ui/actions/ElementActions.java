
package com.aarushi.automation.ui.actions;
import com.aarushi.automation.ui.base.DriverFactory;
import com.aarushi.automation.ui.utilities.ConfigReader;
import com.aarushi.automation.ui.utilities.ScreenshotUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.bidi.browsingcontext.Locator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Driver;
import java.time.Duration;
import java.util.List;


public class ElementActions {

    private WebDriver driver;
    private int defaultTimeout;
    private static final Logger log = LogManager.getLogger(ElementActions.class);

    public ElementActions(WebDriver driver) {
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
     * Get list of web elements safely
     */
    public List<WebElement> safelyFindElements(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    /**
     * Get web elements safely
     */
    public WebElement safelyFindElement(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(defaultTimeout)).until(ExpectedConditions.visibilityOfElementLocated(locator));
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
            log.info("Clicked on element: "+elementName);

        } catch (ElementClickInterceptedException e) {
            //ExtentLogger.warning("Standard click failed on: " + elementName + ". Trying JS click.");
            jsClick(element, elementName);
        }
        catch (Exception e) {
            handleFailure("Click failed on: " + elementName, e);
        }
    }

    /**
     * Clicks on element with custom timeout and locator as method parameter
     */
    public void click(By locator, String elementName, int timeoutInSeconds) {
        try {
            getWait(timeoutInSeconds).until(ExpectedConditions.elementToBeClickable(locator)).click();
            log.info("Clicked on element: "+elementName);
            //ExtentLogger.pass("Clicked on: " + elementName);
        } catch (ElementClickInterceptedException e) {
            //ExtentLogger.warning("Standard click failed on: " + elementName + ". Trying JS click.");
            WebElement element=driver.findElement(locator);
            jsClick(element, elementName);
        }  catch (StaleElementReferenceException e){
            driver.findElement(locator).click();
        }
        catch (Exception e) {
            handleFailure("Click failed on: " + elementName, e);
        }
    }

    /**
     * Send keys with default timeout and web element parameter
     */
    public void sendKeys(WebElement element, String text, String elementName) {
        sendKeys(element, text, elementName, defaultTimeout);
    }

    /**
     * Send keys with web element parameter
     */
    public void sendKeys(WebElement element, String text, String elementName, int timeoutInSeconds) {
        try {
            WebElement el = getWait(timeoutInSeconds).until(ExpectedConditions.visibilityOf(element));
            el.clear();
            el.sendKeys(text);
            log.info("Entered text "+ text +" into "+elementName);
            //ExtentLogger.pass("Entered text '" + text + "' in: " + elementName);
        } catch (Exception e) {
            handleFailure("Failed to send text to: " + elementName, e);
        }
    }

    /**
     * Send keys with locator parameter
     */
    public void sendKeys(By locator, String text, String elementName, int timeoutInSeconds) {
        try {
            WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(timeoutInSeconds));
            WebElement returnElement=wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
            returnElement.clear();
            returnElement.sendKeys(text);
            log.info("Entered text "+ text +" into "+elementName);
            //ExtentLogger.pass("Entered text '" + text + "' in: " + elementName);
        }
        catch (StaleElementReferenceException e){
            driver.findElement(locator).sendKeys(text);
        }
        catch (Exception e) {
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
            log.info("Fetched text from " + elementName+ " : "+text);
            //ExtentLogger.pass("Fetched text from: " + elementName + " => " + text);
            return text;
        } catch (Exception e) {
            handleFailure("Failed to get text from: " + elementName, e);
            return "";
        }
    }

    public String getText(By locator, String elementName) {
        try {
            String text = getWait(defaultTimeout).until(ExpectedConditions.visibilityOfElementLocated(locator)).getText();
            //ExtentLogger.pass("Fetched text from: " + elementName + " => " + text);
            log.info("Fetched text from " + elementName+ " : "+text);
            return text;
        } catch (StaleElementReferenceException e) {
           return driver.findElement(locator).getText();
        }
        catch (Exception e){
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
     * Failure Handler
     */
    private void handleFailure(String message, Exception e) {
        //ExtentLogger.fail(message);
        ScreenshotUtils.captureScreenshotBase64(message.replace(" ", "_"));

        throw new RuntimeException(message, e);
    }
}



