package pageObjects;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.LogManager;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;

public class BaseTest {
	
	public static String baseUrl;
	public static String browser;
	public static WebDriver driver;
	
	static {
        try {
            LogManager.getLogManager().readConfiguration(new FileInputStream("src/test/resources/log.properties"));
        } catch (IOException e) {
            System.err.println("Could not load logging configuration file");
            e.printStackTrace();
        }
    }
	
	@BeforeSuite
	public void readProperties() throws IOException {

		String filePath = "src/test/resources/config.properties";

		InputStream inputStream = new FileInputStream(filePath);
		Properties properties = new Properties();
		properties.load(inputStream);
		baseUrl = properties.getProperty("AppUrl");
		browser= properties.getProperty("browser");
		
	}
}
