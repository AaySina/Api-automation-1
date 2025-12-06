package Listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentManager;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExtentTestNGTestListener implements ITestListener {
    private static ExtentTest test;

    @Override
    public void onTestStart(ITestResult result) {
        test = ExtentManager.getInstance().createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test failed: " + result.getThrowable());

        String screenshotDir = "reports-output/screenshots/";
        new File(screenshotDir).mkdirs();

        String filePath = screenshotDir + result.getMethod().getMethodName() + "_failure.json";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("Error: " + result.getThrowable() + "\n");
            if (result.getThrowable() != null) {
                writer.write("StackTrace: " + result.getThrowable().toString());
            }
            writer.flush();
            test.addScreenCaptureFromPath(filePath);
        } catch (IOException e) {
            test.log(Status.WARNING, "Failed to save screenshot evidence: " + e.getMessage());
        }
    }

    @Override
    public void onFinish(ITestContext context) {
        ExtentManager.getInstance().flush();
    }
}