package com.aarushi.automation.ui.utilities;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class RunContext {

    private static String runFolder;
    private static String reportsDir;
    private static String logsDir;
    private static String screenshotsDir;

    private RunContext() {}

    public static synchronized void initialize() {
        if (runFolder != null) return;

        String timestamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date());
        runFolder = System.getProperty("user.dir") + "/Reports/Run_" + timestamp;
        reportsDir = runFolder;
        logsDir = runFolder + "/Logs";
        screenshotsDir = runFolder + "/Screenshots";

        new File(logsDir).mkdirs();
        new File(screenshotsDir).mkdirs();

        System.setProperty("run.folder", runFolder);
        System.setProperty("log.folder", logsDir);
        System.setProperty("screenshot.folder", screenshotsDir);

        System.out.println("Run folder created: " + runFolder);
    }

    public static String getRunFolder() {
        return runFolder;
    }

    public static String getLogsDir() {
        return logsDir;
    }

    public static String getScreenshotsDir() {
        return screenshotsDir;
    }
}
