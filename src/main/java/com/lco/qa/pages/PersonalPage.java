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

	@FindBy(css = ".form-group")
	WebElement formElement;

	@FindBy(xpath = "//div[contains(text(),'State')]//following-sibling::*//div[@class='Select-multi-value-wrapper']//input")
	WebElement stateValue;

	@FindBy(css = ".Select-option.is-focused")
	WebElement selectItem;

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

		String url = "https://vantislifeinsurancestg.sureify.com/questions?user=KzFvRDdHbUxPK0VPNTRTUHJadEhPdz09&q_id=WEl6cmF6UWhzQW1XUEFVSitKOXNYZz09&auth_code=Fg5dlg23czdONE33mtXBhocQ4VFb0q&transaction_id=ecfe99c0-2f6d-11e9-b22a-0793f348bb14_1550048478556,c0e3d4b0-2f70-11e9-b22a-0793f348bb14_1550049693051";

		driver.navigate().to(url);

		// for(int i=0;i<5; i++)
		FillTextFields();

		return new PersonalPage();

	}

	public void FillTextFields() {

		/*
		List<WebElement> listField = null;
		Iterator<WebElement> f = null;
		List<WebElement> listLabel = driver.findElements(By.cssSelector(".form-group"));

		List<WebElement> listCheckbox = null;
		Iterator<WebElement> cb = null;
		List<WebElement> listCheckboxLabel = driver.findElements(By.cssSelector(".custom-checkbox-container"));

		
		 * System.out.println("Label count" + listLabel.size()); if
		 * (listLabel.size() > 0) { listField =
		 * driver.findElements(By.cssSelector(".form-group .field .form-control"
		 * )); System.out.println(listField.size()); f = listField.iterator(); }
		 * 
		 * System.out.println("check box count" + listCheckboxLabel.size()); if
		 * (listCheckboxLabel.size() > 0) { listCheckbox =
		 * driver.findElements(By.
		 * cssSelector(".custom-checkbox-container .custom-checkbox-checkmark"))
		 * ; System.out.println(listField.size()); cb = listCheckbox.iterator();
		 * }
		 */

		int i = 0;

		String pageType = "def";

		By byFormL = By.cssSelector(".form-group");
		By byCheckboxL = By.cssSelector(".custom-checkbox-container");

		By byFormF = By.cssSelector(".form-group .field .form-control");
		By byCheckboxF = By.cssSelector(".custom-checkbox-container .custom-checkbox-checkmark");

		int formFlag = CheckWebElements(byFormL);
		if (formFlag > 0)
			pageType = "form";
		int checkboxFlag = CheckWebElements(byCheckboxL);
		if (checkboxFlag > 0)
			pageType = "checkbox";

		do {

			//Iterator<WebElement> l = listLabel.iterator();

			// l = CheckNextElement(By.cssSelector(".form-group"))

			// CheckNextElement(By.cssSelector(".form-group")).hasNext();

			switch (pageType) {

			case "checkbox":
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byCheckboxL, byCheckboxF), CheckNextElement(byCheckboxL, byCheckboxF), byCheckboxL, byCheckboxF, pageType);
				break;
			case "form":
				System.out.println("This is a page filled with " + pageType);
				checkPage(CheckNextElement(byFormL, byFormF), CheckNextElement(byFormL, byFormF), byFormL, byFormF, pageType);
				break;
			default:
				System.out.println("This is a page filled with default");
				break;

			}

			if (formFlag < 1 && checkboxFlag < 1) {

				driver.findElement(By.xpath("//button[contains(text(), 'Yes')]")).click();
			}
			
			formFlag = CheckWebElements(byFormL);
			if (formFlag > 0)
				pageType = "form";
			checkboxFlag = CheckWebElements(byCheckboxL);
			if (checkboxFlag > 0)
				pageType = "checkbox";

		}while (formFlag > 0 || checkboxFlag > 0);												

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

	public void checkPage(Iterator<WebElement> l, Iterator<WebElement> f, By byL, By byF, String pageType) {

		int i = 0;

		while (l.hasNext() && f.hasNext()) {
			i++;

			FillPage(l, f, i, pageType);

			if (i == CheckWebElements(byL)) {
				driver.findElement(By.xpath("//button[contains(text(), 'Next')]")).click();

				l = CheckNextElement(byL, byF);

				f = CheckNextElement(byL, byF);
			}

		}

	}

	public void FillPage(Iterator<WebElement> l, Iterator<WebElement> f, int i, String pageType) {

		WebElement elementL = l.next();
		WebElement elementF = f.next();

		// String returnText =element.getAttribute("type").toString();
		String returnText = elementL.getText().toString();

		// t.next().click();
		System.out.println("i value: " + i + returnText);

		String QuestionType;

		switch (returnText) {
		case "Phone (HOME/CELL)":
		case "Phone (WORK)":
			QuestionType = "Phone";

			System.out.println("This is a text field WebElement" + returnText);
			break;
		case "ZIP":
		case "*Annual Income":
		case "*What is your annual household income?":
			QuestionType = "Number";
			System.out.println("This is a ZIP WebElement" + returnText);
			break;
		case "City":
			QuestionType = "Text";
			System.out.println("This is a City WebElement" + returnText);
			break;
		case "checkbox":
			QuestionType = "Text";
			System.out.println("This is a City WebElement" + returnText);
			break;				

		default:			
			if (pageType.contains("checkbox")) QuestionType = "Checkbox";
			else			
			 QuestionType = "Text";
			System.out.println("This is a default  WebElement" + returnText);
			break;

		}

		switch (QuestionType) {
		case "Text":
			if (FieldType(elementF, "text"))
				elementF.sendKeys("999");

			break;
		case "Phone":
			if (FieldType(elementF, "9999999999"))
				elementF.sendKeys("text");
			break;
		case "Number":
			elementF.sendKeys("12345");
			break;
		case "DropDown":
			stateValue.sendKeys("Alabama (AL)");
			selectItem.click();
			break;			
		case "Checkbox":
			elementF.click();
			break;
		case "Button":
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
