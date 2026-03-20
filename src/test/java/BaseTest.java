import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseTest {
    // Protected so child page classes can access driver and driverReady,
    // while keeping them hidden from tests and external classes.
    protected WebDriver driver;
    protected boolean driverReady = false;

    public File takeScreenshot(String testName) {
        //Takes a screenshot when a test fails and stores it in the screenshots folder with a timestamped filename
        if (driver == null) {
            System.out.println("Driver not ready, screenshot skipped.");
            return null;
        }

        try {
            File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Ensure folder exists
            File screenshotsDir = new File("screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdirs();
            }

            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            File dest = new File(screenshotsDir, testName + "_" + timestamp + ".png");

            Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot saved: " + dest.getAbsolutePath());

        } catch (Exception e) {
            System.out.println("Failed to take screenshot: " + e.getMessage());
        }
        return null;
    }
}
