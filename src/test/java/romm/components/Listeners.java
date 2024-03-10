package romm.components;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import romm.utils.CustomExtentReporter;

public class Listeners extends BaseTest implements ITestListener {
    private ExtentTest test;
    private ExtentReports extentReporter = CustomExtentReporter.getReport();
    private ThreadLocal threadLocal = new ThreadLocal<>();

    @Override
    public void onTestStart(ITestResult result) {
        test = extentReporter.createTest(result.getMethod().getMethodName());
        threadLocal.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ITestListener.super.onTestSuccess(result);
        test.log(Status.PASS, "Test pass");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ITestListener.super.onTestFailure(result);
        test.fail(result.getThrowable());
        WebDriver currentTestDriver = null;
        try {
            currentTestDriver = (WebDriver) result.getTestClass().getRealClass()
                .getField("driver").get(result.getInstance());
        } catch (Exception e) {
            e.printStackTrace();
        }
        String filePath = getScreeShoot(result.getMethod().getMethodName(), currentTestDriver);
        test.addScreenCaptureFromPath(filePath, result.getMethod().getMethodName());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ITestListener.super.onTestSkipped(result);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        ITestListener.super.onTestFailedWithTimeout(result);
    }

    @Override
    public void onStart(ITestContext context) {
        ITestListener.super.onStart(context);
    }

    @Override
    public void onFinish(ITestContext context) {
       // ITestListener.super.onFinish(context);
        extentReporter.flush();
    }
}
