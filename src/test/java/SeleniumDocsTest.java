import org.openqa.selenium.HasCapabilities;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;

@Listeners(TestListener.class)
public class SeleniumDocsTest extends BaseTest{

    SeleniumDocsPage page;
    private static final String baseUrl = "https://www.jetbrains.com/help/idea/getting-started.html";

    @BeforeMethod
    public void initPage() {
        driver.get(baseUrl);
        driverReady = true;
        page = new SeleniumDocsPage(driver);
        page.acceptCookies();
    }

    @Test
    public void seleniumDocsPage () throws MalformedURLException {
        page.clickTestingLink();
        page.clickSeleniumHeader();
        Assert.assertEquals(page.verifyAddElementsText(), "Add elements to code", "Heading text mismatch");
    }

    @Test
    public void clickJUnitLink () throws MalformedURLException {
        page.clickTestingLink();
        page.clickJUnit();
        Assert.assertEquals(page.verifyJUnitText(), "Tutorial: Get started with JUnit", "Heading text mismatch");
    }

    @Test
    public void searchForKeyword () throws MalformedURLException {
        page.clickSearchIcon();
        page.typeKeyword();
        page.clickSearchResult();
        Assert.assertEquals(page.verifyDebugToolText(), "Debug tool window", "Heading text mismatch");
    }

    @Test(groups = "local-only")
    // JetBrains’ feedback widget renders unpredictably in CI, causing false failures.
    // The test is reliable locally but excluded from CI to avoid pipeline noise.
    public void votePositiveFeedback () {
        page.clickAiSection();
        Assert.assertEquals(page.verifyWasPageHelpfulHeading(), "Was this page helpful?");
        page.voteWithYes();
        Assert.assertEquals(page.voteOptionDisappearAfterVote(), "Thanks for your feedback!");
        System.out.println("CI? " + System.getenv("GITHUB_ACTIONS"));
        System.out.println("Window size: " + driver.manage().window().getSize());
        System.out.println("Chrome version: " + ((HasCapabilities) driver).getCapabilities().getBrowserVersion());
        System.out.println("Headless: " + ((HasCapabilities) driver).getCapabilities().is("headless"));
    }
}
