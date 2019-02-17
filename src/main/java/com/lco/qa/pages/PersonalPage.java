package com.lco.qa.pages;

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
import com.lco.qa.util.Testutil;

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

		driver.navigate().to(url);

		// for(int i=0;i<5; i++)
		FillTextFields();

		return new PersonalPage();

	}

	public void FillTextFields() {

		By byFormL = By.cssSelector(".form-group");
		By byCheckboxL = By.cssSelector(".custom-checkbox-container");

		By byFormF = By.cssSelector(".form-group .field .form-control");
		By byCheckboxF = By.cssSelector(".custom-checkbox-container .custom-checkbox-checkmark");

		// By byButtonF = By.xpath("//button[contains(text(), 'Yes')]");

		// By byDropdownF =

		By byDropdownF = By.cssSelector(".Select-placeholder");
		//By byButtonF = By.cssSelector(".single-select-btn-container .c-button-default"); byNoButtonF
		
		By  byButtonF = By.cssSelector("button[class='c-button-default circular single-select-btn  btn btn-default']");
				
				
		
		//driver.findElement(By.xpath("//div[starts-with(@class,'Add')]//following-sibling::*//button[contains(text(), 'ADD PRIMARY BENEFICIARY')]")).click();
		
		//driver.findElement(By.xpath("//button[contains(text(), 'ADD PRIMARY BENEFICIARY')]"));
		
		//driver.findElement(By.xpath(""));
		
		//click esign
		
		/*driver.findElement(By.cssSelector("div > button")).click();
		LoadingNextPage();
		
		driver.findElement(By.xpath("//label[contains(text(), 'I agree to use electronic records and signatures.')]")).click();
		*/
		
		System.out.println("clicked continue");
		
		driver.findElement(By.xpath("//button[@track='continue-button']")).click();
		
		System.out.println("clicked finish/start");
		
		driver.findElement(By.xpath("//button[@track='top-finish-button']")).click();
		
		System.out.println("clicked sign arrow");
		
		CheckElementExists(".signature-tab-content .tab-image-arrow", true);
		
		driver.findElement(By.cssSelector(".signature-tab-content .tab-image-arrow")).click();
		
		System.out.println("clicked draw");
		
		driver.findElement(By.xpath("//button[contains(text(), 'Draw')]")).click();
		
		//driver.findElement(By.xpath("//button[contains(text(), 'Adopt and Sign')]")).click();
		
		
		
		
		//div[starts-with(@class,'Add')]//following-sibling::*//button[contains(text(), 'ADD PRIMARY BENEFICIARY')]
		//button[contains(text(), 'ESIGN AND SUBMIT')]
		
		
		
		WebElement wbCanvas = driver.findElement(By.cssSelector(".signature-draw .canvas-wrapper"));
		
		System.out.println("x:" + wbCanvas.getLocation());
		
		Actions actionBuilder=new Actions(driver);          
		Action drawOnCanvas=actionBuilder		           
		                .moveToElement(wbCanvas,97,331)
						//.click(wbCanvas)
		                .clickAndHold(wbCanvas)
		                .moveByOffset(10, 20)
		                .click(wbCanvas)
		                .moveByOffset(100,350)
		                //.moveByOffset(-140,-140)
		                //.doubleClick(wbCanvas)
		                .build();
		drawOnCanvas.perform();
		
		//driver.findElement(By.xpath("//button[contains(text(), 'Adopt and Sign')]")).click();
		

		By byautoSuggF = By.xpath("//input[@autocomplete='off']");
		By byDatepickerF = By.cssSelector(".react-datepicker-wrapper");
		
		

		// input[@autocomplete='off']

		int formFlag = 0;
		int checkboxFlag = 0;
		int buttonFlag = 0;
		int dropDownFlag = 0;
		int autoSuggFlag = 0;
		int datePickerFlag = 0;
		int i = 0;
		int counter = 0;
		String pageType = "def";
		boolean flag = false;

		do {
			
			

			if (i == 0) {
				System.out.println("checking for autoSugg fields");
				autoSuggFlag = CheckWebElements(byautoSuggF);
			}
			if (autoSuggFlag > 0) {
				pageType = "autoSugg";
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byautoSuggF), byautoSuggF, pageType);
			}

			if (i == 0) {
				System.out.println("checking for datepicker fields");
				datePickerFlag = CheckWebElements(byDatepickerF);
			}
			if (datePickerFlag > 0) {
				pageType = "datepicker";
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byDatepickerF), byDatepickerF, pageType);
			}

			if (i == 0) {
				System.out.println("checking for checkbox fields");
				checkboxFlag = CheckWebElements(byCheckboxL);
			}
			if (checkboxFlag > 0) {
				pageType = "checkbox";
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(checkboxFlag, byCheckboxL), CheckNextElement(checkboxFlag, byCheckboxF),
						byCheckboxL, byCheckboxF, pageType);
			}

			if (i == 0) {
				System.out.println("checking for form fields");
				formFlag = CheckWebElements(byFormL);
			}
			if (formFlag > 0) {
				pageType = "form";
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(formFlag, byFormL), CheckNextElement(formFlag, byFormF), byFormL, byFormF,
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
				checkPage(CheckNextElement(byButtonF), byButtonF, pageType);
			}

			if (i == 0) {
				System.out.println("checking for dropdown fields");
				dropDownFlag = CheckWebElements(byDropdownF);
			}
			if (dropDownFlag > 0) {
				pageType = "dropdown";
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byDropdownF), byDropdownF, pageType);
			}

			i++;
			System.out.println("All submited in page " + i + ", going to next page. Clicking on Next button");

			driver.findElement(By.xpath("//button[@class='c-button-default circular  action btn btn-default']"))
					.click();
			CheckElementDoNotExists(".fa.fa-circle-o-notch", true);

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

	public void checkPage(Iterator<WebElement> l, Iterator<WebElement> f, By byL, By byF, String pageType) {

		int i = 0;

		while (l.hasNext() && f.hasNext()) {
			i++;

			if (pageType == "checkbox" && i == 1)
				FillPage(l, f, i, pageType);

			if (pageType != "checkbox")
				FillPage(l, f, i, pageType);
			else {
				f.next();
				l.next();
			}

		}

	}

	public void checkPage(Iterator<WebElement> f, By byF, String pageType) {

		int i = 0;

		while (f.hasNext()) {
			i++;

			FillPage(f, i, pageType);

		}

	}

	public void FillPage(Iterator<WebElement> l, Iterator<WebElement> f, int i, String pageType) {

		WebElement elementL = l.next();
		WebElement elementF = f.next();

		// String returnText =element.getAttribute("type").toString();
		String returnText = elementL.getText().toString();

		if (returnText.isEmpty())
			returnText = pageType;

		// t.next().click();
		System.out.println("i value: " + i + returnText);

		String QuestionType;

		if (pageType == "checkbox")
			returnText = "checkbox";

		QuestionType = IdentifyWebElementType(elementL, returnText);

		ClickWebElement(elementF, QuestionType);

	}

	public void FillPage(Iterator<WebElement> f, int i, String pageType) {

		WebElement elementF = f.next();

		// String returnText =element.getAttribute("type").toString();
		// String returnText = elementL.getText().toString();

		// t.next().click();
		System.out.println("i value: " + i + pageType);

		String QuestionType;

		QuestionType = IdentifyWebElementType(elementF, pageType);

		ClickWebElement(elementF, QuestionType);

	}

	public String IdentifyWebElementType(WebElement elementL, String returnText) {

		String QuestionType;
		switch (returnText) {
		case "*Phone (HOME/CELL)":
		case "Phone (WORK)":
			QuestionType = "Phone";
			System.out.println("This is a text field WebElement " + returnText);
			break;
		case "*Zip":
		case "*ZIP":
		case "Zip":
		case "ZIP":
		case "*Annual Income":
		case "*What is your annual household income?":
			QuestionType = "Number";
			System.out.println("This is a ZIP WebElement " + returnText);
			break;

		case "*Social Security #":
			QuestionType = "SSN";
			System.out.println("This is a SSN WebElement " + returnText);
			break;
		case "City":
			QuestionType = "Text";
			System.out.println("This is a City WebElement " + returnText);
			break;
		case "checkbox":
			QuestionType = "Checkbox";
			System.out.println("This is a Checkbox WebElement " + returnText);
			break;
		case "button":
			QuestionType = "Button";
			System.out.println("This is a Button WebElement " + returnText);
			break;
		case "dropdown":
			QuestionType = "Dropdown";
			System.out.println("This is a Dropdown WebElement " + returnText);
			break;
		case "autoSugg":
			QuestionType = "AutoSugg";
			System.out.println("This is a Dropdown WebElement " + returnText);
			break;
		case "datepicker":
			QuestionType = "DatePicker";
			System.out.println("This is a Dropdown WebElement " + returnText);
			break;

		default:
			QuestionType = "Text";
			System.out.println("This is a default  WebElement " + returnText);
			break;

		}
		return QuestionType;
	}

	public void ClickWebElement(WebElement elementF, String QuestionType) {

		switch (QuestionType) {
		case "Text":

			if (FieldType(elementF, "text")) {
				elementF.sendKeys("999");
				System.out.println("typed number");
			}
			break;
		case "Phone":

			if (FieldType(elementF, "9999999999")) {
				System.out.println("typed text");
				elementF.sendKeys("text");
			}

			break;
		case "Number":
			elementF.sendKeys("12345");
			break;
		case "SSN":
			elementF.sendKeys("222222222");
			break;

		case "Dropdown":
			dropdown.click();
			selectItem.click();
			// .Select-placeholder
			// stateValue.sendKeys("Alabama (AL)");
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
			elementF.sendKeys("ab");
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
			DOB.sendKeys("03/12/1979");
			dateSelect.click();
			break;

		default:
			elementF.sendKeys("default");
			break;

		}

	}

	public boolean FieldType(WebElement ele, String text) {

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

		//boolean present = true;

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
	
	
	public void CheckElementDoNotExists(String css, boolean flag) {

		//boolean present = true;

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
				flag = true;
			} catch (NoSuchElementException e) {
				flag = false;
			}

		}

	}

}
