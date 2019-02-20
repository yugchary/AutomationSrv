package com.lco.qa.util;

import java.util.HashMap;

import org.openqa.selenium.By;
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
	
	
	public static HashMap<String, String> GetInputData(String SheetName) {

		HashMap<String, String> inputData = new HashMap<String, String>();
		
		Object[][] x = null;
		try {
			x = Testutil.getTableArray(Testutil.TESTDATA_SHEET_PATH, SheetName);
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
	

}
