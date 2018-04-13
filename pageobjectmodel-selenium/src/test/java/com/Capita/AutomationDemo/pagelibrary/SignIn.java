package com.Capita.AutomationDemo.pagelibrary;



import java.io.IOException;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import com.Capita.AutomationDemo.testbase.TestBase;
import com.relevantcodes.extentreports.LogStatus;

public class SignIn extends TestBase{
	
	WebDriver driver;
	static Logger log = Logger.getLogger(SignIn.class.getName());
	String LoginScreen, LogoutScreen;
	public enum ELEMENT {
		USERNAME(By.id("username")),
		PASSWORD(By.id("password")),
		LOGIN(By.id("login")),
		LOGOUT(By.xpath("/html/body/table[2]/tbody/tr[1]/td[2]/a[4]")),
		ERRORMESSAGE(By.xpath("//*[@id='login_form']/table/tbody/tr[5]/td[2]/div/b"));
	
		private By findby;
		private ELEMENT(By locator)
		{
			this.findby = locator;
		}
	}
	

	
	public SignIn(WebDriver driver){
		this.driver = driver;
	}

	
	/**
	 * This method will enter email address to create an account text box.
	 * In Login Page
	 * @param emailAddress
	 * @throws IOException 
	 */

	public void enterAllreadyRegisterUserEmail(String username) {
		log.info("entering email addredd to all ready registered user text box");
		driver.findElement(ELEMENT.USERNAME.findby).sendKeys(username);
		test.log(LogStatus.PASS, "Username "+ username + " is entered successfully");
		
	}
	
	public void clickonSignInToAccount() throws IOException{
		log.info("clicking on sign in button");
		driver.findElement(ELEMENT.LOGIN.findby).click();
		test.log(LogStatus.PASS, "SignIn button is clicked successfully");
		getScreenShot(LoginScreen);
	}
	
	public void enterAllReadyRegisteredPassword(String password){
		log.info("enterign password to password text box");
		driver.findElement(ELEMENT.PASSWORD.findby).sendKeys(password);
		test.log(LogStatus.PASS, "Password "+ password + " is entered successfully");
	}
	

	public void logout() throws IOException{
		try{
		boolean isdisplayed = driver.findElement(ELEMENT.LOGOUT.findby).isDisplayed();
		if(isdisplayed){
			driver.findElement(ELEMENT.LOGOUT.findby).click();
			test.log(LogStatus.PASS, "Logout button is clicked successfully");
		}
		}
		catch(Exception e){
			Assert.assertTrue(false, "sign out button is not displayed");
		}
		getScreenShot(LogoutScreen);
	}
}
