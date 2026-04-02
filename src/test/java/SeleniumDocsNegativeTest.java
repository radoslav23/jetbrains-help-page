import org.openqa.selenium.HasCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class SeleniumDocsNegativeTest extends BaseTest{

    SeleniumDocsNegativePage page;
    private static final String baseUrl = "https://www.jetbrains.com/help/idea/getting-started.html";
    private static final String brokenUrl = "https://www.jetbrains.com/help/idea/non-existent-page.html";

    @BeforeMethod
    public void initPage() {
        driver.get(baseUrl);
        driverReady = true;
        page = new SeleniumDocsNegativePage(driver);
        page.acceptCookies();
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

    @Test(groups = "local-only")
    //The JetBrains feedback widget behaves differently in headless environments.
    //The button’s enabled state depends on client-side validation that does not
    //fire consistently in CI due to timing, hydration delays, and animation-driven
    //DOM updates. As a result, the button remains disabled and the test fails in headless environments
    //intermittently despite correct locators and event simulation.
    //The test works reliably in local browsers, so it is kept for functional
    //coverage but excluded from CI to avoid false negatives and pipeline noise.
    public void voteNegativeFeedback () {
        page.voteWithNegative();
        Assert.assertTrue(page.feedbackFormVisibility(), "Feedback form was not visible");
        Assert.assertEquals(page.feedbackFormHeading(), "How can we improve?");
        Assert.assertTrue(page.textFieldDisplayed(), "Text field was not displayed");
        Assert.assertEquals(page.textFieldPlaceholderDisplayed(), "Tell us what you think would make this page better");
        Assert.assertTrue(page.nameFieldDisplayed(), "Name field was not displayed");
        Assert.assertEquals(page.nameFieldPlaceholderDisplayed(), "Name");
        Assert.assertTrue(page.emailFieldDisplayed(), "Email field was not displayed");
        Assert.assertEquals(page.emailFieldPlaceholderDisplayed(), "Email address");
        Assert.assertTrue(page.submitButtonDisplayed(), "Submit button was not displayed");
        Assert.assertTrue(page.submitButtonDisabled(), "Submit button was not disabled");
        page.typeFeedback();
        Assert.assertTrue(page.submitButtonEnabled(), "Submit button was not enabled");
        page.submitFeedback();
        Assert.assertTrue(page.feedbackFormInvisibility(), "Feedback form still visible when shouldn't");
        System.out.println("CI? " + System.getenv("GITHUB_ACTIONS"));
        System.out.println("Window size: " + driver.manage().window().getSize());
        System.out.println("Chrome version: " + ((HasCapabilities) driver).getCapabilities().getBrowserVersion());
        System.out.println("Headless: " + ((HasCapabilities) driver).getCapabilities().is("headless"));
    }

    @Test
    public void typeInvalidEmail () {
        page.voteWithNegative();
        page.typeInvalidEmail();
        Assert.assertTrue(page.isTheEmailInvalid());
    }
}