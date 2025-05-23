package Util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.commons.io.FileUtils;

public class TestUtil {

    private static ExtentReports extent;
    private static ExtentTest test;

    public static void initReport() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
    }

    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

    public static void createTest(String testName) {
        test = extent.createTest(testName);
    }

    public static void logPass(String message) {
        test.pass(message);
    }

    public static void logFail(WebDriver driver, String message) {
        String screenshotPath = takeScreenshot(driver, "failure");
        try {
            test.fail(message).addScreenCaptureFromPath(screenshotPath);
        } catch (Exception e) {
            test.fail("Screenshot capture failed: " + e.getMessage());
        }
    }

    public static String takeScreenshot(WebDriver driver, String name) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = "screenshots/" + name + "_" + timestamp + ".png";
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File dest = new File(filePath);
        try {
            FileUtils.copyFile(src, dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return filePath;
    }
}
