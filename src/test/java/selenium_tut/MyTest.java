package selenium_tut;

import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MyTest {
	
	public void checkAlert(WebDriver driver) {
	    try {
	        Alert alert = driver.switchTo().alert();
	        alert.accept();
	    } catch (Exception e) {
	        //exception handling
	    }
	}
	
	@Test
	public void startWebDriver() {
		System.setProperty("webdriver.gecko.driver","webdriver/geckodriver_0.19.1-win32.exe");
		
		WebDriver driver = new FirefoxDriver();
		
		driver.navigate().to("http://demo.guru99.com/test/delete_customer.php");
		checkAlert(driver);
		driver.close();
	}
	
}
