package com.Capita.AutomationDemo.pagelibrary;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import com.Capita.AutomationDemo.testbase.TestBase;

public class SearchHotel extends TestBase{

	WebDriver driver;
	static Logger log = Logger.getLogger(SignIn.class.getName());
	public enum ELEMENT {
		LOCATION(By.id("location")),
		HOTELS(By.id("hotels")),
		ROOMTYPE(By.id("room_type")),
		NUMBEROFROOMS(By.id("room_nos")),
		CHECKINDATE(By.id("datepick_in")),
		CHECKOUTDATE(By.id("datepick_out")),
		ADULTS(By.id("adult_room")),
		CHILDREN(By.id("child_room")),
		SUBMIT(By.id("Submit"));
	
		private By findby;
		private ELEMENT(By locator)
		{
			this.findby = locator;
		}
	}
	
	public SearchHotel(WebDriver driver){
		this.driver = driver;
	}
	
	/*
	 * This Method will select the Location Name from Dropdown
	 */
	public void selectYourLocation(String locationName) throws InterruptedException{
		log.info("clicking on your Location Drop Down");
		new Select(driver.findElement(ELEMENT.LOCATION.findby)).selectByVisibleText(locationName);

	}
	
	/*
	 * This Method will select the hotelname Name from Dropdown
	 */
	public void selectYourHotels(String hotelname) throws InterruptedException{
		log.info("clicking on your hotelname Drop Down");
		new Select(driver.findElement(ELEMENT.HOTELS.findby)).selectByVisibleText(hotelname);

	}
	
	/*
	 * This Method will select the roomtype from Dropdown
	 */
	public void selectYourRoomsType(String roomtype) throws InterruptedException{
		log.info("clicking on your roomtype Drop Down");
		new Select(driver.findElement(ELEMENT.ROOMTYPE.findby)).selectByVisibleText(roomtype);

	}
	
	/*
	 * This Method will select the roomCount Value from Dropdown
	 */
	public void selectYourRoomCount() {
		log.info("clicking on your roomCount Drop Down");
		new Select(driver.findElement(ELEMENT.NUMBEROFROOMS.findby)).selectByIndex(1);

	}
	
	/*
	 * This Method will select the adultCount Name from Dropdown
	 */
	public void selectAdults(){
		log.info("clicking on your adultCount Drop Down");
		new Select(driver.findElement(ELEMENT.ADULTS.findby)).selectByIndex(1);

	}
	
	/*
	 * This Method will select the children Name from Dropdown
	 */
	public void selectChidren(){
		log.info("clicking on your children Drop Down");
		new Select(driver.findElement(ELEMENT.CHILDREN.findby)).selectByIndex(2);

	}
	
	/*
	 * This Method will click the Search Button
	 */
	public void clickSearchButton() {
		log.info("clicking on your Search Button");
		driver.findElement(ELEMENT.SUBMIT.findby).click();

	}
	
}
