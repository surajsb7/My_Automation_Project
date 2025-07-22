package pages;

import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sign_In {

	private static final Logger logger = LogManager.getLogger(Sign_In.class);
	protected WebDriver driver;
	WebDriverWait Wait;

	@FindBy(id = "details-button")
	private WebElement advance;
	
	@FindBy(id = "proceed-link")
	private WebElement conti;
	
	@FindBy(id = "username")
	private WebElement Username;

	@FindBy(id = "password")
	private WebElement Password;

	@FindBy(id = "kc-login")
	private WebElement Login_Button;
	
	public Sign_In(WebDriver driver) {

		this.driver = driver;
		PageFactory.initElements(driver, this);

	}
	
	public void Advance() {
		advance.click();
		conti.click();
	}
	
	public void Login_OnPrem() {
		Username.sendKeys("surajb");
		Password.sendKeys("Suraj@1122");
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		Login_Button.click();
		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		logger.info("User entered valid login details");
	}
}