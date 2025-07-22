package StepDef;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.WebDriverWait;

import Base.WebDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;
import pages.Sign_In;

public class Sign_In_Pom extends WebDriverFactory {
	private static final Logger logger = LogManager.getLogger(Sign_In_Pom.class);
	Sign_In sing_In;
	WebDriverWait Wait;

	@Before
	public void setUp(Scenario scn) throws Exception {
		this.scn = scn;
		String browserName = WebDriverFactory.getBrowserName();
		driver = WebDriverFactory.getWebDriverForBrowser(browserName);
		logger.info("Browser invoked.");
		sing_In = new Sign_In(driver);
	}

	@Given("User opened browser")
	@Deprecated
	public void User_opened_browser() {

		/*
		 * System.setProperty("webdriver.chrome.driver", "Browser\\chromedriver.exe");
		 * driver = new ChromeDriver(); driver.manage().window().maximize();
		 * driver.manage().deleteAllCookies();
		 * driver.manage().timeouts().implicitlyWait(Wait_Time_Second,
		 * TimeUnit.SECONDS);
		 */
		scn.log("Browser launched successfully");
	}

	@Given("User navigates to the application url")
	public void user_navigates_to_the_application_url() {
		driver.get(Base_Url);
		scn.log("Browser navigated to URL: " + Base_Url);
		String expected = "https://qa.onprem.icedq.com/";
		String actual = driver.getCurrentUrl();
		Assert.assertEquals("Page URL validation", expected, actual);
		scn.log("Page url validation successfull. Actual Url: " + actual);
		sing_In.Advance();
		logger.info("Page Url validation successfull. Expected and actual text matched. Text: " + actual);
		logger.info("<User is navigates to the application url>");
	}

	@Given("User add valid credentials to login the application")
	public void User_add_valid_credentials_to_login_the_application() {
		logger.info("user added valid details");
	}

	@When("User enters the valid username and password to click on sign in button")
	public void User_enters_the_valid_username_and_password_to_click_on_sign_in_button() {
		sing_In.Login_OnPrem();
		scn.log("User added valid credentials");
	}

	@Then("User navigated successfully to the welcome screen")
	public void User_navigated_successfully_to_the_welcome_screen() {
		logger.info("user log in successfully");
	}

	@After(order = 1)
	public void Clean_Up() {
		WebDriverFactory.quitDriver();
	}

	@After(order = 2)
	public void takeScreenShot(Scenario s) {
		if (s.isFailed()) {
			TakesScreenshot scrnShot = (TakesScreenshot) driver;
			byte[] data = scrnShot.getScreenshotAs(OutputType.BYTES);
			scn.attach(data, "image/png", "Failed Step Name: " + s.getName());
		} else {
			scn.log("Test case is passed successfully, no screen shot captured");
		}
	}

}
