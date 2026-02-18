import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumDocsPage extends BasePage {
    //WebDriver driver;
    //WebDriverWait wait;
    //JavascriptExecutor js;
//These fields define the tools my page object uses to interact with the browser. driver is the main controller,
// wait helps synchronize actions with page state, and js allows direct JavaScript execution for advanced control

    private By acceptAllButton = By.xpath("//div[@id='ch2-dialog']//button[.='Accept All']");
    private By testingLink = By.xpath("//a[normalize-space()='Testing']");
    private By seleniumHeader = By.xpath("//h3[normalize-space()='Selenium']");
    private By addElementHeading = By.xpath("//span[normalize-space()='Add elements to code']");
    private By jUnitLink = By.xpath("//h3[normalize-space()='JUnit']");
    private By getStartedJUnit = By.xpath("//span[contains(text(),'Get started with JUnit')]");
    private By searchIcon = By.xpath("//button[@title='Search']//*[name()='svg']");
    private By searchField = By.xpath("//input[@placeholder='Ctrl+K for advanced search']");
    private By searchResult = By.partialLinkText("Debug tool window");
    private By debugToolHeading = By.cssSelector("h1#Debug_Tool_Window\\.topic > .title__content");
    private By aiSection = By.xpath("/html//div[@id='webhelp-root']/div/div[2]/nav//ul//a[@href='ai.html']");
    private By yesButton = By.xpath("/html//div[@id='webhelp-root']/div/div[2]/div//article//div[@class='wt-col-inline']/button[1]");
    private By wasPageHelpful = By.xpath("/html//div[@id='webhelp-root']/div/div[2]//article//div[.='Was this page helpful?']");
    private By thanksForFeedback = By.xpath("//p[@class='feedback__text']");
    //private By noButton = By.xpath("/html//div[@id='webhelp-root']/div/div[2]/div//article//div[@class='wt-col-inline']/button[2]");
    private By feedbackForm = By.xpath("//body[@class='app-is-rendered']/div[2]/div");
    private By howToImproveHeading = By.xpath("/html/body[@class='app-is-rendered']//form//legend[@class='feedback__legend']");
    
//here locators are defined

    public SeleniumDocsPage(WebDriver driver) {
        super(driver);
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

    public String verifyAddElementsText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(addElementHeading));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element.getText();
    }

    public void clickJUnit() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(jUnitLink));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public String verifyJUnitText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(getStartedJUnit));
        return element.getText();
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

    public String verifyDebugToolText() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(debugToolHeading));
        return element.getText();
    }

    public void clickAiSection() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(aiSection));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    public String verifyWasPageHelpfulHeading(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(wasPageHelpful));
        return element.getText();
    }

    public void voteWithYes(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(yesButton));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }
    public String verifyVotedYes(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(thanksForFeedback));
        return element.getText();
    }
}
