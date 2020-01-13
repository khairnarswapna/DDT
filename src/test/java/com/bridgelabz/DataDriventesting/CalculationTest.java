package com.bridgelabz.DataDriventesting;


import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class CalculationTest {

    public static void main(String args[]) throws IOException {


        ChromeDriver driver=new ChromeDriver();
        System.setProperty("webdriver.chrome.driver", "/home/user/IdeaProjects/DataDrivenTesting/chromedriver");
        driver.manage().window().maximize();
        // To launch facebook
        driver.get("https://www.moneycontrol.com/fixed-income/calculator/state-bank-of-india-sbi/fixed-deposit-calculator-SBI-BSB001.html");
        // implicit wait
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        FileInputStream file= new FileInputStream("/home/user/IdeaProjects/DataDrivenTesting/src/test/resources/calculateData.xlsx");
        XSSFWorkbook workbook=new XSSFWorkbook(file);
        XSSFSheet sheet=workbook.getSheet("sheet1");
        int rowcount=sheet.getLastRowNum();

        for(int i=1;i<=rowcount;i++)
        {
            XSSFRow row=sheet.getRow(i);

           // XSSFCell principlecell=row.getCell(0);
           // int princ=(int)principlecell.getNumericCellValue();

            int princ=(int)row.getCell(0).getNumericCellValue();

            //XSSFCell roi=row.getCell(1);
           // int rateofinterest=(int)roi.getNumericCellValue();
            int rateofinterest=(int)row.getCell(1).getNumericCellValue();

           // XSSFCell period=row.getCell(2);
           // int per=(int)period.getNumericCellValue();
            int per=(int)row.getCell(2).getNumericCellValue();

            XSSFCell Frequency=row.getCell(3);
            String Freq= Frequency.getStringCellValue();

            XSSFCell MaturityValue=row.getCell(4);
            int Exp_value=(int)MaturityValue.getNumericCellValue();

            driver.findElement(By.id("principle")).sendKeys(String.valueOf(princ));
            driver.findElement(By.id("interest")).sendKeys(String.valueOf(rateofinterest));
            driver.findElement(By.id("tenure")).sendKeys(String.valueOf(per));

            Select periodCombo=new Select(driver.findElement(By.id("tenurePeriod")));
            periodCombo.selectByVisibleText("year(s)");

            Select frequency=new Select(driver.findElement(By.id("frequency")));
            frequency.selectByVisibleText(Freq);

            driver.findElement(By.xpath("//div[@class='PT25']//a[1]//img[1]")).click();
            String actual_value=driver.findElement(By.xpath("//strong[contains(text(),'2301.26')]")).getText();

            if((Double.parseDouble(actual_value))==Exp_value){
                System.out.println("Test is passed");
            }else {
                System.out.println("Test is failed");
            }

            driver.findElement(By.id("principle")).click();
            driver.findElement(By.id("interest")).click();
            driver.findElement(By.id("tenure")).click();

            driver.findElement(By.xpath("//img[@class='PL5']")).click();





        }
        driver.close();


    }

}
