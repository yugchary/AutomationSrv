package com.lco.qa.util;

import java.util.HashMap;

import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.By.ByXPath;
import org.openqa.selenium.NoSuchElementException;

import com.lco.qa.base.TestBase;

public class ProductUtil extends TestBase{
	
	public static void CheckElementDoNotExists(String css, boolean flag) {

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
	
	
	public static HashMap<String, String> GetInputData(String SheetName, int rowsCount, int colsCount) {

		HashMap<String, String> inputData = new HashMap<String, String>();
		
		Object[][] x = null;
		try {
			x = Testutil.getTableArray(Testutil.TESTDATA_SHEET_PATH, SheetName, rowsCount, colsCount, 2, 1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String line = null;

		// Object data;

		for (Object[] innerArray : x) {
			StringBuilder sb = new StringBuilder();
			for (Object data : innerArray)
				sb.append(data).append("_");

			// System.out.println(sb);

			line = sb.toString();
			String[] arrOfStr = line.split("_", 2);

			for (String a : arrOfStr) {
				int len = arrOfStr[1].length();
				String str = arrOfStr[1].substring(0, len - 1);
				// returnText = arrOfStr[1].substring(len -1);
				inputData.put(arrOfStr[0], str);

			}

			// System.out.println("read line");
		}
		 
		return inputData;
	}
	
	
	public static void selectDropdown(String elementName, String item){
		
		driver.findElement(By.xpath("//div[starts-with(text(),'"+ elementName +"')]//following-sibling::*//div[@class='Select-multi-value-wrapper']//input")).sendKeys(item);
		driver.findElement(By.cssSelector(".Select-option")).click();

		
	}
	
	public static void clickButton(String elementName){
		
		driver.findElement(By.xpath("//button[contains(text(), '"+ elementName + "')]")).click();

		
	}
	
	public static void clickButton(By bycss){
		
		driver.findElement(bycss).click();

		
	}
	
	public static void clickButton(ByXPath xPath){
		
		driver.findElement(xPath).click();

		
	}
	
	public static void inputText(String elementName, String value){
		
		driver.findElement(By.xpath("//label[starts-with(text(),'"+ elementName +"')]//following-sibling::*//input")).sendKeys(value);;

		
	}
	
	public static void inputCCDetails(String elementName, String value){
		
		driver.findElement(By.xpath("//label[starts-with(text(),'"+ elementName +"')]//parent::*//following-sibling::*//input")).sendKeys(value);;

		
	}
	
	
	
	
	

}
