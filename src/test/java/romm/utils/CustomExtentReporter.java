package romm.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class CustomExtentReporter {
    public static ExtentReports getReport() {
        String path = "reports/index.html";
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(path);
        extentSparkReporter.config().setReportName("Demo Test");
        extentSparkReporter.config().setDocumentTitle("NZZ");

        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("NZZ", "QA Test");
        extentReports.createTest(path);
        return extentReports;
    }
}
