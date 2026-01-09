import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class SeleniumDocsTest {
    WebDriver driver;
    SeleniumDocsPage page;

    @BeforeTest
    public void setUp() {
        driver = new ChromeDriver();
        page = new SeleniumDocsPage(driver);
    }

    @Test
    public void SeleniumDocsPage () throws MalformedURLException {
        driver.get("https://www.jetbrains.com/help/idea/ai-assistant-in-jetbrains-ides.html");
        page.acceptCookies();
        page.clickTestingLink();
        page.clickSeleniumHeader();
        page.verifyAddElementsText();
    }

    @Test
    public void ClickJUnitLink () throws MalformedURLException {
        driver.get("https://www.jetbrains.com/help/idea/ai-assistant-in-jetbrains-ides.html");
        page.acceptCookies();
        page.clickTestingLink();
        page.clickJUnit();
        page.verifyJUnitText();
    }

    @Test
    public void SearchForKeyword () throws MalformedURLException {
        driver.get("https://www.jetbrains.com/help/idea/ai-assistant-in-jetbrains-ides.html");
        page.acceptCookies();
        page.clickSearchIcon();
        page.typeKeyword();
        page.clickSearchResult();
        page.verifyDebugToolText();
    }

}
