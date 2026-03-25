import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

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

    @BeforeClass
    public void setUpClass() {
        ChromeOptions options = new ChromeOptions();

        // Only headless in GitHub Actions
        if (System.getenv("GITHUB_ACTIONS") != null) {
            options.addArguments("--headless=new");
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
        }

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDownClass() {
        if (driver != null) {
            driver.quit();
        }
    }



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
