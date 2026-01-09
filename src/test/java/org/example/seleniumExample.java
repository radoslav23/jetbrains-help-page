package org.example;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;


public class seleniumExample {
    WebDriver driver;
    //WebDriverWait wait;
    @BeforeTest
    public void beforeTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        //wait = new WebDriverWait(driver, Duration.ofSeconds(3));
    }

    @Test
    public void seleniumDocsPage() throws InterruptedException {

        driver.get("https://www.jetbrains.com/help/idea/ai-assistant-in-jetbrains-ides.html");
        //Thread.sleep(2000);
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.findElement(By.xpath("//div[@id='ch2-dialog']//button[.='Accept All']")).click();
        driver.findElement(By.xpath("//a[normalize-space()='Testing']")).click();

        JavascriptExecutor js =  (JavascriptExecutor) driver;
        //WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[normalize-space()='Selenium']")));
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        element.click();

        WebElement element1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[normalize-space()='Add elements to code']")));
        js.executeScript("arguments[0].scrollIntoView(true);", element1);
        String text2 = "Add elements to code";
        Assert.assertEquals(element1.getText(), text2);
    }

    @Test
    public void junitDocsPage() throws InterruptedException {

        driver.get("https://www.jetbrains.com/help/idea/tests-in-ide.html");
        //Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.findElement(By.xpath("//div[@id='ch2-dialog']//button[.='Accept All']")).click();
        driver.findElement(By.xpath("//h3[normalize-space()='JUnit']")).click();
        String actualTitle = driver.getTitle();
        String expectedTitle = "Get started with JUnit";
        Assert.assertTrue(actualTitle.contains(expectedTitle));
    }

    @Test
    public void searchForKeyword() {
        driver.get("https://www.jetbrains.com/help/idea/getting-started.html");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
        driver.findElement(By.xpath("//div[@id='ch2-dialog']//button[.='Accept All']")).click();
        WebElement searchField = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@title='Search']//*[name()='svg']")));
        searchField.click();
        //driver.findElement(By.xpath("//button[@title='Search']//*[name()='svg']"));
        //button[@title='Search']//*[name()='svg']
    }

    @AfterTest
    public void afterTest() {
        //driver.quit();
    }
}
