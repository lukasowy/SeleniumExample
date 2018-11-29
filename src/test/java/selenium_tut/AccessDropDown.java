package selenium_tut;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class AccessDropDown {
	WebDriver driver;

	@Test
	public void test() {

		System.setProperty("webdriver.gecko.driver", "webdriver/geckodriver_0.19.1-win32.exe");
//		FirefoxOptions options = new FirefoxOptions();
//		options.setCapability("marionette", false);

		driver = new FirefoxDriver();

		selectFromList();
		DragnDrop();

	}

	public void selectFromList() {
		String baseURL = "http://demo.guru99.com/test/newtours/register.php";
		driver.get(baseURL);

		Select dropCountry = new Select(driver.findElement(By.name("country")));

		dropCountry.selectByVisibleText("ANTARCTICA");

		// Selecting Items in a Multiple SELECT elements
		driver.get("http://jsbin.com/osebed/2");
		Select fruits = new Select(driver.findElement(By.id("fruits")));
		fruits.selectByVisibleText("Banana");
		fruits.selectByIndex(1);
	}

	public void DragnDrop() {
		driver.get("http://demo.guru99.com/test/drag_drop.html");

		WebElement From1 = driver.findElement(By.xpath("//*[@id='credit2']/a"));

		WebElement To1 = driver.findElement(By.xpath("//*[@id='bank']/li"));

		WebElement From2 = driver.findElement(By.xpath("//*[@id='credit1']/a"));

		WebElement To2 = driver.findElement(By.xpath("//*[@id='loan']/li"));

		WebElement From3 = driver.findElement(By.xpath("//*[@id='fourth']/a"));

		WebElement To3 = driver.findElement(By.xpath("//*[@id='amt7']/li"));

		WebElement From4 = driver.findElement(By.xpath("//*[@id='fourth']/a"));

		WebElement To4 = driver.findElement(By.xpath("//*[@id='amt8']/li"));

		Actions act = new Actions(driver);

		act.dragAndDrop(From1, To1).build().perform();

		act.dragAndDrop(From2, To2).build().perform();

		act.dragAndDrop(From3, To3).build().perform();

		act.dragAndDrop(From4, To4).build().perform();

		if (driver.findElement(By.xpath("//a[contains(text(),'Perfect')]")).isDisplayed()) {
			System.out.println("Perfect Displayed !!!");
		} else {
			System.out.println("Perfect not Displayed !!!");
		}
	}
}
