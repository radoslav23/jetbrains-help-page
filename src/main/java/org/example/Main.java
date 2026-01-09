//package org.example;
//import com.google.common.annotations.VisibleForTesting;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
//import org.testng.annotations.Test;
//
////TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
//// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
////public class Main {
////    public static void main(String[] args) throws InterruptedException {
////        //.setProperty("webdriver.chrome.driver","C:\\browser drivers\\chromedriver.exe");
////        WebDriver driver = new ChromeDriver();
////        driver.get("https://www.jetbrains.com/help/idea/ai-assistant-in-jetbrains-ides.html");
////        driver.manage().window().maximize();
////        Thread.sleep(3000);
////        driver.quit();
////        }
////    }
//
//public class Main {
//    WebDriver driver;
//    @BeforeTest
//    public void beforeTest() {
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//    }
//
//    @Test
//    public void main() throws InterruptedException {
//
//        driver.get("http://localhost/litecart/en/");
//        Thread.sleep(2000);
//    }
//
//    @AfterTest
//    public void afterTest() {
//        driver.quit();
//    }
//}
