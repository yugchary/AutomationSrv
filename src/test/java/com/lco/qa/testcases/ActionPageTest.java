package com.lco.qa.testcases;

import java.io.IOException;
import java.util.HashMap;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



//import com.crm.qa.pages.HomePage;

import com.lco.qa.base.TestBase;
import com.lco.qa.pages.ActionPage;
import com.lco.qa.pages.OnePersonGatherInfoPage;
import com.lco.qa.pages.PersonalPage;
import com.lco.qa.pages.ProductSelectionPage;
import com.lco.qa.pages.TemplatePage;
import com.lco.qa.util.Testutil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ActionPageTest extends TestBase {

	ProductSelectionPage productSelectionPage;
	OnePersonGatherInfoPage onePersonGatherInfoPage;
	ActionPage actionPage;
	ExtentTest extentTest;	
	PersonalPage personalPage;
	//HashMap<String, String> inputData = new HashMap<String, String>();

	

	public ActionPageTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		productSelectionPage = new ProductSelectionPage();
		onePersonGatherInfoPage = new OnePersonGatherInfoPage();
		personalPage = new PersonalPage();
		actionPage = new ActionPage();
		
	}
	
	@DataProvider
	Object[][] getData() throws Exception {
		return Testutil.getTableArray(Testutil.TESTDATA_SHEET_PATH,"Quote1");
	}

	@Test(enabled = false)
	public void actionPageTitleTest() {
		log.info("****************************** Starting actionPageTitleTest test cases execution *****************************************");
		extentTest = extent.startTest("actionPageTitleTest");
		String title = actionPage.validateActionPageTitle();
		Assert.assertEquals(title, "LifeCo Insurance Company");
		log.info("****************************** Ending actionPageTitleTest test cases execution *****************************************");
	}

	@Test(dataProvider = "getData", enabled = true)
	public void continueToApplication(String FirstName, String DateOfBirth, String Gender, String State, String tobaccoUse, String healthRate, String stateCode) {
		log.info("****************************** Starting continueToApplication test cases execution *****************************************");
		extentTest = extent.startTest("actionPageTitleTest");
		DateOfBirth = DateOfBirth.replace(".", "/");
		onePersonGatherInfoPage.Quote11(FirstName, DateOfBirth, Gender, State, tobaccoUse, healthRate, stateCode);
		productSelectionPage.ProductSelection();
		System.out.println("product selected");
		productSelectionPage.FinalizeProductSelection();	
		System.out.println("selected quote");
		actionPage.selectAction("Continue to application", Testutil.email_ID);
		personalPage.FillTextFields();
		
		log.info("****************************** Ending continueToApplication test cases execution *****************************************");

		
	}
	
	
	

	
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException{
		
		if(result.getStatus()==ITestResult.FAILURE){
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getName()); //to add name in extent report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS "+result.getThrowable()); //to add error/exception in extent report
			
			String screenshotPath = Testutil.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); //to add screenshot in extent report
			//extentTest.log(LogStatus.FAIL, extentTest.addScreencast(screenshotPath)); //to add screencast/video in extent report
		}
		else if(result.getStatus()==ITestResult.SKIP){
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		}
		else if(result.getStatus()==ITestResult.SUCCESS){
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}
		
		
		extent.endTest(extentTest); //ending test and ends the current test and prepare to create html report
		//driver.quit();
	}

}
