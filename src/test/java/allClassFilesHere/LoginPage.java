package allClassFilesHere;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
	WebDriver driver;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}

	// Create Object Repository for Web Elements
	By login = By.xpath("//a[contains (text(), 'Login')]");
	By username = By.xpath("//input[@class='_2zrpKA _1dBPDZ']");
	By password = By.xpath("//input[@class='_2zrpKA _3v41xv _1dBPDZ']");
	By next = By.xpath("//button[@class='_2AkmmA _1LctnI _7UHT_c']");

	public By getUsername() {
		return username;
	}

	public By getPassword() {
		return password;
	}
	// Create methods for elements

	public void click_login() {
		driver.findElement(login).click();
	}

	public void type_username(String mobile_no) {
		driver.findElement(username).sendKeys(mobile_no);

	}

	public void type_password(String Password) {
		driver.findElement(password).sendKeys(Password);

	}

	public void type_next() {
		driver.findElement(next).click();

	}
}
