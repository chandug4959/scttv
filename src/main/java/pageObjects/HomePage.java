package pageObjects;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.web;

public class HomePage extends BaseTest {

	/** country Button */
	@FindBy(xpath = "//div[@class='country-current']/a[@id='country-btn']")
	private WebElement countryButton;

	/** country Saudiarabia */
	@FindBy(xpath = "//a[@id='sa']")
	private WebElement countrySaudiarabia;

	/** country Kuwait */
	@FindBy(xpath = "//a[@id='kw']")
	private WebElement countryKuwait;

	/** country Baharin */
	@FindBy(xpath = "//a[@id='bh']")
	private WebElement countryBaharin;

	/** country Button */
	@FindBy(xpath = "//div/strong[@id='name-lite']")
	private WebElement planLiteTextLabel;

	/** plan Lite Text Label */
	@FindBy(xpath = "//div/strong[@id='name-classic']")
	private WebElement planClassicTextLabel;

	/** plan Premium Text Label */
	@FindBy(xpath = "//div/strong[@id='name-premium']")
	private WebElement planPremiumTextLabel;

	/** price Lite Plan */
	@FindBy(xpath = "//div[@id='currency-lite']/b")
	private WebElement priceLitePlan;

	/** price Classic Plan */
	@FindBy(xpath = "//div[@id='currency-classic']/b")
	private WebElement priceClassicPlan;

	/** price Premium Plan */
	@FindBy(xpath = "//div[@id='currency-premium']/b")
	private WebElement pricePremiumPlan;

	/** currency Lite Plan */
	@FindBy(xpath = "//div[@id='currency-lite']/i")
	private WebElement currencyLitePlan;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	/**
	 * select Country
	 * 
	 * @param CountryName
	 * @throws InterruptedException 
	 */
	public void selectCountry(String CountryName) throws InterruptedException {
		Thread.sleep(3000);
		web.clickOnElement(countryButton);
		logger.info("Click on Country Selection button");
		if (CountryName.equalsIgnoreCase("Saudiarabia")) {
			web.clickOnElement(countrySaudiarabia);
		} else if (CountryName.equalsIgnoreCase("Kuwait"))
			web.clickOnElement(countryKuwait);
		else if (CountryName.equalsIgnoreCase("Baharin"))
			web.clickOnElement(countryBaharin);
		
		logger.info("Choosen on Country "+CountryName);
	}

	/**
	 * verify Plan Type
	 * 
	 * @param CountryName
	 * @param PlanType
	 * @return
	 */
	public boolean verifyPlanType(String CountryName, String PlanType) {

		if (CountryName.equalsIgnoreCase("Saudiarabia"))
			return verifyPlan(PlanType);
		else if (CountryName.equalsIgnoreCase("Kuwait"))
			return verifyPlan(PlanType);
		else if (CountryName.equalsIgnoreCase("Baharin"))
			return verifyPlan(PlanType);
		logger.info("Verified Plan Type for the Country ");
		return false;

		
	}

	/**
	 * verify Price Of Plan
	 * 
	 * @param CountryName
	 * @param PlanType
	 * @param Price
	 * @return
	 */
	public boolean verifyPriceOfPlan(String CountryName, String PlanType, String Price) {
		if (CountryName.equalsIgnoreCase("Saudiarabia"))
			return verifyPlanAndPrice(PlanType, Price);
		else if (CountryName.equalsIgnoreCase("Kuwait"))
			return verifyPlanAndPrice(PlanType, Price);
		else if (CountryName.equalsIgnoreCase("Baharin"))
			return verifyPlanAndPrice(PlanType, Price);
		logger.info("Verified Price and Plan Type for the Country ");
		return false;
	}

	/**
	 * verify Plan
	 * 
	 * @param PlanType
	 * @return
	 */
	public boolean verifyPlan(String PlanType) {
		if (PlanType.equalsIgnoreCase(web.getWebElementText(planLiteTextLabel))
				|| PlanType.equalsIgnoreCase(web.getWebElementText(planClassicTextLabel))
				|| PlanType.equalsIgnoreCase(web.getWebElementText(planPremiumTextLabel))) {
			return true;
		}
		return false;
	}

	/**
	 * verify Plan And Price
	 * 
	 * @param PlanType
	 * @param Price
	 * @return
	 */
	public boolean verifyPlanAndPrice(String PlanType, String Price) {
		WebDriverWait wait =new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(planLiteTextLabel));
		if (PlanType.equalsIgnoreCase(web.getWebElementText(planLiteTextLabel))
				&& Price.equalsIgnoreCase(web.getWebElementText(priceLitePlan)))
			return true;
		else if (PlanType.equalsIgnoreCase(web.getWebElementText(planClassicTextLabel))
				&& Price.equalsIgnoreCase(web.getWebElementText(priceClassicPlan)))
			return true;
		else if (PlanType.equalsIgnoreCase(web.getWebElementText(planPremiumTextLabel))
				&& Price.equalsIgnoreCase(web.getWebElementText(pricePremiumPlan)))
			return true;
		logger.info("Verified Currency for the Country ");
		return false;
	}

	/**
	 * verify Currency and Country
	 * 
	 * @param CountryName
	 * @param Currency
	 * @return
	 */
	public boolean verifyCurrencyandCountry(String CountryName, String Currency) {
		String currencyText = web.getWebElementText(currencyLitePlan).trim();
		if (CountryName.equalsIgnoreCase("Saudiarabia") && currencyText.contains(Currency))
			return true;
		else if (CountryName.equalsIgnoreCase("Kuwait") && currencyText.contains(Currency))
			return true;
		else if (CountryName.equalsIgnoreCase("Baharin") && currencyText.contains(Currency))
			return true;

		return false;
	}

}
