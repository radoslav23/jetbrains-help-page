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
    public void votePositiveFeedback () {
        page.clickAiSection();
        Assert.assertEquals(page.verifyWasPageHelpfulHeading(), "Was this page helpful?");
        page.voteWithYes();
        Assert.assertEquals(page.verifyVotedYes(), "Thanks for your feedback!");
    }
}
