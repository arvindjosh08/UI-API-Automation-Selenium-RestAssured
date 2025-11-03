package com.aarushi.automation.ui.utilities;

import com.aarushi.automation.ui.base.DriverFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {


    /**
     * Capture screenshot as Base64 string
     * Thread-safe and recommended for parallel execution
     */
    public static String captureScreenshotBase64(String testName) {
        WebDriver driver = DriverFactory.getDriver();
        if (driver == null) return null;

        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
    }


    public static String captureScreenshot(String testName) {
        WebDriver driver=DriverFactory.getDriver();
        String screenshotDir = RunContext.getScreenshotsDir();
        // Unique filename using timestamp + thread id
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date());
        String threadId = String.valueOf(Thread.currentThread().getId());
        String fileName = testName + "_T" + threadId + "_" + timestamp + ".png";

        String filePath = screenshotDir + File.separator + fileName;
        System.out.println(filePath);

        // Capture and save screenshot
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            Files.copy(srcFile.toPath(), new File(filePath).toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return filePath;
    }


}
