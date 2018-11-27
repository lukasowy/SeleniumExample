package selenium_tut;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class AlertTest {

	public void checkAlert(WebDriver driver) {
		try {
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			// exception handling
		}
	}

	@Test
	public void startWebDriver() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver", "webdriver/geckodriver_0.19.1-win32.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("marionette", false);
		
		
		WebDriver driver = new FirefoxDriver(options);

		driver.get("http://demo.guru99.com/test/delete_customer.php");
		
		driver.manage().window().maximize();
		
		driver.findElement(By.name("cusid")).sendKeys("53920");
		driver.findElement(By.name("submit")).submit();

		Alert alert = driver.switchTo().alert();

		String alertMessage = driver.switchTo().alert().getText();

		System.out.println(alertMessage);
		alert.dismiss();

		driver.findElement(By.name("submit")).submit();
		driver.switchTo().alert();

		alertMessage = driver.switchTo().alert().getText();

		System.out.println(alertMessage);
		alert.accept();
		
		driver.close();
	}

}
