package common;

import java.time.Duration;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pageObjects.BaseTest;



public class web extends BaseTest{

	
	public web(WebDriver driver) {
    	this.driver = driver;
	}
	
	/**
	 * 
	 * @param clickableElement
	 * @return
	 */
	public static boolean clickOnElement(WebElement clickableElement) {
		boolean success = false;
		try{
			
			if (isWebElementDisplayed(clickableElement,true)) 
			{
				clickableElement.click();
				success = true;
			}
		}
			catch(Exception e)
			{
				e.printStackTrace();
			}
			return success;
			
			
	}	
			
	
	/**
	 * 
	 * @param element
	 * @param waitForElement
	 * @return
	 */
	public static boolean isWebElementDisplayed(WebElement element,
			boolean... waitForElement) {
		boolean blnElementDisplayed = false;
		if(element==null){
			return false;
		}
		try {
			try {
				if (waitForElement.length > 0) {
					if (waitForElement[0] == true) {
						waitForElement(element);
					}
				}

			} catch (Exception e) {
				// Do nothing
			}
			blnElementDisplayed = element.isDisplayed();
		} catch (NoSuchElementException e) {
			blnElementDisplayed = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return blnElementDisplayed;
	}
	
	/**
	 * wait For Element
	 * @param element
	 */
	public static void waitForElement(WebElement element) {
		try {
					
			new WebDriverWait(driver, Duration.ofSeconds(10))
	        .until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			//			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * @param element
	 * @return
	 */
	public static String getWebElementText(WebElement element){
		if(isWebElementDisplayed(element)){
			return element.getText();
		}
		return "";
	}
}
