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
import com.lco.qa.pages.AgentWebHomePage;
import com.lco.qa.pages.AgentWebLoginPage;
import com.lco.qa.pages.BeneficiariesPage;
import com.lco.qa.pages.QuoteInformationPage;
import com.lco.qa.pages.PaymentPage;
import com.lco.qa.pages.PersonalPage;
import com.lco.qa.pages.ProcessPage;
import com.lco.qa.pages.ProductSelectionPage;
import com.lco.qa.pages.SignaturePage;
import com.lco.qa.pages.TemplatePage;
import com.lco.qa.util.Testutil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class CustomerQuoteTest extends TestBase {

	ProductSelectionPage productSelectionPage;
	QuoteInformationPage quoteInformationPage;
	ActionPage actionPage;
	ExtentTest extentTest;
	//PersonalPage personalPage;
	ProcessPage processPage;
	SignaturePage signaturePage;
	BeneficiariesPage beneficiariesPage;
	PaymentPage paymentPage;
	int rowNum = 2;

	// HashMap<String, String> inputData = new HashMap<String, String>();

	public CustomerQuoteTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		url = prop.getProperty("url");
		initialization(url);
		productSelectionPage = new ProductSelectionPage();
		quoteInformationPage = new QuoteInformationPage();
		//personalPage = new PersonalPage();
		processPage = new ProcessPage();
		beneficiariesPage = new BeneficiariesPage();
		signaturePage = new SignaturePage();
		paymentPage = new PaymentPage();
		actionPage = new ActionPage();
		
	
	

	}

	@DataProvider
	Object[][] getData() throws Exception {
		return Testutil.getTableArray(Testutil.TESTDATA_SHEET_PATH, "Quote", 5, 12, 5, 1);
	}

	@Test(enabled = false)
	public void actionPageTitleTest() {
		log.info(
				"****************************** Starting actionPageTitleTest test cases execution *****************************************");
		extentTest = extent.startTest("actionPageTitleTest");
		String title = actionPage.validateActionPageTitle();
		Assert.assertEquals(title, "LifeCo Insurance Company");
		log.info(
				"****************************** Ending actionPageTitleTest test cases execution *****************************************");
	}

	@Test(dataProvider = "getData", enabled = false)
	public void continueToApplication(String FirstName, String DateOfBirth, String Gender, String State,
			String tobaccoUse, String healthRate, String stateCode) {
		log.info(
				"****************************** Starting continueToApplication test cases execution *****************************************");
		extentTest = extent.startTest("actionPageTitleTest");
		DateOfBirth = DateOfBirth.replace(".", "/");
		quoteInformationPage.Quote(2, FirstName, "yug@sureify.com", DateOfBirth, Gender, State, tobaccoUse, healthRate);
		productSelectionPage.ProductSelection(2);
		System.out.println("product selected");
		productSelectionPage.FinalizeProductSelection(2);
		System.out.println("selected quote");
		actionPage.selectAction("Continue to application", Testutil.email_ID, Testutil.phoneNumber);

		String count = prop.getProperty("iterator");

		int itrCount = Integer.parseInt(count);
		processPage.ProcessFields(2, "self", itrCount, "DTC", "e-sign", "cc");

		log.info(
				"****************************** Ending continueToApplication test cases execution *****************************************");

	}

	@Test(dataProvider = "getData", enabled = true)
	public void selfQuote(String Num, String FirstName, String DateOfBirth, String Gender, String State,
			String tobaccoUse, String healthRate, String distribution, String product, String requestType,
			String signType, String paymentMethod) {
		log.info(
				"****************************** Starting selfQuote test cases execution *****************************************");
		extentTest = extent.startTest("selfQuote");

		DateOfBirth = DateOfBirth.replace(".", "/");

		/*
		 * onePersonGatherInfoPage.Quote(2, FirstName, DateOfBirth, Gender,
		 * State, tobaccoUse, healthRate);
		 * productSelectionPage.ProductSelection();
		 * System.out.println("product selected");
		 * productSelectionPage.FinalizeProductSelection();
		 * System.out.println("selected quote");
		 * actionPage.selectAction(requestType, Testutil.email_ID);
		 */

		quoteInformationPage = quoteInformationPage.Quote(rowNum, FirstName, "yug@sureify.com", DateOfBirth, Gender, State,
				tobaccoUse, healthRate);

		Assert.assertNotNull(quoteInformationPage);
		
		System.out.println("Quote info collected");
		
		//Assert.assertNull(productSelectionPage);
		
		//Assert.assertNotEquals(actual1, actual2);
		
		//Assert.assertNotEquals(quoteInformationPage, null);

		productSelectionPage = productSelectionPage.ProductSelection(2);

		Assert.assertNotNull(productSelectionPage);
		
		//Assert.assertNotEquals(productSelectionPage, null);

		System.out.println("product selected");

		productSelectionPage = productSelectionPage.FinalizeProductSelection(2);
		

		Assert.assertNotNull(productSelectionPage);
		
		System.out.println("Finalized Product Selection");
		//Assert.assertNotEquals(productSelectionPage, null);

		actionPage = actionPage.selectAction(requestType, Testutil.email_ID, Testutil.phoneNumber);

		Assert.assertNotNull(actionPage);
		
		System.out.println("Action taken");
		
		//Assert.assertNotEquals(actionPage, null);

		Testutil.updateResult(Testutil.resultSheet, "Quote", rowNum, "Pass");

		String count = prop.getProperty("iterator");
		int itrCount = Integer.parseInt(count);
		
		
		processPage.ProcessFields(rowNum, distribution.toLowerCase(), itrCount, distribution, signType,
				paymentMethod.toLowerCase());

		log.info(
				"****************************** Ending selfQuote test cases execution *****************************************");
		rowNum++;
	}

	@AfterMethod
	public void tearDown(ITestResult result) throws IOException {

		if (result.getStatus() == ITestResult.FAILURE) {
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getName()); // to
																						// add
																						// name
																						// in
																						// extent
																						// report
			extentTest.log(LogStatus.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to
																							// add
																							// error/exception
																							// in
																							// extent
																							// report

			String screenshotPath = Testutil.getScreenshot(driver, result.getName());
			extentTest.log(LogStatus.FAIL, extentTest.addScreenCapture(screenshotPath)); // to
																							// add
																							// screenshot
																							// in
																							// extent
																							// report
			// extentTest.log(LogStatus.FAIL,
			// extentTest.addScreencast(screenshotPath)); //to add
			// screencast/video in extent report
		} else if (result.getStatus() == ITestResult.SKIP) {
			extentTest.log(LogStatus.SKIP, "Test Case SKIPPED IS " + result.getName());
		} else if (result.getStatus() == ITestResult.SUCCESS) {
			extentTest.log(LogStatus.PASS, "Test Case PASSED IS " + result.getName());

		}

		extent.endTest(extentTest); // ending test and ends the current test and
									// prepare to create html report
		// driver.quit();
	}

}
