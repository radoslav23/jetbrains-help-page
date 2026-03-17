import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DownloadSectionPage extends BasePage {

    public DownloadSectionPage(WebDriver driver) {super(driver);}

    //below locators are defined
    private By getIntellijIdeaButton = By.xpath("/html//div[@id='webhelp-root']/div//a[@href='https://www.jetbrains.com/idea/download/']");
    private By pricingButton = By.xpath("/html//div[@id='js-menu-second-desktop']//a[@href='/idea/buy/']/span[@class='_content_10h2pj3_207']");
    private By downloadButton = By.xpath("/html//div[@id='js-menu-second-desktop']//a[@href='/idea/download/']/span[@class='_content_10h2pj3_207']");
    private By intelliJHeading = By.xpath("//p[@class='_rs-subtitle-2_1owszn1_1 _rs-text_hardness_auto_1owszn1_1 wt-offset-top-12']");
    private By macOsButton = By.xpath("//div[@id='download-block']/section[1]//div[@class='_scrollable_171fxw9_23']/div/button[2]/div[.='macOS']");
    private By linuxButton = By.xpath("//div[@id='download-block']/section[1]//div[@class='_scrollable_171fxw9_23']/div/button[3]/div[.='Linux']");
    private By windowsButton = By.xpath("//div[@id='download-block']/section[1]//div[@class='_scrollable_171fxw9_23']/div/button[1]/div[.='Windows']");
    private By windowsDownloadDropdown = By.xpath("//span[@title='.exe (Windows)']");
    private By windowsZip = By.linkText(".zip (Windows)");
    private By windowsExe = By.linkText(".exe (Windows)");
    private By windowsExeArm64 = By.linkText(".exe (Windows ARM64)");
    private By macOsDownloadButton = By.xpath("//button[@type='button' and contains(., 'Download')]");
    private By dmgAppleSilicon = By.linkText(".dmg (Apple Silicon)");
    private By dmgIntel = By.linkText(".dmg (Intel)");
    private By linuxDownloadButton = By.xpath("//*[@id=\"download-block\"]/section[2]/div/div/div[1]/div[2]/div[3]/div/div/div/span/button");
    private By tarLinux = By.linkText(".tar.gz (Linux)");
    private By tarLinuxArm64 = By.linkText(".tar.gz (Linux ARM 64)");

    //below methods for interactions with the page are defined
    public void clickGetIntellijIdeaButton() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(getIntellijIdeaButton));
        element.click();
    }

    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        return currentUrl;
    }

    public boolean prisingButtonDisplayed(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(pricingButton));
        return element.isDisplayed();
    }

    public boolean downloadButtonDisplayed(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(downloadButton));
        return element.isDisplayed();
    }

    public boolean leadingIdeHeadingVisibility() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(intelliJHeading));
        return element.isDisplayed();
    }

    public void clickWindowsDownloadDropdown(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(windowsDownloadDropdown));
        element.click();
    }

    public boolean windowsZipVisibility(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(windowsZip));
        return element.isDisplayed();
    }

    public boolean windowsExeVisibility(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(windowsExe));
        return element.isDisplayed();
    }

    public boolean windowsExeArm64Visibility(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(windowsExeArm64));
        return element.isDisplayed();
    }

    public void clickMacOsButton(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(macOsButton));
        element.click();
    }

    public boolean macOsDownloadButtonVisible(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(macOsDownloadButton));
        return element.isDisplayed();
    }
    public void macOsDownloadButtonClick(){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(macOsDownloadButton));
        element.click();
    }

    public boolean dmgAppleSiliconVisibility(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(dmgAppleSilicon));
        return element.isDisplayed();
    }

    public boolean dmgIntelVisibility(){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(dmgIntel));
        return element.isDisplayed();
    }

    public void clickLinuxButton(){
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(linuxButton));
        element.click();
        wait.until(ExpectedConditions.urlContains("section=linux"));
    }

    public boolean linuxDownloadButtonVisible(){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(linuxDownloadButton));
        return element.isDisplayed();
    }

    public void linuxDownloadButtonClick(){
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(linuxDownloadButton));
        element.click();
    }
}
