import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class DownloadSectionTest extends BaseTest {

    DownloadSectionPage page;
    private static final String baseUrl = "https://www.jetbrains.com/help/idea/getting-started.html";

    @BeforeMethod
    public void initPage() {
        //driver = new ChromeDriver();
        driver.get(baseUrl);
        driverReady = true;
        page = new DownloadSectionPage(driver);
        page.acceptCookies();
        page.clickGetIntellijIdeaButton();
    }

//    @AfterTest
//    public void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

//    @BeforeMethod
//    public void setUpDownloadPage() {
//        page.acceptCookies();
//        page.clickGetIntellijIdeaButton();
//    }


    @Test
    public void navigateIntelliJDownloadPage() {
        Assert.assertEquals(page.getCurrentUrl(), "https://www.jetbrains.com/idea/download/?section=windows");
        Assert.assertTrue(page.prisingButtonDisplayed());
        Assert.assertTrue(page.downloadButtonDisplayed());
        Assert.assertTrue(page.leadingIdeHeadingVisibility());
    }

    @Test
    public void exploreWindowsDownloadOptions() {
        page.clickWindowsDownloadDropdown();
        Assert.assertTrue(page.windowsZipVisibility());
        Assert.assertTrue(page.windowsExeVisibility());
        Assert.assertTrue(page.windowsExeArm64Visibility());
    }

    @Test
    public void clickMacOsDownloadDropdown() {
        page.clickMacOsButton();
        Assert.assertTrue(page.macOsDownloadButtonVisible());
        page.macOsDownloadButtonClick();
        Assert.assertTrue(page.dmgAppleSiliconVisibility());
        Assert.assertTrue(page.dmgIntelVisibility());
    }

    @Test
    public void clickLinuxDownloadDropdown() {
        page.clickLinuxButton();
        Assert.assertTrue(page.linuxDownloadButtonVisible());
        page.linuxDownloadButtonClick();
        Assert.assertTrue(page.tarLinuxVisibility());
        Assert.assertTrue(page.tarLinuxArm64Visibility());
    }
}
