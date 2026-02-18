//package org.example.seleniumExample;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class SeleniumDocsTestNegative {

    WebDriver driver;
    SeleniumDocsPageNegative page;

    private static final String baseUrl = "https://www.jetbrains.com/help/idea/ai-assistant-in-jetbrains-ides.html";
    @BeforeTest
    public void setup() {
        driver = new ChromeDriver();
        page = new SeleniumDocsPageNegative(driver);
        driver.get(baseUrl);

    }

    @AfterTest
    public void teardown() {
        if (driver != null) {
            //driver.quit();
        }
    }

    @Test
    public void testBrokenLinkNavigation() {
        page.goToBrokenLink();
        String title = page.getPageTitle();
        Assert.assertTrue(title.contains("Page Not Found") || title.contains("404"));
    }

    @Test
    public void testMissingElementHandling() {
        driver.get("https://www.jetbrains.com/help/idea/getting-started.html");
        Assert.assertFalse(page.isGhostElementPresent(), "Ghost element should not exist");
    }

    @Test
    public void testInvalidSearchInput() {
        driver.get("https://www.jetbrains.com/help/idea/getting-started.html");
        SeleniumDocsPage docsPage = new SeleniumDocsPage(driver);
        docsPage.acceptCookies();
        docsPage.clickSearchIcon();
        page.searchInvalidKeyword();
        Assert.assertTrue(page.searchInvalidKeyword().contains("We’re sorry! We couldn’t find results"),
                "Expected 'no results' message");
    }

    @Test
    public void testCookieBannerAbsent() {
        driver.get("https://www.jetbrains.com/help/idea/getting-started.html");
        Assert.assertTrue(page.isCookieBannerMissing(), "Cookie banner should be missing");
    }

    @Test
    public void testElementTimeout() {
        driver.get("https://www.jetbrains.com/help/idea/getting-started.html");
        Assert.assertFalse(page.waitForSlowElement(), "Slow element should not appear");
    }
    @Test
    public void VoteNegativeFeedback () {
        //driver.get("https://www.jetbrains.com/help/idea/ai-assistant-in-jetbrains-ides.html");
        SeleniumDocsPage docsPage = new SeleniumDocsPage(driver);
        docsPage.acceptCookies();
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
}