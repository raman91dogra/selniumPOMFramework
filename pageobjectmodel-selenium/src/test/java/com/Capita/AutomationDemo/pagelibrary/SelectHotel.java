package com.Capita.AutomationDemo.pagelibrary;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SelectHotel {

	WebDriver driver;
	static Logger log = Logger.getLogger(SignIn.class.getName());
	public enum ELEMENT {
		RADIOBUTTON(By.id("radiobutton_0")),
		CONTINUE(By.id("continue"));
	
		private By findby;
		private ELEMENT(By locator)
		{
			this.findby = locator;
		}
	}
	
	public SelectHotel(WebDriver driver){
		this.driver = driver;
	}
	
	/*
	 * This Method is created to select the First Radio Button option
	 */
	
	public void selectRadioButton1() {
		log.info("selecting the first Radiobutton");
		driver.findElement(ELEMENT.RADIOBUTTON.findby).click();
	}
	
	
	/*
	 * This Method is created to click Continue Button
	 */
	
	public void clickContinueButton()
	{
		log.info("selecting the Continue Button");
		driver.findElement(ELEMENT.CONTINUE.findby).click();
	}
}
