package com.lco.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lco.qa.base.TestBase;
import com.lco.qa.util.ProductUtil;
import com.lco.qa.util.Testutil;

public class ActionPage extends TestBase{
	
	//Page Factory - OR
		@FindBy(xpath="")
		WebElement xyz;
		
		@FindBy(css=".test")
		WebElement selectItem;
		
		//@FindBy(css=".next-action-img-container .hidden-xs")
		//@FindBy(css=".next-action-application-img-container:nth-child(1) .col-xs-7")
		@FindBy(css=".next-action-application-img-container:nth-child(1) .hidden-xs")		
		WebElement continueToApp;
		
		@FindBy(css=".confirmation-email-desc")		
		WebElement emailDesc;	
		
		@FindBy(css=".close > span:nth-child(1)")		
		WebElement closeButton;			
		
		
		@FindBy(css=".hidden-xs.c-button-default")
		WebElement nextButton;
		
		@FindBy(name="email-1")
		WebElement emailAddress;
		
		@FindBy(name="phone-number")
		WebElement phoneNo;
		
		@FindBy(xpath="//button[contains(text(), 'SUBMIT')]")
		WebElement submitButton;
		
				
		@FindBy(xpath="//div[@class='agent-next-step-container']//*//label")
		WebElement completetheApp;
		
			
		
	//Initialize the Page objects
	public ActionPage(){
		PageFactory.initElements(driver, this);
		
	}	
	
	//Actions:
	
	public String validateActionPageTitle() {
		return driver.getTitle();
	}
	
	public ActionPage selectAction(String actionType, String email, String phoneNumber) {
		
		
		
		try{
			//driver.findElement(By.xpath(""));
			//driver.findElement(By.cssSelector("")).click();
			By actionTypeElement;
			
			switch (actionType) {
			case "Continue to application":				
				
				//driver.findElement(By.cssSelector(".next-action-img-container .hidden-xs")).click();
				//continueToApp.click();
				actionTypeElement = By.cssSelector(".next-action-application-img-container:nth-child(1) .hidden-xs");
				Testutil.WaitnClick(actionTypeElement);
				break;
				
			case "Email me quote":				
				
				//driver.findElement(By.cssSelector(".next-action-img-container .hidden-xs")).click();
				//continueToApp.click();
				actionTypeElement = By.cssSelector(".next-action-application-img-container:nth-child(2) .hidden-xs");
				Testutil.WaitnClick(actionTypeElement);
				break;	
				
			case "Connect me to a licensed agent":				
				
				//driver.findElement(By.cssSelector(".next-action-img-container .hidden-xs")).click();
				//continueToApp.click();
				actionTypeElement = By.cssSelector(".next-action-application-img-container:nth-child(3) .hidden-xs");
				Testutil.WaitnClick(actionTypeElement);
				break;
				
			case "Complete the application":

					completetheApp.click();
					break;	
				
				
			default:
				//elementF.sendKeys("default");
				break;

			}
			
			nextButton.click();
			emailAddress.sendKeys(email);
			
			phoneNo.sendKeys(phoneNumber);
			
			
			
			//String currenURL = driver.getCurrentUrl();
			//driver.navigate().to(currenURL);
			
			//driver.get("https://linkedin.com")
			String winHandleBefore = driver.getWindowHandle();
			
			submitButton.click();
			
			ProductUtil.CheckElementDoNotExists(".fa.fa-circle-o-notch", true);

			
			switch (actionType) {
				case "Email me quote":	
				case "Connect me to a licensed agent":					
					//By emailDesc = By.cssSelector(".confirmation-email-desc");
					
					//WebElement textMsg = driver.findElement(emailDesc);	
					
					String returnText = emailDesc.getText().toString();
					
					//String returnText = textMsg.getText().toString();
					
					System.out.println("before returnText: " + returnText);
					
					closeButton.click();
					break;
					
					//driver.quit();
					
				default:
					//elementF.sendKeys("default");
					break;
			
			}
			
			
			
			switch (actionType) {
				case "Email me quote":	
				case "Connect me to a licensed agent":					
						
					driver.close();
					break;
					
				case "Continue to application":	
					// Perform the click operation that opens new window

					// Switch to new window opened
					for(String winHandle : driver.getWindowHandles()){
					    driver.switchTo().window(winHandle);
					}
					
					System.out.println("switching the handle");

					// Perform the actions on new window

					// Close the new window, if that window no more required
					//driver.close();

					// Switch back to original browser (first window)
					driver.switchTo().window(winHandleBefore);
					break;
					
				default:
					//elementF.sendKeys("default");
					break;
			}
			
			return new ActionPage();
			
			
			
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println("other exception, Quote failed, Unable to select the any Action");
			return null; 
			
		}
		
		
		
	}

}
