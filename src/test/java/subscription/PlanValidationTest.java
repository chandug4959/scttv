package subscription;

import org.testng.annotations.Test;
import common.ExcelDataProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.io.IOException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import java.util.logging.Logger;
import pageObjects.BaseTest;
import pageObjects.HomePage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class PlanValidationTest extends BaseTest{
	public static WebDriver driver;
	private static final Logger logger = Logger.getLogger(BaseTest.class.getName());

	//public String baseUrl = "https://subscribe.stctv.com/sa-en?";
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
		System.out.println(homepage.verifyPlanType(countryName,planType));
		System.out.println(homepage.verifyPlanAndPrice(planType, price));
		System.out.println(homepage.verifyCurrencyandCountry(countryName, currency));
		logger.info("Successfully navigated to website.");
	}
	
	
	
	 @DataProvider(name = "excelData")
	    public Object[][] excelDataProvider() throws IOException {
	 
	        // We are creating an object from the excel sheet data by calling a method that
	        // reads data from the excel stored locally in our system
	        Object[][] arrObj = new ExcelDataProvider().getExcelData(
	        		"src/test/resources/testdata.xlsx","Sheet1");
	        return arrObj;
	}
	 
	 @BeforeClass
	 public void setUp() {
		 WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			logger.info("Chrome Drive Launched");
			driver.manage().window().maximize();
			driver.get(baseUrl);
			logger.info("Successfully navigated to website.");
		}

	
	@AfterClass
	public void closeBrowser() {
		driver.quit();
	}
	 
	 
}
