package com.Capita.AutomationDemo.testscripts;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.SkipException;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.logging.Logger;

import com.Capita.AutomationDemo.excelreader.Excel_Reader;
import com.Capita.AutomationDemo.pagelibrary.SignIn;
import com.Capita.AutomationDemo.testbase.TestBase;

public class TC002_TestLoginThroughDataProvider extends TestBase{
	SignIn signIn;
	static Logger log = Logger.getLogger(TC002_TestLoginThroughDataProvider.class.getName());
	
	@BeforeMethod
	public void setUP() throws IOException{
		init();
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
	public Object[][] loginData(){
		Object[][] data = getData("Login.xlsx", "Login");
		return data;
	}
	
	@Test(dataProvider = "loginData")
	public void TestLoginWithDataProvider(String TestCaseName, String Email, String Password,String runMode) throws InterruptedException, IOException{
        log.info("Getting Data from Data Provider");
		if(runMode.equals("N")){
			throw new SkipException("Skipping the test");
		}
		signIn = new SignIn(driver);
		//signIn.loginToApplication(Email, Password);
		signIn.enterAllreadyRegisterUserEmail(Email);
		signIn.enterAllReadyRegisteredPassword(Password);
		signIn.clickonSignInToAccount();
		Thread.sleep(3000);
		signIn.logout();
		
		
	}
	
	@AfterMethod
	public void quitBrowser() throws InterruptedException{
		closeBrowser();
		Thread.sleep(3000);
	}

}
