package com.Capita.AutomationDemo.testscripts;

import java.io.IOException;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Capita.AutomationDemo.excelreader.Excel_Reader;
import com.Capita.AutomationDemo.pagelibrary.BookHotel;
import com.Capita.AutomationDemo.pagelibrary.SearchHotel;
import com.Capita.AutomationDemo.pagelibrary.SelectHotel;
import com.Capita.AutomationDemo.pagelibrary.SignIn;
import com.Capita.AutomationDemo.testbase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class TC003_testHotel_EndtoEnd extends TestBase{
	SignIn signIn;
	SearchHotel searchhotel;
	SelectHotel selecthotel;
	BookHotel bookhotel;
	static Logger log = Logger.getLogger(TC002_TestLoginThroughDataProvider.class.getName());
	
	@BeforeMethod
	public void setUP() throws IOException{
		init();
		// starting test
		test = extent.startTest("Login Test", "This test will verify login test");
		
		test.log(LogStatus.PASS, "Basic set up for test is done");
	}
	
	public Object[][] getData(String ExcelName, String testcase) {
		Excel_Reader Data = new Excel_Reader(System.getProperty("user.dir") + "//src//main//resources//resources//"+ExcelName);
		int rowNum = Data.getRowCount(testcase);
		System.out.println("Total Tests with Y Flag are " +(rowNum-1));
		int colNum = Data.getColumnCount(testcase);
		Object sampleData[][] = new Object[rowNum - 1][colNum];
		for (int i = 2; i <=rowNum; i++) {
			for (int j = 0; j < colNum; j++) {
				sampleData[i - 2][j] = Data.getCellData(testcase, j, i);
			}
		}
		return sampleData;
	}
	
	@DataProvider
	public Object[][] EndtoEndHotels(){
		Object[][] data = getData("Login.xlsx", "EndtoEnd");
		return data;
	}
	
	@Test(priority=1, dataProvider = "EndtoEndHotels")
	public void testendtoEnd(String TestCaseName, String Username, String Password, String Location,String Hotels,
			String RoomType,String FirstName, String LastName, String Address,String CCNumber,
String CCType, String Expirymonth, String ExpiryYear, String CVVNumber,	String runMode) throws InterruptedException, IOException{
		log.info("Getting Data from Data Provider");
		if(runMode.equals("N")){
			throw new SkipException("Skipping the test");
		}
		try{
			test.log(LogStatus.PASS, "Login Test started");
			signIn = new SignIn(driver);
			signIn.enterAllreadyRegisterUserEmail(Username);
			signIn.enterAllReadyRegisteredPassword(Password);
			signIn.clickonSignInToAccount();
			test.log(LogStatus.PASS, "Login to Adactin done");
			Thread.sleep(3000);
			
		}
		catch(Exception e){
		}
			test.log(LogStatus.PASS, "Search Hotels started");
			searchhotel = new SearchHotel(driver);
			searchhotel.selectYourLocation(Location);
			searchhotel.selectYourHotels(Hotels);
			searchhotel.selectYourRoomsType(RoomType);
			searchhotel.selectYourRoomCount();
			searchhotel.selectAdults();
			searchhotel.selectChidren();
			searchhotel.clickSearchButton();
			test.log(LogStatus.PASS, "Search Hotels details entered successfully");
			
			test.log(LogStatus.PASS, "Select Hotels details started");
			selecthotel = new SelectHotel(driver);
			selecthotel.selectRadioButton1();
			selecthotel.clickContinueButton();
			test.log(LogStatus.PASS, "Select Hotels details  entered successfully");
			Thread.sleep(4000);
			
			test.log(LogStatus.PASS, "Booking Confirmation details started");
			bookhotel = new  BookHotel(driver);
			bookhotel.enterFirstName(FirstName);
			bookhotel.enterLastName(LastName);
			bookhotel.enterAddress(Address);
			bookhotel.enterCCNumber(CCNumber);
			bookhotel.selectCCType(CCType);
			bookhotel.selectExpiryMonth(Expirymonth);
			bookhotel.selectExpiryYear(ExpiryYear);
			bookhotel.selectCVVNumber(CVVNumber);
			bookhotel.clickBookNow();
			Thread.sleep(5000);
			WebElement OrderNo = driver.findElement(By.id("order_no"));
			
			test.log(LogStatus.PASS, "Booking Confirmation details started and Order Numbewr is " + OrderNo.getAttribute("value"));
			Thread.sleep(2000);
			signIn.logout();
	}
	
	
	
	@AfterMethod
	public void quitBrowser() throws InterruptedException{
		closeBrowser();
		Thread.sleep(3000);
	}
	
}
