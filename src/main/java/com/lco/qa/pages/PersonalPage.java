package com.lco.qa.pages;

import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.lco.qa.base.TestBase;

public class PersonalPage extends TestBase {

	// Page Factory - OR
	@FindBy(xpath = "")
	WebElement xyz;

	@FindBy(css = ".test")
	WebElement select1Item;

	@FindBy(css = ".Select-placeholder")
	WebElement dropdown;
	
	//@FindBy(xpath = "//div[@id='react-select-.*--option-0']")
	//WebElement selectItem;
	
	@FindBy(css=".Select-option")
	//@FindBy(xpath="//div[@class='Select-option is-focused']")
		//#react-select-2--option-0
	WebElement selectItem;

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

		String url = "https://vantislifeinsurancestg.sureify.com/questions?user=VUVad1FuaXl5anZBcXp2K1duSkRaQT09&text_accepted=No&vdtca&transaction_id=8e2f2350-311c-11e9-aff3-8fff83072c1a_1550233432581&ipAddress=10.134.118.34&timezoneOffset=-330&timezoneFormatted=GMT%200530%20(India%20Standard%20Time)&currentTime=1550233457890&q_id=cGRyVCtwd2xmY3JYNFYwcE1FVHoxdz09&transaction_id=8e2f2350-311c-11e9-aff3-8fff83072c1a_1550233432581&auth_code=0XGADPHkmGJSVWyKeoXJcvSMzGlI15";

		driver.navigate().to(url);

		// for(int i=0;i<5; i++)
		FillTextFields();

		return new PersonalPage();

	}

	public void FillTextFields() {

		

		int i = 0;

		String pageType = "def";

		By byFormL = By.cssSelector(".form-group");
		By byCheckboxL = By.cssSelector(".custom-checkbox-container");

		By byFormF = By.cssSelector(".form-group .field .form-control");
		By byCheckboxF = By.cssSelector(".custom-checkbox-container .custom-checkbox-checkmark");
		
		By byButtonF = By.xpath("//button[contains(text(), 'Yes')]");
		
		By byDropdownF = By.xpath("//div[@class='Select-multi-value-wrapper']//input");
		
		int formFlag = 0;
		int checkboxFlag = 0;
		int buttonFlag = 0;
		int dropDownFlag = 0;

		

		do {

	        i++;

			/*switch (pageType) {

			case "checkbox":
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byCheckboxL, byCheckboxF), CheckNextElement(byCheckboxL, byCheckboxF), byCheckboxL, byCheckboxF, pageType);
				break;
			case "form":
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byFormL, byFormF), CheckNextElement(byFormL, byFormF), byFormL, byFormF, pageType);
				break;				
			case "button":
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byButtonF), byButtonF, pageType);
				break;
			case "dropdown":
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byDropdownF), byDropdownF, pageType);
				break;
			default:
				System.out.println("This is a page filled with default");
				break;

			}

			if (formFlag < 1 && checkboxFlag < 1) {
				System.out.println("This is a page filled with single button");
				//driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
			}*/
			
			
			formFlag = CheckWebElements(byFormL);
			if (formFlag > 0){
				pageType = "form";
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byFormL, byFormF), CheckNextElement(byFormL, byFormF), byFormL, byFormF, pageType);
				}
			
			checkboxFlag = CheckWebElements(byCheckboxL);
			if (checkboxFlag > 0){
				pageType = "checkbox";
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byCheckboxL, byCheckboxF), CheckNextElement(byCheckboxL, byCheckboxF), byCheckboxL, byCheckboxF, pageType);
			
			}
			
			buttonFlag = CheckWebElements(byButtonF);
			if (buttonFlag > 0){
				pageType = "button";
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byButtonF), byButtonF, pageType);
			}
			
			dropDownFlag = CheckWebElements(byDropdownF);
			if (dropDownFlag > 0){
				pageType = "dropdown";
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byDropdownF), byDropdownF, pageType);
			}
			
			
			
			System.out.println("All submited in page " +i + ", going to next page. Clicking on Next button");
			driver.findElement(By.xpath("//button[contains(text(), 'Next')]")).click();
			
			//System.out.println(i+" page is completed going to next page");
			
			formFlag = CheckWebElements(byFormL);
			if (formFlag > 0)
				pageType = "form";
			checkboxFlag = CheckWebElements(byCheckboxL);
			if (checkboxFlag > 0)
				pageType = "checkbox";
			buttonFlag = CheckWebElements(byButtonF);
			if (buttonFlag > 0)
				pageType = "button";
			dropDownFlag = CheckWebElements(byDropdownF);
			if (dropDownFlag > 0)
				pageType = "dropdown";
			
			

		}while (formFlag > 0 || checkboxFlag > 0 || buttonFlag > 0 || dropDownFlag > 0);												

		//
		// return new PersonalPage();

	}

	public int CheckWebElements(By by) {

		List<WebElement> listWebElementLabel = driver.findElements(by);

		return listWebElementLabel.size();

		/*
		 * if (listWebElementLabel.size() > 0) { return true; } else return
		 * false;
		 */

	}

	public Iterator<WebElement> CheckNextElement(By byL, By byF) {

		if (CheckWebElements(byL) > 0) {
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

			FillPage(l, f, i, pageType);

			/*if (i == CheckWebElements(byL)) {
				//driver.findElement(By.xpath("//button[contains(text(), 'Next')]")).click();

				l = CheckNextElement(byL, byF);

				f = CheckNextElement(byL, byF);
			}*/

		}

	}

	
	public void checkPage(Iterator<WebElement> f, By byF, String pageType) {

		int i = 0;

		while (f.hasNext()) {
			i++;			
			
			FillPage(f, i, pageType);

			/*if (i == CheckWebElements(byF)) {
				
			

				f = CheckNextElement(byF);
			}*/

		}

	}
	
	
	public void FillPage(Iterator<WebElement> l, Iterator<WebElement> f, int i, String pageType) {

		WebElement elementL = l.next();
		WebElement elementF = f.next();

		// String returnText =element.getAttribute("type").toString();
		String returnText = elementL.getText().toString();
		
		if (returnText.isEmpty()) returnText = pageType;

		// t.next().click();
		System.out.println("i value: " + i + returnText);

		String QuestionType;

		QuestionType = IdentifyWebElementType(elementL, returnText);

		ClickWebElement(elementF, QuestionType);

	}
	
	
	public void FillPage(Iterator<WebElement> f, int i, String pageType) {


		WebElement elementF = f.next();

		// String returnText =element.getAttribute("type").toString();
		//String returnText = elementL.getText().toString();
		
		

		// t.next().click();
		System.out.println("i value: " + i + pageType);

		String QuestionType;

		QuestionType = IdentifyWebElementType(elementF, pageType);

		ClickWebElement(elementF, QuestionType);

	}
	
	public String IdentifyWebElementType(WebElement elementL, String returnText){
		
		String QuestionType;
		switch (returnText) {
		case "Phone (HOME/CELL)":
		case "Phone (WORK)":
			QuestionType = "Phone";
			System.out.println("This is a text field WebElement " + returnText);
			break;
		case "ZIP":
		case "*Annual Income":
		case "*What is your annual household income?":
			QuestionType = "Number";
			System.out.println("This is a ZIP WebElement " + returnText);
			break;
			
		case "Social Security #":
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

		default:			
			QuestionType = "Text";
			System.out.println("This is a default  WebElement " + returnText);
			break;

		}
		return QuestionType;
	}
	
	public void ClickWebElement(WebElement elementF, String QuestionType){
		
		switch (QuestionType) {
		case "Text":
			
			if (FieldType(elementF, "text")){
				elementF.sendKeys("999");
				System.out.println("typed number");
			}
			break;
		case "Phone":
			
			if (FieldType(elementF, "9999999999")){
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
			//.Select-placeholder
			//stateValue.sendKeys("Alabama (AL)");
			//selectItem.click();
			
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
		case "SuggestionBox":
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

}
