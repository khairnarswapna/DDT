package com.bridgelabz.DataDriventesting;



import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


public class BaseTest {

    WebDriver driver;
    @BeforeMethod
    public void loading_web_Driver() {
        driver=new ChromeDriver();
        System.setProperty("webdriver.chrome.driver","/home/user/IdeaProjects/DataDrivenTesting/src/Driver/chromedriver");
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void closing_web_driver() {
        driver.close();

    }
}
