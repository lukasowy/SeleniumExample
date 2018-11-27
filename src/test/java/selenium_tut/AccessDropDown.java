package selenium_tut;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.Select;

public class AccessDropDown {

	@Test
	public void test() {

		System.setProperty("webdriver.gecko.driver", "webdriver/geckodriver_0.19.1-win32.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("marionette", false);

		WebDriver driver = new FirefoxDriver(options);

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

}
