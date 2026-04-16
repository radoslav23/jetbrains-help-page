import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CommonComponents extends BasePage{

    public CommonComponents(WebDriver driver) {
        super(driver);
    }

    private By feedbackForm = By.xpath("//span[@class='feedback-side-panel__trigger-text']");

    public void clickFeedbackPanel(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(feedbackForm));
        element.click();
    }

    public boolean feedbackFormVisibility(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(feedbackForm));
        return element.isDisplayed();
    }

    public boolean feedbackFormInvisibility(){
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(feedbackForm));
    }

}
