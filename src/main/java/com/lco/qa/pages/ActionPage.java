package com.lco.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lco.qa.base.TestBase;

public class ActionPage extends TestBase{
	
	//Page Factory - OR
		@FindBy(xpath="")
		WebElement xyz;
		
		@FindBy(css=".test")
		WebElement selectItem;
		
		@FindBy(css=".next-action-img-container .hidden-xs")
		WebElement continueToApp;
		
		@FindBy(css=".hidden-xs.c-button-default")
		WebElement nextButton;
		
		@FindBy(name="email-1")
		WebElement emailAddress;
		
		@FindBy(xpath="//button[contains(text(), 'SUBMIT')]")
		WebElement submitButton;
		
		
		
		
		
	//Initialize the Page objects
	public ActionPage(){
		PageFactory.initElements(driver, this);
		
	}	
	
	//Actions:
	
	public String validateActionPageTitle() {
		return driver.getTitle();
	}
	
	public ActionPage selectAction(String actionType, String email) {
		
		
		//driver.findElement(By.xpath(""));
		//driver.findElement(By.cssSelector("")).click();
		
		switch (actionType) {
		case "Continue to application":

			/*if (FieldType(elementF, "text")) {
				elementF.sendKeys("999");
				System.out.println("typed number");
			}*/
			
			//driver.findElement(By.cssSelector(".next-action-img-container .hidden-xs")).click();
			continueToApp.click();
			break;
			
		default:
			//elementF.sendKeys("default");
			break;

		}
		
		nextButton.click();
		emailAddress.sendKeys(email);
		submitButton.click();
		
		
		
		
		return new ActionPage();
		
	}

}
