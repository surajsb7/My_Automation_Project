package Base;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;

import io.cucumber.java.Scenario;

public class WebDriverFactory {

	protected String Base_Url = "https://qa.onprem.icedq.com/";
	public int Wait_Time_Second = 20;
	public Scenario scn;
	private static final Logger logger = LogManager.getLogger(WebDriverFactory.class);
	public static WebDriver driver = null;

	public static WebDriver getWebDriverForBrowser(String browser) throws Exception {
		switch (browser.toLowerCase()) {
		case "chrome":
			System.setProperty("webdriver.chrome.driver",
					"D:\\Automation\\Framework_Cucumber_automationpractice-master\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");
			driver = new ChromeDriver();
			logger.info("Chrome Browser invoked");
			break;
		case "edge":
			System.setProperty("webdriver.edge.driver", "Browser\\msedgedriver.exe");
			driver = new EdgeDriver();
			logger.info("Edge Browser invoked");
			break;
		case "headless":
			ChromeOptions options = new ChromeOptions();
			options.addArguments("headless");
			options.addArguments("window-size=1200x600");
			driver = new ChromeDriver(options);
			logger.info("Headless Chrome Browser invoked");
			break;
		default:
			logger.fatal("No such browser is implemented.Browser name sent: " + browser);
			throw new Exception("No such browser is implemented.Browser name sent: " + browser);
		}

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		logger.info("Driver maximized and implicit time out set to 20 seconds");
		return driver;
	}

	public static void navigateToTheUrl(String Base_Url) {
		driver.get(Base_Url);
		logger.info("Browser navigated to the url: " + Base_Url);
	}

	public static void quitDriver() {
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.quit();
		logger.info("Driver closed");
	}

	public static String getBrowserName() {
		String browserDefault = "chrome"; // Set by default
		String browserSentFromCmd = System.getProperty("browser");

		if (browserSentFromCmd == null) {
			return browserDefault;
		} else {
			return browserSentFromCmd;
		}
	}
}
