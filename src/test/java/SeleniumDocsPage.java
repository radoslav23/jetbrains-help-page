import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class SeleniumDocsPage extends BasePage {

    public SeleniumDocsPage(WebDriver driver) {
        super(driver);
    }

    //below locators are defined
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

    //below methods for interactions with the page are defined
    public void acceptCookies() {
        List<WebElement> buttons = driver.findElements(acceptAllButton);

        if (!buttons.isEmpty()) {
            try {
                buttons.get(0).click();
            } catch (Exception e) {
                js.executeScript("arguments[0].click();", buttons.get(0));
            }
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
    public String verifyVotedYes(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(thanksForFeedback));
        return element.getText();
    }
}
