import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    //These fields define the tools my page object uses to interact with the browser. driver is the main controller,
    // wait helps synchronize actions with page state, and js allows direct JavaScript execution for advanced control
    public BasePage(WebDriver driver) {
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
}
