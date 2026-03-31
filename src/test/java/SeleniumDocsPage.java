import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class SeleniumDocsPage extends BasePage {

    public SeleniumDocsPage(WebDriver driver) {
        super(driver);
    }

    // Locators are private to enforce encapsulation and prevent tests from accessing elements directly
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
    //private By thanksForFeedback = By.xpath("//p[@class='feedback__text']");
    private By thanksForFeedback = By.xpath("//div[@data-test='feedback-left']//p[contains(@class,'feedback__text')]");


    //below methods for interactions with the page are defined. Page methods return data instead of asserting
    // so tests control validation and remain flexible and reusable
    public void clickTestingLink(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(testingLink));
        element.click();
    }

    //This method performs an action and does not return a value
    public void clickSeleniumHeader(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(seleniumHeader));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();
    }

    //This method scrolls to the element, and returns its text
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
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(debugToolHeading));
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
    public String voteOptionDisappearAfterVote(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(thanksForFeedback));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        return element.getText().trim();
    }
}
