package com.lco.qa.testcases;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;

import org.testng.annotations.BeforeMethod;

import org.testng.annotations.Test;



//import com.crm.qa.pages.HomePage;

import com.lco.qa.base.TestBase;
import com.lco.qa.pages.DemoPage;
import com.lco.qa.util.Testutil;
import com.lco.qa.util.Xlsutil;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class DemoTest extends TestBase {

	DemoPage demoPage;
	ExtentTest extentTest;

	

	public DemoTest() {
		super();
	}

	@BeforeMethod
	public void setup() {
		initialization();
		demoPage = new DemoPage();
	}

	@Test(enabled = false)
	public void loginPageTitleTest() {
		
		
		log.info("****************************** Starting loginPageTitleTest test cases execution *****************************************");
		extentTest = extent.startTest("loginPageTitleTest");
		String title = demoPage.validateLoginPageTitle();
		Assert.assertEquals(title, "OrangeHRM");
		log.info("****************************** Ending loginPageTitleTest test cases execution *****************************************");
	}
	
	@Test
	public void Test1(){
		
		
		
	}
	
	
	
	

	@Test(enabled = false)
	public void Test() throws ParseException {
		//demoPage = demoPage.SampleTest(prop.getProperty("username"), prop.getProperty("password"));
		
		extentTest = extent.startTest("Test");
	
		int arr[] = {1, 2, 3, 4, 5}; 
        int r = 3; 
        int n = arr.length; 
        
        for (int i=1;i<=n; i++)
        	printCombination(arr, n, i);
        
        HashMap<Integer, String> ec = ProductList();
		
        for(int i=0; i<ec.size(); i++)		//returns null
			System.out.println(ec.get(i));
		
	}
	
	
	
	
	
	
	static HashMap<Integer, String> ProductList() {
		
		HashMap<Integer, String> ec = new HashMap<Integer, String>();
		
		ec.put(1, "OK");		
		ec.put(2, "authentication error");
		ec.put(3, "service no found");		
		ec.put(4, "Not found");
		
		
		
		
		
		
		
		return ec;
	}
	
	/* arr[]  ---> Input Array 
    data[] ---> Temporary array to store current combination 
    start & end ---> Staring and Ending indexes in arr[] 
    index  ---> Current index in data[] 
    r ---> Size of a combination to be printed */
    static void combinationUtil(int arr[], int data[], int start, 
                                int end, int index, int r) 
    { 
        // Current combination is ready to be printed, print it 
        if (index == r) 
        { 
            for (int j=0; j<r; j++) 
                System.out.print(data[j]+" "); 
            System.out.println(""); 
            return; 
        } 
  
        // replace index with all possible elements. The condition 
        // "end-i+1 >= r-index" makes sure that including one element 
        // at index will make a combination with remaining elements 
        // at remaining positions 
        for (int i=start; i<=end && end-i+1 >= r-index; i++) 
        { 
            data[index] = arr[i]; 
            combinationUtil(arr, data, i+1, end, index+1, r); 
        } 
    } 
  
    // The main function that prints all combinations of size r 
    // in arr[] of size n. This function mainly uses combinationUtil() 
    static void printCombination(int arr[], int n, int r) 
    { 
        // A temporary array to store all combination one by one 
        int data[]=new int[r]; 
  
        // Print all combination using temprary array 'data[]' 
        combinationUtil(arr, data, 0, n-1, 0, r); 
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
		driver.quit();
	}

}
