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

    @Test
    //The JetBrains feedback widget renders differently in headless environments.
    //The text node appears with a delay due to animations and DOM rehydration,
    //causing the test to fail intermittently even though the logic and locator are correct.
    //The test remains valid locally and is kept for functional coverage,
    //but it is excluded from CI to avoid false negatives and pipeline noise
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
