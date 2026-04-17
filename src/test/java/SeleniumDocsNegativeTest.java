import org.openqa.selenium.HasCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestListener.class)
public class SeleniumDocsNegativeTest extends BaseTest{

    SeleniumDocsNegativePage page;
    CommonComponents component;

    private static final String baseUrl = "https://www.jetbrains.com/help/idea/getting-started.html";
    private static final String brokenUrl = "https://www.jetbrains.com/help/idea/non-existent-page.html";

    @BeforeMethod
    public void initPage() {
        driver.get(baseUrl);
        driverReady = true;
        page = new SeleniumDocsNegativePage(driver);
        component = new CommonComponents(driver);
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
    // The button’s enabled state is unreliable in CI due to delayed DOM updates.
    // Works locally but excluded from CI to avoid false failures.
    public void voteNegativeFeedback () {
        component.clickFeedbackPanel();
        page.voteWithNegative();
        Assert.assertTrue(component.feedbackFormVisibility(), "Feedback form was not visible");
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
        page.typeName();
        page.typeEmail();
        Assert.assertTrue(page.submitButtonEnabled(), "Submit button was not enabled");
        page.submitFeedback();
        Assert.assertTrue(component.feedbackFormInvisibility(), "Feedback form still visible when shouldn't");
    }

    @Test
    public void typeInvalidEmail () {
        component.clickFeedbackPanel();
        page.voteWithNegative();
        page.typeInvalidEmail();
        Assert.assertTrue(page.isTheEmailInvalid());
    }
}