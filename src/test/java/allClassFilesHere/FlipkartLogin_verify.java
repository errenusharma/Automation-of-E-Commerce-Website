package allClassFilesHere;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FlipkartLogin_verify {
	// create static variables
	static WebDriver driver;
	static LoginPage login;
	Map<Integer, ArrayList<String>> dataMap;
	String localDir = System.getProperty("user.dir");

	// launch the chrome browser
	@BeforeClass
	public void setup(ITestContext testcontext) throws IOException {
		String browser = testcontext.getCurrentXmlTest().getParameter("Browser");
		if ("Chrome".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("Chrome launched");
		} else if ("Firefox".equalsIgnoreCase(browser)) {
			System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
			driver = new FirefoxDriver();
			System.out.println("Firefox Launched");
		} else {
			System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
			driver = new ChromeDriver();
			System.out.println("DEFAULT Browser :: Chrome launched");
		}
		
		// got to url https://www.flipkart.com/
		driver.get("https://www.flipkart.com/");
		// insert implicit wait time
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// maximize window
		driver.manage().window().maximize();
		// provide path of excel file
		String excelFile = localDir + "\\src\\test\\resources\\allDataFilesHere\\Project_Test_Data.xlsx";
		// Read Excel file using Test Util Class
		dataMap = Test_Util.read_Excel(excelFile, 1);

	}

	@Test
	public void verify_login() throws InterruptedException {
		// Create object of LoginPage class
		login = new LoginPage(driver);

		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		// Iterate over Map to get key values
		for (Integer mapValue : dataMap.keySet()) {
			List<String> userNmeAndPass = dataMap.get(mapValue);
			// Call LoginPage methods to pass Username & Password values
			login.type_username(userNmeAndPass.get(0));
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			login.type_password(userNmeAndPass.get(1));
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
			login.type_next();

			Thread.sleep(2000);
			Boolean isPresent = driver.findElements(By.xpath("//div[text()='My Account']")).size() > 0;
			if (isPresent) {
				// Verification Point to check if login is successful
				WebElement button_disp = driver.findElement(By.xpath("//div[text()='My Account']"));
				if (button_disp.isDisplayed()) {
					System.out.println("Valid Id & Password");
				}
				new FlipkartShoppingPage(driver).shoppCateogry("Electronics");
				// Using Actions class for advanced user interaction like MouseHover
				Actions a = new Actions(driver);
				a.moveToElement(button_disp).build().perform();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//div[contains(text(),'Logout')]")).click();
				Thread.sleep(5000);
			} else {
				Thread.sleep(2000);
				captureScreenShot(userNmeAndPass.get(0));
				System.out.println("Invalid email or password");
				driver.navigate().refresh();
			}
			driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		}
	}

	private void captureScreenShot(String userName) {

		File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenShot,
					new File(localDir + "\\src\\test\\java\\allClassFilesHere\\" + userName + ".jpeg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		driver.quit();
	}
}
