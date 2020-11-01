package allClassFilesHere;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class FlipkartShoppingPage {
	WebDriver driver;

	public FlipkartShoppingPage(WebDriver driver) {
		this.driver = driver;
	}

	public void shoppCateogry(String cateogry) throws InterruptedException {
		if (cateogry.equalsIgnoreCase("Electronics")) {
			System.out.println("Electronics Cateogry is selected");
			By electronics = By.xpath("//body/div[@id='container']/div[1]/div[2]/div[1]/div[1]/span[1]");
			if (verify_cat(electronics)) {
				Actions a = new Actions(driver);
				WebElement element = driver.findElement(electronics);
				a.moveToElement(element).build().perform();
				Thread.sleep(5000);
				driver.findElement(By.xpath("//a[text()='iPhone SE']")).click();
				Thread.sleep(5000);
				driver.findElement(By
						.xpath("//div[contains(text(), 'Apple iPhone SE (Black, 64 GB) (Includes EarPods, Power Adapter)')]"))
						.click();
				Thread.sleep(3000);
				// switch to window 1 from default window
				ArrayList<String> wins = new ArrayList<String>(driver.getWindowHandles());
				driver.switchTo().window(wins.get(1));
				Thread.sleep(3000);
				// Add to Cart
				driver.findElement(By.xpath("//button[@class='_2AkmmA _2Npkh4 _2MWPVK']")).click();
				Thread.sleep(3000);
				// Verification if item is added to cart
				String cartPageURL = driver.getCurrentUrl();
				String ExpCartPageURL = "https://www.flipkart.com/viewcart?otracker=PP_GoToCart";
				Assert.assertEquals(cartPageURL, ExpCartPageURL, "Add To Cart Test Failed");

				// Place order
				driver.findElement(By.xpath("//span[contains(text(), 'Place Order')]")).click();
				Thread.sleep(3000);
				// Verify if delivery address URL is displayed
				String delPageURL = driver.getCurrentUrl();
				String ExpPageURL = "https://www.flipkart.com/checkout/init?loginFlow=false";
				Assert.assertEquals(delPageURL, ExpPageURL, "Checkout Page Test Failed");
				// switch to default window
				driver.switchTo().window(wins.get(0));

			}
		}

	}

	private boolean verify_cat(By cateogry) {
		boolean isElectronicCatAval = driver.findElements(cateogry).size() > 0;
		return isElectronicCatAval;
	}
}
