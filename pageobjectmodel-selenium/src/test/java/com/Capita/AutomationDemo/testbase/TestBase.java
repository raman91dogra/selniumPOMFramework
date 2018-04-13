package com.Capita.AutomationDemo.testbase;


import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import org.testng.Reporter;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeMethod;

import com.google.common.base.Function;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jna.Platform;

public class TestBase {

	public static Properties Repository = new Properties();
	public File f;
	public FileInputStream FI;
	public static WebDriver driver;
	public String startTime;
	public static int indexSI = 1;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	static {
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat formater = new SimpleDateFormat("dd_MM_yyyy_hh_mm_ss");
		extent = new ExtentReports(System.getProperty("user.dir") + "/src/test/java/com/Capita/AutomationDemo/testReport/test" + formater.format(calendar.getTime()) + ".html", false);
		
	}

	public void init() throws IOException {
		String log4jConfPath = "log4j.properties";
		PropertyConfigurator.configure(log4jConfPath);
		loadPropertiesFile();
		selectBrowser(Repository.getProperty("browser"));
		//driver.get(Repository.getProperty("url"));
		driver.get("http://adactin.com/HotelApp/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	public void loadPropertiesFile() throws IOException {
		f = new File(System.getProperty("user.dir") + "//src//test//java//com//Capita//AutomationDemo//config//config.properties");
		FI = new FileInputStream(f);
		Repository.load(FI);
	}


	public void selectBrowser(String browser){
		
			if(browser.equalsIgnoreCase("firefox")){
				
				System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir")+"/drivers/geckodriver.exe");
				ProfilesIni profile = new ProfilesIni();
				FirefoxProfile myprofile = profile.getProfile("RobinRelan");
				driver = new FirefoxDriver(myprofile);
				System.out.println("Opening Firefox Driver.......");
			}
			else if(browser.equalsIgnoreCase("chrome")){
				
				System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/drivers/chromedriver.exe");
				driver = new ChromeDriver();
				System.out.println("Opening Chrome Driver.........");
			}
			else {
				
				DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
				capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,true);
				System.setProperty("webdriver.ie.driver", System.getProperty("user.dir")+"/drivers/IEDriverServer.exe");
				
				driver = new InternetExplorerDriver(capabilities);
				System.out.println("Opening Internet Explorer Driver.........");
			}
		
	}

	public void expliciteWait(WebElement element, int timeToWaitInSec) {
		WebDriverWait wait = new WebDriverWait(driver, timeToWaitInSec);
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public void waitForPageToLoad(long timeOutInSeconds) {
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		try {
			System.out.println("Waiting for page to load...");
			WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
			wait.until(expectation);
		} catch (Throwable error) {
			System.out.println("Timeout waiting for Page Load Request to complete after " + timeOutInSeconds + " seconds");
			Assert.assertFalse(true, "Timeout waiting for Page Load Request to complete.");

		}
	}

	public void clickWhenReady(By locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		element.click();

	}

	public WebElement fluentWait(final WebElement webElement, int timeinsec) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(5, TimeUnit.SECONDS).ignoring(NoSuchElementException.class);
		WebElement element = wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				return webElement;
			}
		});

		return element;
	};

	public WebElement getWhenVisible(By locator, int timeout) {
		WebElement element = null;
		WebDriverWait wait = new WebDriverWait(driver, timeout);
		element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		return element;

	}

	public void waitFor(int sec) throws InterruptedException {
		Thread.sleep(sec * 1000);
	}


	public  void getScreenShot(String fileName) throws IOException {
		File outputFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(outputFile, new File(System.getProperty("user.dir") + "//src//test//java//com//Capita//AutomationDemo//screenshots//" + fileName + ".jpg"));
	}

	public static String now(String format) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat fm = new SimpleDateFormat();
		return fm.format(cal.getTime());
	}

	



	@BeforeMethod()
	public void beforeMethod(Method result) {
		test = extent.startTest(result.getName());
		test.log(LogStatus.INFO, result.getName() + " test Started");
	}

	public void closeBrowser() {
		driver.close();
		extent.endTest(test);
		extent.flush();
	}
	
}
