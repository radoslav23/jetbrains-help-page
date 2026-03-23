import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class SeleniumDocsTestNegative extends BaseTest{

    SeleniumDocsPageNegative page;
    private static final String baseUrl = "https://www.jetbrains.com/help/idea/getting-started.html";
    private static final String brokenUrl = "https://www.jetbrains.com/help/idea/non-existent-page.html";

    //private static final String baseUrl = "https://www.jetbrains.com/help/idea/ai-assistant-in-jetbrains-ides.html";
    @BeforeMethod
    public void initPage() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driverReady = true;
        page = new SeleniumDocsPageNegative(driver);
        page.acceptCookies();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testBrokenLinkNavigation() {
        driver.get(brokenUrl);
        Assert.assertTrue(page.getPageTitle().contains("Page Not Found")
                || page.getPageTitle().contains("404"));
    }

    @Test
    public void testInvalidSearchInput() {
        SeleniumDocsPage docsPage = new SeleniumDocsPage(driver);
        docsPage.clickSearchIcon();
        page.typeInvalidKeyword();
        Assert.assertTrue(page.noResultForInvalidKeyword().contains("We’re sorry! We couldn’t find results"),
                "Expected 'no results' message");
    }

    @Test
    public void testCookieBannerMissing() {
        Assert.assertTrue(page.isCookieBannerMissing(), "Cookie banner should be missing");
    }

    @Test
    public void testSlowElementTimeout() {
        Assert.assertFalse(page.isSlowElementVisible(), "Slow element should not appear");
    }
    @Test
    public void voteNegativeFeedback () {
        page.voteWithNegative();
        Assert.assertTrue(page.feedbackFormVisibility());
        Assert.assertEquals(page.feedbackFormHeading(), "How can we improve?");
        Assert.assertTrue(page.textFieldDisplayed());
        Assert.assertEquals(page.textFieldPlaceholderDisplayed(), "Tell us what you think would make this page better");
        Assert.assertTrue(page.nameFieldDisplayed());
        Assert.assertEquals(page.nameFieldPlaceholderDisplayed(), "Name");
        Assert.assertTrue(page.emailFieldDisplayed());
        Assert.assertEquals(page.emailFieldPlaceholderDisplayed(), "Email address");
        Assert.assertTrue(page.submitButtonDisplayed());
        Assert.assertTrue(page.submitButtonDisabled());
        page.typeFeedback();
        Assert.assertTrue(page.submitButtonEnabled());
        page.submitFeedback();
        Assert.assertTrue(page.feedbackFormInvisibility());
    }

    @Test
    public void typeInvalidEmail () {
        page.voteWithNegative();
        page.typeInvalidEmail();
        Assert.assertTrue(page.isTheEmailInvalid());
    }
}