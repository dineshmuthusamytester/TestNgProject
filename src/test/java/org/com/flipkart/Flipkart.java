package org.com.flipkart;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Flipkart {
	static WebDriver driver;
	@BeforeClass
	@Test(priority = 1)
	public  static void login()
	{
    WebDriverManager.chromedriver().setup();
	 driver = new ChromeDriver ();
	driver .get("https://www.flipkart.com/");
	}
	@Test(priority = 2)
	public   void script() throws InterruptedException, IOException
	{
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.manage().window().maximize();
	 driver.findElement(By.xpath("//button[text()='✕']")).click();
	
	  driver.findElement(By.xpath("//input[@type='text']")).sendKeys("samsung mobiles");
	
	  driver.findElement(By.xpath("//button[@type='submit']")).click();
	
	Thread.sleep(3000);	
	File f = new File ("C:\\Users\\Dharani\\eclipse-workspace\\Testng\\target\\sheet 1.xlsx");
	FileOutputStream f1 = new FileOutputStream (f);
    XSSFWorkbook a = new XSSFWorkbook(); 
    XSSFSheet b = a.createSheet("samsung");
    
    List<WebElement> galaxy = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
    for (int i = 0; i < galaxy.size(); i++) {
    	WebElement galaxy1 = galaxy.get(i);
    	String text = galaxy1.getText();
    	System.out.println(text);
    	Thread.sleep(3000);
    	XSSFRow c = b.createRow(i);
    	XSSFCell d = c.createCell(0);
    	d.setCellValue(text);
    			
	}
    a.write(f1);
    f1.close();
    
    FileInputStream g=new FileInputStream(f);
    Workbook f2 =new XSSFWorkbook(g);
       Sheet h = f2.getSheet("samsung");
       Row i = h.getRow(0);
       Cell j = i.getCell(0);
       String k = j.getStringCellValue();
       System.out.println("third mobile name is : ");
       System.out.println(b);
       
       
       String TestFile = "C:\\Users\\Dharani\\eclipse-workspace\\Testng\\target\\dinnesh sheet 1.txt";
		FileReader FR = new FileReader(TestFile);
		BufferedReader BR = new BufferedReader(FR);
		String Content = "";

		
		while((Content = BR.readLine())!= null){
			System.out.println(Content);
		
			
		}
		BR.close();
		if (k.equals(Content))  {
			System.out.println("Excel Value is Equal to Notepad");	
		}
		else {
			System.out.println("Excel Value is Notequal to Notepad");
		}
		boolean as=true;
		Assert.assertTrue(as);
		Assert.assertEquals(k, Content);
	}
	@Test(priority = 3,enabled = true,invocationCount = 2)
	public void windowshandle()
	{
	driver.findElement(By.xpath("(//div[contains(text(),'SAMSUNG Galaxy ')]) [1]")).click();
	String parent = driver.getWindowHandle();
	Set<String> allwindow = driver.getWindowHandles();
	for (String x : allwindow) {
		if(!x.equals(parent));
		{
			driver.switchTo().window(x);
		}
		
	}
	List<String>b = new ArrayList<String>(); 
	b.addAll(allwindow);
	driver.switchTo().window(b.get(0));
	
	}

}
