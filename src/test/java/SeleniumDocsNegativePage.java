import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumDocsNegativePage extends BasePage {

    public SeleniumDocsNegativePage(WebDriver driver) {super(driver);}

    // Locators are private to enforce encapsulation and prevent tests from accessing elements directly
    private By cookieBanner = By.id("cookie-banner");
    private By slowElement = By.id("slowElement");
    private By searchField = By.xpath("//input[@placeholder='Ctrl+K for advanced search']");
    private By noSearchResults = By.xpath("//h3[@class='quick-search__no-results-text']");
    private By noButton = By.xpath("//button[normalize-space()='No']");
    private By howToImproveHeading = By.xpath("/html/body[@class='app-is-rendered']//form//legend[@class='feedback__legend']");
    private By textField = By.cssSelector("textarea[data-test-id='feedback-textarea']");
    private By nameField = By.xpath("//input[@placeholder='Name']");
    private By emailField = By.xpath("//input[@placeholder='Email address']");
    private By submitButton = By.cssSelector("button[data-test='feedback-send']");

    //below methods for interactions with the page are defined. Page methods return data instead of asserting
    // so tests control validation and remain flexible and reusable
    public String getPageTitle() {
        return driver.getTitle();
    }

    public void typeInvalidKeyword() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(searchField));
        element.sendKeys("!@#$%^&*()_+");
    }

    public String noResultForInvalidKeyword(){
        WebElement resultMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(noSearchResults));
        return resultMessage.getText();
    }

    //This method checks whether the cookie banner is missing from the page
    //and returns true if no elements matching the cookie banner locator are found
    public boolean isCookieBannerMissing() {
        return driver.findElements(cookieBanner).isEmpty();
    }

    public boolean isSlowElementVisible() {
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
    }

    public void typeFeedback(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(textField));
        element.sendKeys("I'm not happy with the site navigation");
    }

    public boolean submitButtonEnabled(){
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        return element.getAttribute("disabled") == null;
    }

    public void submitFeedback(){
        WebElement element =  wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton));
        element.click();
    }

    public void typeInvalidEmail(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        element.sendKeys("notValid@gmail.");
    }

    public boolean isTheEmailInvalid(){
        wait.until(ExpectedConditions.attributeToBe(emailField, "aria-invalid", "true"));
        return true;
    }

    public void typeName(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(nameField));
        element.sendKeys("John Doe");
    }

    public void typeEmail(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(emailField));
        element.sendKeys("doe54jign56565@gmail.com");
    }
}