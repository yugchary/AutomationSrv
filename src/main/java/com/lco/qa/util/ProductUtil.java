package com.lco.qa.util;

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

}
