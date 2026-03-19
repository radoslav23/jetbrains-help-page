import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        if (testClass instanceof BaseTest) {
            BaseTest base = (BaseTest) testClass;
            base.takeScreenshot(result.getName());
        }
    }
}
