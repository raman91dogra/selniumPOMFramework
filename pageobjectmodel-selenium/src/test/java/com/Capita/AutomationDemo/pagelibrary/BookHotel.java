package com.Capita.AutomationDemo.pagelibrary;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

import com.Capita.AutomationDemo.pagelibrary.SearchHotel.ELEMENT;


public class BookHotel {

	WebDriver driver;
	static Logger log = Logger.getLogger(SignIn.class.getName());
	public enum ELEMENT {
		FIRSTNAME(By.id("first_name")),
		LASTNAME(By.id("last_name")),
		BILLINGADDRESS(By.id("address")),
		CCNO(By.id("cc_num")),
		CCTYPE(By.id("cc_type")),
		EXPMONTH(By.id("cc_exp_month")),
		EXPYEAR(By.id("cc_exp_year")),
		CVV(By.id("cc_cvv")),
		BOOKNOW(By.id("book_now"));
		
		private By findby;
		private ELEMENT(By locator)
		{
			this.findby = locator;
		}
	}
	
		public BookHotel(WebDriver driver){
			this.driver = driver;
	}
	
		
		public void enterFirstName(String FirstName)
		{
			log.info("entering the First Name");
			driver.findElement(ELEMENT.FIRSTNAME.findby).sendKeys(FirstName);
		}
		
		public void enterLastName(String LastName)
		{
			log.info("entering the Last Name");
			driver.findElement(ELEMENT.LASTNAME.findby).sendKeys(LastName);
		}
		
		public void enterAddress(String Address)
		{
			log.info("entering the Billing Address");
			driver.findElement(ELEMENT.BILLINGADDRESS.findby).sendKeys(Address);
		}
		
		public void enterCCNumber(String CCNumber)
		{
			log.info("entering the CC Number");
			driver.findElement(ELEMENT.CCNO.findby).sendKeys(CCNumber);
		}
		
		public void selectCCType(String CCType)
		{
			log.info("clicking on your CCType Drop Down");
			new Select(driver.findElement(ELEMENT.CCTYPE.findby)).selectByVisibleText(CCType);
		}
		
		public void selectExpiryMonth(String Month)
		{
			log.info("clicking on your Month Drop Down");
			new Select(driver.findElement(ELEMENT.EXPMONTH.findby)).selectByVisibleText(Month);
		}
		
		public void selectExpiryYear(String Year)
		{
			log.info("clicking on your Year Drop Down");
			new Select(driver.findElement(ELEMENT.EXPYEAR.findby)).selectByIndex(9);
		}
		
		
		public void selectCVVNumber(String CVVNumber)
		{
			log.info("entering on your CVVNumber");
			driver.findElement(ELEMENT.CVV.findby).sendKeys(CVVNumber);
		}
		
		public void clickBookNow()
		{
			log.info("clicking Book Now Button");
			driver.findElement(ELEMENT.BOOKNOW.findby).click();
		}
		
}
