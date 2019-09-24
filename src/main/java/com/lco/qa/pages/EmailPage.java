package com.lco.qa.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.lco.qa.base.TestBase;
import com.lco.qa.util.Testutil;

public class EmailPage extends TestBase {

	// Page Factory - OR
	@FindBy(xpath = "")
	WebElement xyz;

	@FindBy(css = ".test")
	WebElement selectItem;

	// Initialize the Page objects
	public EmailPage() {
		PageFactory.initElements(driver, this);

	}

	// Actions:

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	

	public EmailPage loginGmail(int rowNum) {
		String currentURL = "";
		try {
			url = prop.getProperty("gmail_url");
			initialization(url);
			
			currentURL = driver.getCurrentUrl();
			WebElement email_phone = driver.findElement(By.xpath("//input[@id='identifierId']"));
			String gmail_uid = prop.getProperty("gmail_uid");
			String gmail_pwd = prop.getProperty("gmail_pwd");
			email_phone.sendKeys(gmail_uid);
			driver.findElement(By.id("identifierNext")).click();
			WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
			// WebDriverWait wait = new WebDriverWait(driver, 10*Testutil.waitTime);
			TestBase.wait.until(ExpectedConditions.elementToBeClickable(password));
			password.sendKeys(gmail_pwd);
			driver.findElement(By.id("passwordNext")).click();

			// some optional actions for reaching gmail inbox
			driver.findElement(By.xpath("//a[@aria-label='Google apps']")).click();
			driver.findElement(By.xpath("//span[contains(text(),'Gmail')]")).click();

			

			// Perform the actions on new window

			// Close the new window, if that window no more required
			// driver.close();

			// Switch back to original browser (first window)
			// driver.switchTo().window(winHandleBefore);

			return new EmailPage();
		} catch (Exception e) {
			System.out.println("other exception, Unable to open the email, failed");
			Testutil.updateResult(Testutil.resultSheet, "Launch From Email", rowNum, "Fail");
			Testutil.updateResult(Testutil.resultSheet, "URL", rowNum, currentURL);
			Testutil.updateResult(Testutil.resultSheet, "Comments", rowNum, "Unable to open the Inbox");
			return null;
		}

	}

	public EmailPage openVeryFirstEmail(int rowNum) {

		String currentURL = "";
		currentURL = driver.getCurrentUrl();

		try {
			String winHandleBefore = driver.getWindowHandle();

			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}

			System.out.println("switching the handle");
			List<WebElement> a = driver.findElements(By.xpath("//*[@class='yW']/span"));
			// List<WebElement> a = driver.findElements(By.name("Vantis Life"));
			// List<WebElement> a =
			// driver.findElements(By.xpath("//span[contains(text(),'Vantis Life')]"));
			// span[contains(text(),'Vantis Life')]
			System.out.println(a.size());
			a.get(0).click();
			/*
			 * for (int i = 0; i < a.size(); i++) { System.out.println(a.get(i).getText());
			 * if (a.get(i).getText().equals(from)) //to click on a specific mail. {
			 * a.get(i).click(); } }
			 */
			return new EmailPage();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());

			System.out.println("other exception, Unable to open the email, failed");
			Testutil.updateResult(Testutil.resultSheet, "Launch From Email", rowNum, "Fail");
			Testutil.updateResult(Testutil.resultSheet, "URL", rowNum, currentURL);
			Testutil.updateResult(Testutil.resultSheet, "Comments", rowNum, "Unable to open the email from the Inbox");
			return null;

		}
	}

	public EmailPage LaunchFromEmail(int rowNum, String signType, String resultMsg) {

		

		String currentURL = "";
		

		try {

			loginGmail(2);
			currentURL = driver.getCurrentUrl();
			Testutil.staticWait();
			openVeryFirstEmail(2);
			
			String winHandleBefore = driver.getWindowHandle();

			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}

			System.out.println("switching the handle");

			System.out.println("clicking on the inbox img ... ");

			Testutil.WaitnClick(By.xpath("//img[@class='ajT']"));

			// driver.findElement(By.xpath("//img[@class='ajT']")).click();

			System.out.println("clicking on the " + signType + " ... ");

			// driver.findElement(By.xpath("//a[contains(text(),'REVIEW AND
			// SIGN')]")).click();

			Testutil.findElements(By.xpath("//a[contains(text(),'" + signType + "')]"));
			Testutil.staticLongWait();
			


			// Testutil.updateResult(Testutil.resultSheet, "URL", rowNum, currentURL);
			return new EmailPage();
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
			System.out.println("other exception, " + resultMsg + " failed");
			Testutil.updateResult(Testutil.resultSheet, resultMsg, rowNum, "Fail");
			Testutil.updateResult(Testutil.resultSheet, "URL", rowNum, currentURL);
			return null;

		}

	}

}
