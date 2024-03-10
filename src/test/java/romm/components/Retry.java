package romm.components;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer {
    private static final int MAX_RETRY_ATTEMPT = 2;
    private int retryCounter = 0;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (retryCounter < MAX_RETRY_ATTEMPT) {
            retryCounter++;
            return true;
        }
        return false;
    }
}
