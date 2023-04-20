package com.selenium.test2.test2;

import java.awt.AWTException;
import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;
import com.aventstack.extentreports.reporter.configuration.Theme;

import io.github.bonigarcia.wdm.WebDriverManager;

public class D10C4 {
	WebDriver driver;
	static ExtentTest test;
	static ExtentReports extent;
	ExtentHtmlReporter htmlReporter;
	@Test
  public void f() throws Exception {
		Thread.sleep(3000);
		  WebElement uname=driver.findElement(By.name("username"));
		  WebElement pwd=driver.findElement(By.name("password"));
		  WebElement submit=driver.findElement(By.xpath("//*[@class='oxd-button oxd-button--medium oxd-button--main orangehrm-login-button']"));
		  uname.sendKeys("Admin");
		  pwd.sendKeys("admin123");
		  submit.click(); 
		  test = extent.createTest("Test Case 1", "The test case 1 has passed");
  }

	@Parameters("browser")
	  @BeforeMethod()
	 	  public void beforeMethod(String browser1) {
		htmlReporter =  new ExtentHtmlReporter("D://D10report5.html");
		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);
		
		//configuration items to change the look and feel
        //add content, manage tests etc
        htmlReporter.config().setChartVisibilityOnOpen(true);
        htmlReporter.config().setDocumentTitle("Simple Automation Report");
        htmlReporter.config().setReportName("Test Report");
        htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        htmlReporter.config().setTheme(Theme.STANDARD);

		  if(browser1.equals("chrome"))
		  {
		  WebDriverManager.chromedriver().setup();
			ChromeOptions co = new ChromeOptions();
			co.addArguments("--remote-allow-origins=*");
			driver=new ChromeDriver(co);
			driver.manage().window().maximize();
			String url="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
			driver.get(url);
			driver.manage().window().maximize();
	  }
		  else if(browser1.equals("edge"))
		  {
			  WebDriverManager.edgedriver().setup();
				//FirefoxOptions co = new FirefoxOptions();
				//co.addArguments("--remote-allow-origins=*");
				driver=new EdgeDriver();
				driver.manage().window().maximize();
				String url="https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
				driver.get(url);
				driver.manage().window().maximize();
		  }
	  }
	
	@AfterMethod
	  public void getResult(ITestResult result) throws AWTException, IOException {
		  if(result.getStatus() == ITestResult.FAILURE) {
			  test.log(Status.FAIL, result.getTestName());
	      }
	      else if(result.getStatus() == ITestResult.SUCCESS) {
	          test.log(Status.PASS, result.getTestName());
	          
	      }
	      else {
	          test.log(Status.SKIP, result.getTestName());
	      }
	  }
	  @AfterSuite
	  public void afterMethod() {
		  extent.flush();
		  //driver.close();
	  }

}
