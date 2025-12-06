package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter("reports-output/extent-report.html");
            sparkReporter.config().setDocumentTitle("API Automation Report");
            sparkReporter.config().setReportName("Sport Reservation API Test Suite");

            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
        }
        return extent;
    }
}