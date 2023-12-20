package subscription;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import common.ExcelDataProvider;
import pageObjects.BaseTest;
import pageObjects.HomePage;

import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PlanValidationTest extends BaseTest{
	public static WebDriver driver;
	
	SoftAssert softAssert = new SoftAssert();

	/**
	 * validate PlanPrice Currency For All Countries 
	 * @param countryName
	 * @param planType
	 * @param price
	 * @param currency
	 * @throws InterruptedException 
	 */
	@Test(dataProvider="excelData")
	public void validatePlanPriceCurrencyForCountry(String countryName,String planType,String price,String currency) throws InterruptedException
	{
		
		HomePage homepage=new HomePage(driver);
		homepage.selectCountry(countryName);
		softAssert.assertEquals(homepage.verifyPlanType(countryName,planType), true, "Validated Plan Type for "+countryName+" , Plan "+planType);
		//homepage.verifyPlanType(countryName,planType);
		softAssert.assertEquals(homepage.verifyPriceOfPlan(countryName, planType, price), true, "Validated Price and Plan Type for "+countryName+" , Plan "+planType+ " Price "+price);
		//homepage.verifyPriceOfPlan(countryName, planType, price);
		//homepage.verifyPlanAndPrice(planType, price);
		softAssert.assertEquals(homepage.verifyCurrencyandCountry(countryName, currency), true, "Validated Currency for "+countryName+" , Currency"+currency);
		softAssert.assertAll();
		//homepage.verifyCurrencyandCountry(countryName, currency);
		logger.info("Validated All Cases");
	}
	
	
	 @DataProvider(name = "excelData")
	    public Object[][] excelDataProvider() throws IOException {
	 
	       Object[][] arrObj = new ExcelDataProvider().getExcelData(
	        		"src/test/resources/testdata.xlsx","Sheet1");
	        return arrObj;
	}
	 
	 @BeforeClass
	 public void setUp() {
		 	//WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("Chrome Browser Launched");
			driver.manage().window().maximize();
			driver.get(baseUrl);
			logger.info("Successfully navigated to website.");
		}

	
	@AfterClass
	public void closeBrowser() {
		logger.info("Browser Closed");
		driver.quit();
	}
	 
	 
}
