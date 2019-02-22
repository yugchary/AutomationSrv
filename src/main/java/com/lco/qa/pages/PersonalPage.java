package com.lco.qa.pages;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.util.SystemOutLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;

import com.lco.qa.base.TestBase;
import com.lco.qa.util.ProductUtil;
import com.lco.qa.util.Testutil;
import com.lco.qa.util.Xlsutil;

public class PersonalPage extends TestBase {

	// Page Factory - OR
	@FindBy(xpath = "")
	WebElement xyz;

	@FindBy(css = ".test")
	WebElement test;

	@FindBy(css = ".Select-placeholder")
	WebElement dropdown;

	// @FindBy(xpath = "//div[@id='react-select-.*--option-0']")
	// WebElement selectItem;

	@FindBy(css = ".Select-option")
	// @FindBy(xpath="//div[@class='Select-option is-focused']")
	// #react-select-2--option-0
	WebElement selectItem;

	@FindBy(css = ".autosuggest-input-choice")
	WebElement autoSugg;

	@FindBy(css = ".pseudofocused")
	WebElement selectAutoSugg;

	@FindBy(xpath = "//input[@placeholder='MM/DD/YYYY']")
	WebElement DOB;

	@FindBy(xpath = "//div[@class='react-datepicker__week']//div[@aria-label='day-12']")
	WebElement dateSelect;

	@FindBy(xpath = "//div[contains(text(),'State')]//following-sibling::*//div[@class='Select-multi-value-wrapper']//input")
	WebElement stateValue;

	@FindBy(css = ".Select-option.is-focused")
	WebElement selectItem1;

	@FindBy(xpath = "//div[@class='c-submit-person-info-btn c-center']//button[1]")
	// @FindBy(xpath="//div[@class='c-submit-person-info-bftn
	// c-center']//button[@class='c-button-default.visible.btn-default']")
	// button[@class='c-button-default circular hidden-xs active btn
	// btn-default']
	WebElement nextBtn;

	@FindBy(css = ".single-select-btn-container .c-button-default")
	WebElement yesButton;

	@FindBy(css = "button[class='c-button-default circular single-select-btn  btn btn-default']")
	WebElement noButton;

	HashMap<String, String> inputData = new HashMap<String, String>();

	// Initialize the Page objects
	public PersonalPage() {
		PageFactory.initElements(driver, this);

	}

	// Actions:

	public String validateLoginPageTitle() {
		return driver.getTitle();
	}

	public PersonalPage SampleTest(String un, String pwd) {

		String url = "https://vantislifeinsurancestg.sureify.com/questions?user=bmtwR3Y3VnZadE5NLy83SkkxbG1vQT09&text_accepted=No&vdtca&transaction_id=13a55130-324e-11e9-aff3-8fff83072c1a_1550364652995&ipAddress=192.168.1.110&timezoneOffset=-330&timezoneFormatted=GMT%200530%20(India%20Standard%20Time)&currentTime=1550364678937&q_id=d3B1RlpsUUpMNkdYY2Y0MStFQ1Nydz09&transaction_id=13a55130-324e-11e9-aff3-8fff83072c1a_1550364652995&auth_code=Lq5nLFCGGzXq6dOb1ZkTO7vx1Wc4lM";

		url = "https://vantislifeinsurancestg.sureify.com/questions?user=cFdMUWl1dmZBMldaa1JYS2ZndnA1dz09&text_accepted=No&vdtca&transaction_id=988a9350-3538-11e9-8602-d7cbb2869fb4_1550685280517&ipAddress=192.168.1.121&timezoneOffset=-330&timezoneFormatted=GMT%200530%20(India%20Standard%20Time)&currentTime=1550685315199&q_id=TDZyakl5eFpuc1NhUElLNGMzTjFIQT09&transaction_id=988a9350-3538-11e9-8602-d7cbb2869fb4_1550685280517&auth_code=naETd0RayyHxUYRnhMAWPVzlmTqag7";
		driver.navigate().to(url);

		// for(int i=0;i<5; i++)
		FillTextFields();

		return new PersonalPage();

	}

	public void FillTextFields() {

		By byFormL = By.cssSelector(".form-group");
		By byCheckboxL = By.cssSelector(".custom-checkbox-container");
		By byDropdownL = By.cssSelector(".c-subheader-text.fs18");
		By byButtonL = By.cssSelector(".c-subheader-text.fs18.col-sm-12");
		By byAutoSuggL = By.cssSelector(".c-subheader-text.fs18.col-sm-12");
		By byDatepickerL = By.cssSelector(".c-subheader-text.fs18.col-sm-12");
		

		By byFormF = By.cssSelector(".form-group .field .form-control");
		By byCheckboxF = By.cssSelector(".custom-checkbox-container .custom-checkbox-checkmark");

		// By byButtonF = By.xpath("//button[contains(text(), 'Yes')]");

		// By byDropdownF =

		By byDropdownF = By.cssSelector(".Select-placeholder");
		By byButtonF = By.cssSelector(".single-select-btn-container .c-button-default");

		By byNoButtonF = By.cssSelector("button[class='c-button-default circular single-select-btn  btn btn-default']");

		By byautoSuggF = By.xpath("//input[@autocomplete='off']");
		By byDatepickerF = By.cssSelector(".react-datepicker-wrapper");

		inputData = ProductUtil.GetInputData("Questions");

		int formFlag = 0;
		int checkboxFlag = 0;
		int buttonFlag = 0;
		int dropDownFlag = 0;
		int autoSuggFlag = 0;
		int datePickerFlag = 0;
		int i = 0;
		int counter = 0;
		String pageType = "def";
		boolean flag = false, breakFlag = true;

		do {

			if (i == 0) {
				System.out.println("checking for autoSugg fields");
				autoSuggFlag = CheckWebElements(byautoSuggF);
			}
			if (autoSuggFlag > 0) {
				pageType = "autoSugg";
				System.out.println("This is a page filled with " + pageType);
				//checkPage(CheckNextElement(byautoSuggF), byautoSuggF, pageType);
				breakFlag = checkPage(CheckNextElement(autoSuggFlag, byAutoSuggL), CheckNextElement(autoSuggFlag, byautoSuggF),
						byAutoSuggL, byautoSuggF, pageType);
			}

			if (i == 0) {
				System.out.println("checking for datepicker fields");
				datePickerFlag = CheckWebElements(byDatepickerF);
			}
			if (datePickerFlag > 0) {
				pageType = "datepicker";
				System.out.println("This is a page filled with " + pageType);
				//checkPage(CheckNextElement(byDatepickerF), byDatepickerF, pageType);
				breakFlag = checkPage(CheckNextElement(datePickerFlag, byDatepickerL), CheckNextElement(datePickerFlag, byDatepickerF),
						byDatepickerL, byDatepickerF, pageType);
			}

			if (i == 0) {
				System.out.println("checking for checkbox fields");
				checkboxFlag = CheckWebElements(byCheckboxL);
			}
			if (checkboxFlag > 0) {
				pageType = "checkbox";
				System.out.println("This is a page filled with " + pageType);
				breakFlag = checkPage(CheckNextElement(checkboxFlag, byCheckboxL), CheckNextElement(checkboxFlag, byCheckboxF),
						byCheckboxL, byCheckboxF, pageType);
			}

			if (i == 0) {
				System.out.println("checking for form fields");
				formFlag = CheckWebElements(byFormL);
			}
			if (formFlag > 0) {
				pageType = "form";
				System.out.println("This is a page filled with " + pageType);
				breakFlag = checkPage(CheckNextElement(formFlag, byFormL), CheckNextElement(formFlag, byFormF), byFormL, byFormF,
						pageType);
			}

			if (i == 0) {
				pageType = "button";
				System.out.println("checking for button fields");
				buttonFlag = CheckWebElements(byButtonF, pageType);
			}
			if (buttonFlag > 0) {
				pageType = "button";
				System.out.println("This is a page filled with " + pageType);
				// checkPage(CheckNextElement(byButtonF), byButtonF, pageType);
				breakFlag = checkPage(CheckNextElement(buttonFlag, byButtonL), CheckNextElement(buttonFlag, byButtonF), byButtonL,
						byButtonF, pageType);
			}

			if (i == 0) {
				System.out.println("checking for dropdown fields");
				dropDownFlag = CheckWebElements(byDropdownF);
			}
			if (dropDownFlag > 0) {
				pageType = "dropdown";
				System.out.println("This is a page filled with " + pageType);
				// checkPage(CheckNextElement(byDropdownF), byDropdownF,
				// pageType);
				breakFlag = checkPage(CheckNextElement(dropDownFlag, byDropdownL), CheckNextElement(dropDownFlag, byDropdownF),
						byDropdownL, byDropdownF, pageType);
			}

			i++;
			System.out.println("All submited in page " + i + ", going to next page. Clicking on Next button");

			driver.findElement(By.xpath("//button[@class='c-button-default circular  action btn btn-default']"))
					.click();

			ProductUtil.CheckElementDoNotExists(".fa.fa-circle-o-notch", true);

			formFlag = 0;
			checkboxFlag = 0;
			buttonFlag = 0;
			dropDownFlag = 0;
			autoSuggFlag = 0;
			datePickerFlag = 0;

			System.out.println("checking for autoSugg fields");
			autoSuggFlag = CheckWebElements(byautoSuggF);

			System.out.println("checking for datepicker fields");
			datePickerFlag = CheckWebElements(byDatepickerF);

			System.out.println("checking for button fields");
			buttonFlag = CheckWebElements(byButtonF, "button");

			System.out.println("checking for dropdown fields");
			dropDownFlag = CheckWebElements(byDropdownF);

			System.out.println("checking for form fields");
			formFlag = CheckWebElements(byFormL);

			System.out.println("checking for checkbox fields");
			checkboxFlag = CheckWebElements(byCheckboxL);

			if (formFlag == 0 && checkboxFlag == 0 && buttonFlag == 0 && dropDownFlag == 0 && autoSuggFlag == 0
					&& datePickerFlag == 0 && counter == 1) {
				counter++;
				flag = true;
			}
			
			if(!breakFlag) break;
			

		} while (formFlag > 0 || checkboxFlag > 0 || buttonFlag > 0 || dropDownFlag > 0 || autoSuggFlag > 0
				|| datePickerFlag > 0 || flag == true);

		//
		// return new PersonalPage();

	}

	public int CheckWebElements(By by) {

		List<WebElement> listWebElementLabel = driver.findElements(by);

		System.out.println("found webelements: " + listWebElementLabel.size());

		return listWebElementLabel.size();

		/*
		 * if (listWebElementLabel.size() > 0) { return true; } else return
		 * false;
		 */

	}

	public int CheckWebElements(By by, String elementType) {

		List<WebElement> listWebElementLabel = driver.findElements(by);

		if (elementType == "button")
			listWebElementLabel = driver.findElements(by);

		System.out.println("found webelements: " + listWebElementLabel.size());

		return listWebElementLabel.size();

	}

	public Iterator<WebElement> CheckNextElement(int size, By byF) {

		if (size > 0) {
			List<WebElement> listWebElementField = driver.findElements(byF);
			System.out.println(listWebElementField.size());
			return listWebElementField.iterator();
		} else
			return null;

	}

	public Iterator<WebElement> CheckNextElement(By byF) {

		List<WebElement> listWebElementField = driver.findElements(byF);
		System.out.println(listWebElementField.size());
		if (listWebElementField.size() > 0) {
			return listWebElementField.iterator();
		} else
			return null;

	}

	public boolean checkPage(Iterator<WebElement> l, Iterator<WebElement> f, By byL, By byF, String pageType) {

		int i = 0;
		boolean flag = true;

		while (l.hasNext() && f.hasNext()) {
			i++;

			if (pageType == "checkbox" && i == 1)
				flag = FillPage(l, f, i, pageType);

			if (pageType != "checkbox")
				flag = FillPage(l, f, i, pageType);
			else {
				f.next();
				l.next();
			}
			

		}
		return flag;

	}

	public void checkPage(Iterator<WebElement> f, By byF, String pageType) {

		int i = 0;

		while (f.hasNext()) {
			i++;

			FillPage(f, i, pageType);

		}

	}

	public boolean FillPage(Iterator<WebElement> l, Iterator<WebElement> f, int i, String pageType) {

		String FieldType, FieldValue = null, breakPoint = null;
		WebElement elementL = l.next();
		WebElement elementF = f.next();

		// String returnText =element.getAttribute("type").toString();

		String returnText = elementL.getText().toString();
		System.out.println("returnText: " + returnText);

		int len = returnText.length();

		if (returnText.charAt(0) == '*' && len > 1)
			returnText = returnText.substring(len - (len - 1));

		// String value = Testutil.getFromXls("Questions", "Question",
		// returnText);

		String value = Testutil.getFromHashMap(inputData, returnText);
		System.out.println("value:" + value);
		
		boolean nullValue = Testutil.isNullOrEmpty(value);
		

		if (value == "" || nullValue) {

			//if (returnText.isEmpty())
				returnText = pageType;

			// t.next().click();
			System.out.println("i value: " + i + returnText);

			if (pageType == "checkbox")
				returnText = "checkbox";

			FieldType = IdentifyWebElementType(elementL, returnText);

		} else {
			String inputValues[] = value.split("_");

			// System.out.println(Testutil.getFromHashMap(inputData, "First
			// Name"));
			
			if (inputValues.length>2){
				breakPoint = inputValues[2];
				System.out.println(inputValues[0] + "and" + inputValues[1] + "and" +  inputValues[2]);
			}

			System.out.println(inputValues[0] + "and" + inputValues[1]);
			FieldType = inputValues[0];
			FieldValue = inputValues[1];
			
			
			nullValue = Testutil.isNullOrEmpty(breakPoint);
			
			if(nullValue)
				return false;

			if (FieldValue == "No")
				elementF = noButton;
			
			int len1 = FieldValue.length();
			
			if (FieldValue.contains("."))
				FieldValue = FieldValue.substring(0,len1-2);

		}

		FillData(elementF, FieldType, FieldValue);
		
		return true;

	}

	public void FillPage(Iterator<WebElement> f, int i, String pageType) {

		String FieldType, FieldValue = null;

		WebElement elementF = f.next();

		// String returnText =element.getAttribute("type").toString();
		String returnText = elementF.getText().toString();
		System.out.println("returnText: " + returnText);

		int len = returnText.length();

		if (returnText.charAt(0) == '*' && len > 1)
			returnText = returnText.substring(len - (len - 1));

		// String FieldValue = Testutil.getFromXls("Questions", "Question",
		// returnText);

		// t.next().click();
		System.out.println("i value: " + i + pageType);

		String value = Testutil.getFromHashMap(inputData, returnText);
		System.out.println("value:" + value);

		if (value == "" || value.contentEquals("_")) {

			if (returnText.isEmpty())
				returnText = pageType;

			// t.next().click();
			System.out.println("i value: " + i + returnText);

			FieldType = IdentifyWebElementType(elementF, returnText);

			// FieldType = IdentifyWebElementType(elementF, pageType);

		} else {
			String inputValues[] = value.split("_");

			// System.out.println(Testutil.getFromHashMap(inputData, "First
			// Name"));

			System.out.println(inputValues[0] + "and" + inputValues[1]);
			FieldType = inputValues[0];
			FieldValue = inputValues[1];

			// if(FieldValue == "Yes")
			
			int len1 = FieldValue.length();
			
			if (FieldValue.contains("."))
				FieldValue = FieldValue.substring(0,len1-2);

		}

		FillData(elementF, FieldType, FieldValue);

	}

	public String IdentifyWebElementType(WebElement elementL, String returnText) {

		String FieldType;
		switch (returnText) {

		case "*First Name":
		case "First Name":
			FieldType = "First Name";
			System.out.println("This is a text field WebElement " + returnText);
			break;

		case "*Phone (HOME/CELL)":
		case "Phone (WORK)":
			FieldType = "Phone";
			System.out.println("This is a text field WebElement " + returnText);
			break;
		case "*Zip":
		case "*ZIP":
		case "Zip":
		case "ZIP":
		case "*Annual Income":
		case "*What is your annual household income?":
			FieldType = "Number";
			System.out.println("This is a ZIP WebElement " + returnText);
			break;

		case "*Social Security #":
			FieldType = "SSN";
			System.out.println("This is a SSN WebElement " + returnText);
			break;
		case "*Driver's license #":
		case "Driver's license #":
			FieldType = "DL";
			System.out.println("This is a SSN WebElement " + returnText);
			break;
		case "City":
			FieldType = "Text";
			System.out.println("This is a City WebElement " + returnText);
			break;
		case "checkbox":
			FieldType = "Checkbox";
			System.out.println("This is a Checkbox WebElement " + returnText);
			break;
		case "button":
			FieldType = "Button";
			System.out.println("This is a Button WebElement " + returnText);
			break;
		case "dropdown":
			FieldType = "Dropdown";
			System.out.println("This is a Dropdown WebElement " + returnText);
			break;
		case "autoSugg":
		case "AutoSugg":
			FieldType = "AutoSugg";
			System.out.println("This is a Dropdown WebElement " + returnText);
			break;
		case "datepicker":
			FieldType = "DatePicker";
			System.out.println("This is a Dropdown WebElement " + returnText);
			break;

		default:
			FieldType = "Text";
			System.out.println("This is a default  WebElement " + returnText);
			break;

		}
		return FieldType;
	}

	public void FillData(WebElement elementF, String FieldType, String FieldValue) {
		
		boolean nullValue = Testutil.isNullOrEmpty(FieldValue);
		switch (FieldType) {
		case "Text":
			if(nullValue)
				elementF.sendKeys("ab");
			else
				elementF.sendKeys(FieldValue);
			break;

		case "First Name":
			// elementF.sendKeys("12345");
			if(nullValue)
				elementF.sendKeys("12345");
			else
				elementF.sendKeys(FieldValue);
			break;
		case "Phone":
			if (EnterInputData
					(elementF, "9999999999")) {
				System.out.println("typed text");
				// elementF.sendKeys("text");
				if(nullValue)
					elementF.sendKeys("ab");
				else
					elementF.sendKeys(FieldValue);
			}

			break;
		case "Number":
			// elementF.sendKeys("12345");
			if(nullValue)
				elementF.sendKeys("12345");
			else
				elementF.sendKeys(FieldValue);
			
			break;
		case "SSN":
			// elementF.sendKeys("222222222");
			if(nullValue)
				elementF.sendKeys("222222222");
			else
				elementF.sendKeys(FieldValue);
			break;
		case "DL":
			// elementF.sendKeys("753475837");
			if(nullValue)
				elementF.sendKeys("753475837");
			else
				elementF.sendKeys(FieldValue);			
			break;

		case "Dropdown":
		case "DropDown":
			// dropdown.click();
			// stateValue.sendKeys("Alabama (AL)");
			if(nullValue)
				selectItem.click();
			else
				stateValue.sendKeys(FieldValue);
			
			selectItem.click();
			// .Select-placeholder
			//
			// selectItem.click();

			break;
		case "Checkbox":
			System.out.println("Check box clicked");
			elementF.click();
			break;
		case "Button":
			System.out.println("Button clicked");
			elementF.click();
			break;
		case "MultiSelection":
			break;
		case "AutoSugg":
			autoSugg.click();			
			if(nullValue)
				elementF.sendKeys("ab");
			else
				elementF.sendKeys(FieldValue);
			
			try {
				Thread.sleep(Testutil.waitTime);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// selectAutoSugg.click();
			// driver.findElement(By.xpath("//ul[@class='dropdown-menu']//li[1]//a//div")).click();
			// driver.findElement(By.xpath("//ul[@class='dropdown-menu']//li[1]//a//div")).submit();
			selectOptionWithIndex(1);

			break;
		case "DatePicker":
			// DOB.sendKeys("03/12/1979");
			if(nullValue)
				DOB.sendKeys("03/12/1979");
			else
				DOB.sendKeys(FieldValue);
			dateSelect.click();
			break;

		default:
			if (EnterInputData(elementF, "text")) {
				// elementF.sendKeys("999");
				if(nullValue)
					elementF.sendKeys("999");
				else
					elementF.sendKeys(FieldValue);
				System.out.println("typed number");
			}

			break;

		}

	}

	public boolean EnterInputData(WebElement ele, String text) {

		boolean returnType;
		ele.sendKeys(text);

		// Retrieve typed value
		String typedValue = ele.getAttribute("value");

		// Get the length of typed value
		int size = typedValue.length();

		if (size == 0) {
			System.out.println("Alphabets are not allowed.");
			returnType = true;
		} else
			returnType = false;

		return returnType;

	}

	public void selectOptionWithIndex(int indexToSelect) {

		try {
			WebElement autoOptions = driver.findElement(By.xpath("//ul[@class='dropdown-menu']"));
			// wait.until(ExpectedConditions.visibilityOf(autoOptions));

			List<WebElement> optionsToSelect = autoOptions.findElements(By.tagName("li"));
			if (indexToSelect <= optionsToSelect.size()) {
				System.out.println("Trying to select based on index: " + indexToSelect);
				optionsToSelect.get(indexToSelect).click();
			}
		} catch (NoSuchElementException e) {
			System.out.println(e.getStackTrace());
		} catch (Exception e) {
			System.out.println(e.getStackTrace());
		}
	}

	public void CheckElementExists(String css, boolean flag) {

		// boolean present = true;

		while (flag) {

			try {
				driver.findElement(By.cssSelector(css));
				try {
					System.out.println("waiting for " + Testutil.waitTime + " milliseconds");
					Thread.sleep(Testutil.waitTime);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				flag = false;
			} catch (NoSuchElementException e) {
				flag = true;
			}

		}

	}

	public void SignUP(){
		// String value =

				// driver.findElement(By.xpath("//div[starts-with(@class,'Add')]//following-sibling::*//button[contains(text(),
				// 'ADD PRIMARY BENEFICIARY')]")).click();

				
				 
				 
				 driver.findElement(By.
				 xpath("//button[contains(text(), 'ADD PRIMARY BENEFICIARY')]"));
				 
				 //driver.findElement(By.xpath(""));
				 
				 driver.findElement(By.cssSelector("div > button")).click();
				 //LoadingNextPage();
				 
				 driver.findElement(By.
				 xpath("//label[contains(text(), 'I agree to use electronic records and signatures.')]"
				 )).click();
				 
				 
				 System.out.println("clicked continue");
				 
				 driver.findElement(By.xpath("//button[@track='continue-button']")).
				 click();
				 
				 System.out.println("clicked finish/start");
				 
				 driver.findElement(By.xpath("//button[@track='top-finish-button']")).
				 click();
				 
				 System.out.println("clicked sign arrow");
				 
				 CheckElementExists(".signature-tab-content .tab-image-arrow", true);
				 
				 driver.findElement(By.
				 cssSelector(".signature-tab-content .tab-image-arrow")).click();
				 
				 System.out.println("clicked draw");
				 
				 driver.findElement(By.xpath("//button[contains(text(), 'Draw')]")).
				 click();
				 
				 //driver.findElement(By.xpath("//button[contains(text(), 'Adopt and Sign')]")).click();
				 
				 
				 //click esign //button[contains(text(), 'ESIGN AND SUBMIT')]
				 
				 
				 
				 WebElement wbCanvas =
				 driver.findElement(By.cssSelector(".signature-draw .canvas-wrapper"));
				 
				 
				 System.out.println("x:" + wbCanvas.getLocation());
				 
				 Actions actionBuilder=new Actions(driver); 
				 Action drawOnCanvas = actionBuilder.moveToElement(wbCanvas,97,331)				 	 
					 .clickAndHold(wbCanvas) 
					 .moveByOffset(10, 20)
					 //.click(wbCanvas) 
					 .moveByOffset(100,350) 
					 .release()
					 //.doubleClick(wbCanvas) 
					 .build(); 
				drawOnCanvas.perform();
				 
				 //driver.findElement(By.xpath("//button[contains(text(), 'Adopt and Sign')]")).click();
				 
				 
				 
	}

}
