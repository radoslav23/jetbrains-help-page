import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;

public class TestListener implements ITestListener {

    private static ExtentReports extent;
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Override
    public void onStart(ITestContext context) {
        ExtentSparkReporter reporter = new ExtentSparkReporter("extent-report.html");
        extent = new ExtentReports();
        extent.attachReporter(reporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName());
        test.set(extentTest);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.get().fail(result.getThrowable());

        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            BaseTest base = (BaseTest) testClass;
            File screenshot = base.takeScreenshot(result.getName());
            if (screenshot != null) {
                test.get().addScreenCaptureFromPath(screenshot.getAbsolutePath());
            }
        }
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.get().pass("Test passed");
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}