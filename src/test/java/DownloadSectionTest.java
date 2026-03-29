import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class DownloadSectionTest extends BaseTest {

    DownloadSectionPage page;
    private static final String baseUrl = "https://www.jetbrains.com/help/idea/getting-started.html";

    //@BeforeMethod
    public void initPage(String url) {
        //driver = new ChromeDriver();
        //driver.get(baseUrl);
        driver.get(url);
        driverReady = true;
        page = new DownloadSectionPage(driver);
        page.acceptCookies();
        //page.clickGetIntellijIdeaButton();
    }

    @BeforeMethod
    public void openWindowsDownloadPage() {
        initPage("https://www.jetbrains.com/idea/download/?section=windows");
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
        //Assert.assertEquals(page.getCurrentUrl(), "https://www.jetbrains.com/idea/download/?section=windows");
        Assert.assertTrue(page.prisingButtonDisplayed());
        Assert.assertTrue(page.downloadButtonDisplayed());
        Assert.assertTrue(page.leadingIdeHeadingVisibility());
    }

    @Test
    public void exploreWindowsDownloadOptions() {
        Assert.assertEquals(
                page.getCurrentUrl(),
                "https://www.jetbrains.com/idea/download/?section=windows"
        );
        page.clickWindowsDownloadDropdown();
        Assert.assertTrue(page.windowsZipVisibility());
        Assert.assertTrue(page.windowsExeVisibility());
        Assert.assertTrue(page.windowsExeArm64Visibility());
    }

    @Test
    public void clickMacOsDownloadDropdown() {
        page.clickMacOsButton();
        Assert.assertEquals(
                page.getCurrentUrl(),
                "https://www.jetbrains.com/idea/download/?section=mac"
        );
        Assert.assertTrue(page.macOsDownloadButtonVisible());
        page.macOsDownloadButtonClick();
        Assert.assertTrue(page.dmgAppleSiliconVisibility());
        Assert.assertTrue(page.dmgIntelVisibility());
    }

    @Test
    public void clickLinuxDownloadDropdown() {
        page.clickLinuxButton();
        Assert.assertEquals(
                page.getCurrentUrl(),
                "https://www.jetbrains.com/idea/download/?section=linux"
        );
        Assert.assertTrue(page.linuxDownloadButtonVisible());
        page.linuxDownloadButtonClick();
        Assert.assertTrue(page.tarLinuxVisibility());
        Assert.assertTrue(page.tarLinuxArm64Visibility());
    }
}
