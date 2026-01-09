import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SeleniumDocsPage {
    WebDriver driver;
    WebDriverWait wait;
    JavascriptExecutor js;
//These fields define the tools my page object uses to interact with the browser. driver is the main controller,
// wait helps synchronize actions with page state, and js allows direct JavaScript execution for advanced control

    By acceptAllButton = By.xpath("//div[@id='ch2-dialog']//button[.='Accept All']");
    By testingLink = By.xpath("//a[normalize-space()='Testing']");
    By seleniumHeader = By.xpath("//h3[normalize-space()='Selenium']");
    By addElementHeading = By.xpath("//span[normalize-space()='Add elements to code']");
    By jUnitLink = By.xpath("//h3[normalize-space()='JUnit']");
    By getStartedJUnit = By.xpath("//span[contains(text(),'Get started with JUnit')]");
    By searchIcon = By.xpath("//button[@title='Search']//*[name()='svg']");
    By searchField = By.xpath("//input[@placeholder='Ctrl+K for advanced search']");
    By searchResult = By.partialLinkText("Debug tool window");
    By debugToolHeading = By.cssSelector("h1#Debug_Tool_Window\\.topic > .title__content");
//here locators are defined

    public SeleniumDocsPage(WebDriver driver) {
//This constructor initializes the page object with the tools it needs to interact with the browser
//The constructor sets up the browser control, wait mechanism, and JavaScript execution capability.
//It ensures the page object is ready to interact with the web page reliably and flexibly
        this.driver = driver;
        //Stores the passed-in WebDriver instance so the class can use it to control the browser
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        //Sets up an explicit wait with a 2-second timeout. This helps synchronize actions with dynamic page content
        this.js = (JavascriptExecutor) driver;
        //Casts the WebDriver to a JavascriptExecutor so you can run JavaScript commands when needed
    }
    //below are methods with page interactions are defined. First one performs a single action:
    // clicking the "Accept All" button in a cookie consent dialog
    public void acceptCookies() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(acceptAllButton));

            driver.findElement(acceptAllButton).click();
        } catch (Exception e) {
            // Fallback: try clicking via JavaScript if normal click fails
            WebElement button = driver.findElement(acceptAllButton);
            js.executeScript("arguments[0].click();", button);
        }
    }

    public void  clickTestingLink(){
        driver.findElement(testingLink).click();
    }

    public void  clickSeleniumHeader(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(seleniumHeader));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void verifyAddElementsText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(addElementHeading));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Assert.assertEquals(element.getText(), "Add elements to code");
    }

    public void clickJUnit() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(jUnitLink));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public void verifyJUnitText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(getStartedJUnit));
        Assert.assertEquals(element.getText(), "Get started with JUnit");
    }

    public void clickSearchIcon() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchIcon));
        element.click();
    }

    public void typeKeyword() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        element.sendKeys("Debug tool window");
    }

    public void clickSearchResult() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchResult));
        element.click();
    }

    public void verifyDebugToolText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(debugToolHeading));
        Assert.assertEquals(element.getText(), "Debug tool window");
    }
}
