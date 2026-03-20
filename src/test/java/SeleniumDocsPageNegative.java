import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class SeleniumDocsPageNegative extends BasePage {

    public SeleniumDocsPageNegative(WebDriver driver) {super(driver);}

    // Locators are private to enforce encapsulation and prevent tests from accessing elements directly
    private By searchBox = By.name("q");
    private By ghostElement = By.id("nonExistentElement");
    private By slowElement = By.id("slowElement");
    private By cookieBanner = By.id("cookie-banner");
    private By searchField = By.xpath("//input[@placeholder='Ctrl+K for advanced search']");
    private By noSearchResults = By.xpath("//h3[@class='quick-search__no-results-text']");
    private By noButton = By.xpath("/html//div[@id='webhelp-root']/div/div[2]/div//article//div[@class='wt-col-inline']/button[2]");
    private By feedbackForm = By.cssSelector("div[data-test='feedback-popup']");
    private By howToImproveHeading = By.xpath("/html/body[@class='app-is-rendered']//form//legend[@class='feedback__legend']");
    private By textField = By.cssSelector("textarea[data-test-id='feedback-textarea']");
    private By nameField = By.xpath("//input[@placeholder='Name']");
    private By emailField = By.xpath("//input[@placeholder='Email address']");
    private By submitButton = By.cssSelector("button[data-test='feedback-send']");

    //below methods for interactions with the page are defined. Page methods return data instead of asserting
    // so tests control validation and remain flexible and reusable
    public void goToBrokenLink() {
        driver.get("https://www.jetbrains.com/help/idea/non-existent-page.html");
    }

    public String getPageTitle() {
        return driver.getTitle();
    }

    public boolean isGhostElementPresent() {
        try {
            driver.findElement(ghostElement);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String searchInvalidKeyword() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        element.sendKeys("!@#$%^&*()_+");
        WebElement resultMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(noSearchResults));
        return resultMessage.getText();
    }

    public boolean isNoResultsMessagePresent() {
        return driver.getPageSource().toLowerCase().contains("We’re sorry! We couldn’t find results for «\"!@#$%^&*()_+\"»");
    }

    public boolean isCookieBannerMissing() {
        List<WebElement> banner = driver.findElements(cookieBanner);
        return banner.isEmpty();
    }

    public boolean waitForSlowElement() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(slowElement));
            return true;
        } catch (TimeoutException e) {
            return false;
        }
    }

    public void voteWithNegative(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(noButton));
        js.executeScript("arguments[0].scrollIntoView()", element);
        element.click();
    }

    public boolean feedbackFormVisibility(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(feedbackForm));
        return element.isDisplayed();
    }


    public String feedbackFormHeading() {
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(howToImproveHeading));
        return element.getText();
    }
    public boolean textFieldDisplayed(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textField));
        return element.isDisplayed();
    }

    public String textFieldPlaceholderDisplayed(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textField));
       return element.getAttribute("placeholder");
    }

    public boolean nameFieldDisplayed(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        return element.isDisplayed();
    }

    public String nameFieldPlaceholderDisplayed(){
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        return element.getAttribute("placeholder");
    }

    public boolean emailFieldDisplayed(){
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        return element.isDisplayed();
    }

    public String emailFieldPlaceholderDisplayed(){
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        return element.getAttribute("placeholder");
    }

    public boolean submitButtonDisplayed(){
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        return element.isDisplayed();
    }

    public boolean submitButtonDisabled(){
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        return element.getAttribute("class").contains("button_disabled");

        //No real HTML disabled attribute. Before typing: class contains "button_disabled"
        //After typing: class does not contain "button_disabled"
        //The button is never technically disabled in HTML (disabled="" is removed immediately by JS), but the CSS class is the true source of truth.
    }

    public void typeFeedback(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textField));
        element.sendKeys("I'm not happy with the site navigation");
    }

    public boolean submitButtonEnabled(){
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        return !element.getAttribute("class").contains("button_disabled");
    }

    public void submitFeedback(){
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        element.click();
    }

    public boolean feedbackFormInvisibility(){
    return wait.until(ExpectedConditions.invisibilityOfElementLocated(feedbackForm));
    }

    public void typeInvalidEmail(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        element.sendKeys("notValid@gmail.");
    }

    public boolean isTheEmailInvalid(){
        wait.until(ExpectedConditions.attributeToBe(emailField, "aria-invalid", "true"));
        return true;
    }
}