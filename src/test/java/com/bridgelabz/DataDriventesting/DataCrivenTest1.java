package com.bridgelabz.DataDriventesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DataCrivenTest1 {


    WebDriver driver;
    @BeforeTest
    public void initialization()
    {
        driver = new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "/home/user/IdeaProjects/DataDrivenTesting/chromedriver");
        driver.manage().window().maximize();
        // To launch facebook
        driver.get("http://www.facebook.com/");
        // implicit wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    // To get data from dataprovider
    @Test(dataProvider="testdataset")
    public void fbLoginLogout(String email, String password) throws Exception{

         driver.findElement(By.xpath("//input[@type='email'][@name='email']")).clear();
        // To pass Email
        driver.findElement(By.xpath("//input[@type='email'][@name='email']")).sendKeys(email);
        // To clear password field
        driver.findElement(By.xpath("//input[@type='password'][@name='pass']")).clear();
        // To pass password
        driver.findElement(By.xpath("//input[@type='password'][@name='pass']")).sendKeys(password);
        // To click on Login button
        driver.findElement(By.xpath("//input[@id='u_0_b']")).click();
        // To click on Account settings dropdown
        driver.findElement(By.xpath("//div[text()='Account Settings']")).click();
        // To click on logout button
        driver.findElement(By.xpath("//text()[.='Log Out']/ancestor::span[1]")).click();

    }
    // @DataProvider passes data to test cases. Here I took 2 dimension array.
    @DataProvider(name="testdataset")
    public Object[][] getData(){
        // Create object with two paraments
        // first parameter is row and second one is column
        Object[][] data = new Object[2][2];
        data[0][0] = "shivanicha9@gmail.com";
        data[0][1] = "sweetpinu@93";

        data[1][0] = "khairnarswapna99@gmail.com";
        data[1][1] = "sweetpinu@93";

        return data;
    }
}
