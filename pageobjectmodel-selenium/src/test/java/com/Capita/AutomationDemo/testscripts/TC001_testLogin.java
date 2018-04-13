package com.Capita.AutomationDemo.testscripts;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.Capita.AutomationDemo.pagelibrary.SignIn;
import com.Capita.AutomationDemo.testbase.TestBase;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.NetworkMode;

public class TC001_testLogin extends TestBase{
	SignIn signIn;
	
	

	@BeforeClass
	public void setUP() throws IOException{
		init();
		// starting test
		test = extent.startTest("Login Test", "This test will verify login test");
		
		test.log(LogStatus.PASS, "Basic set up for test is done");
	}
	
	@Test()
	public void testlogin(){
		try{
			test.log(LogStatus.PASS, "Login Test started");
			signIn = new SignIn(driver);
			signIn.enterAllreadyRegisterUserEmail("RobinCAP");
			signIn.enterAllReadyRegisteredPassword("79D209");
			signIn.clickonSignInToAccount();
			Thread.sleep(3000);
			signIn.logout();
		}
		catch(Exception e){
		}
		
	}
	
	@AfterClass
	public void quitBrowser(){
		closeBrowser();
	}

}
