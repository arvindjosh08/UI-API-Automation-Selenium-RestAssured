package com.aarushi.automation.common.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public synchronized static ExtentReports initReport(String reportName) {
        if (extent == null) {
            String reportPath = System.getProperty("user.dir") + "/target/" + reportName + ".html";
            ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
            spark.config().setDocumentTitle(reportName);
            spark.config().setReportName(reportName);

            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }

    public static synchronized ExtentReports getExtent() {
        return extent;
    }

    public static synchronized void flush() {
        if (extent != null) {
            extent.flush();
        }
    }
}