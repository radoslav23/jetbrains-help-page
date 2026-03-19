import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.net.MalformedURLException;

@Listeners(TestListener.class)
public class SeleniumDocsTest extends BaseTest{

    SeleniumDocsPage page;

    private static final String baseUrl = "https://www.jetbrains.com/help/idea/getting-started.html";
    @BeforeMethod
    public void initPage() {
        driver = new ChromeDriver();
        driver.get(baseUrl);
        driverReady = true;
        page = new SeleniumDocsPage(driver);
        page.acceptCookies();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void SeleniumDocsPage () throws MalformedURLException {
        page.clickTestingLink();
        page.clickSeleniumHeader();
        page.verifyAddElementsText();
        Assert.assertEquals(page.verifyAddElementsText(), "Add elements to code", "Heading text mismatch"
        );

    }

    @Test
    public void ClickJUnitLink () throws MalformedURLException {
        page.clickTestingLink();
        page.clickJUnit();
        page.verifyJUnitText();
        Assert.assertEquals(page.verifyJUnitText(), "Tutorial: Get started with JUnit", "Heading text mismatch");
    }

    @Test
    public void SearchForKeyword () throws MalformedURLException {
        page.clickSearchIcon();
        page.typeKeyword();
        page.clickSearchResult();
        page.verifyDebugToolText();
        Assert.assertEquals(page.verifyDebugToolText(), "Debug tool window", "Heading text mismatch");
    }

    @Test
    public void VotePositiveFeedback () {
        page.clickAiSection();
        Assert.assertEquals(page.verifyWasPageHelpfulHeading(), "Was this page helpful?");
        page.voteWithYes();
        Assert.assertEquals(page.verifyVotedYes(), "Thanks for your feedback!");
    }
}
