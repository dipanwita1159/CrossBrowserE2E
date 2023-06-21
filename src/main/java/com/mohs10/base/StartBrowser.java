package com.mohs10.base;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import io.github.bonigarcia.wdm.managers.EdgeDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;


public class StartBrowser {
	 public static WebDriver driver;
	 //set up report
	 public static ExtentReports extent;
	 public static ExtentTest parentTest;
	 public static ExtentTest childTest;
	 ExtentSparkReporter sparkReporter;
	 
	 @BeforeTest
	 public void generateReport()
	 {
		 sparkReporter = new ExtentSparkReporter("Report/AutomationReport.html");
		 extent = new ExtentReports();
		 extent.attachReporter(sparkReporter);
	 }
	 
	 @BeforeMethod
	 public void methodName(Method method)
	 {
		parentTest = extent.createTest(method.getName()); 
	 }
	 @Test
	 public static WebDriver beforeClass(String browser, String url) {
	     
	     if (browser.equalsIgnoreCase("firefox")) {
	         WebDriverManager.firefoxdriver().setup();
	         driver = new FirefoxDriver();
	     } else if (browser.equalsIgnoreCase("chrome")) {
	         WebDriverManager.chromedriver().setup();
	         driver = new ChromeDriver();
	     } else if (browser.equalsIgnoreCase("edge")) {
	        WebDriverManager.edgedriver().setup();
	         driver = new EdgeDriver();
	     } else {
	         throw new IllegalArgumentException("Invalid browser specified: " + browser);
	     }
	     
	     driver.manage().window().maximize();
	     driver.get(url);
	     
	     return driver;
	 }

	  

		
		  //@AfterClass public void afterClass() { driver.quit(); extent.flush(); }
		 

	}
