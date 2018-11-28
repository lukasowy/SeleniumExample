package selenium_tut;

import java.util.Iterator;
import java.util.Set;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class PopUpWindow {

	@Test
	public void popUpWindow() {

		System.setProperty("webdriver.gecko.driver", "webdriver/geckodriver_0.19.1-win32.exe");
//		FirefoxOptions options = new FirefoxOptions();
//		options.setCapability("marionette", false);

		WebDriver driver = new FirefoxDriver();

		driver.manage().window().maximize();

		driver.get("http://demo.guru99.com/popup.php");

		driver.findElement(By.xpath("//*[contains(@href,'popup.php')]")).click();

		
		
		
		String MainWindow = driver.getWindowHandle();

		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();
		
		System.out.println(MainWindow);
		System.out.println(s1);
		
		while (i1.hasNext()) {
			String parent = i1.next();
			String ChildWindow = i1.next();

				driver.switchTo().window(ChildWindow);
				driver.findElement(By.name("emailid")).sendKeys("gaurav.3n@gmail.com");

				driver.findElement(By.name("btnLogin")).click();

				driver.close();
			
		}
		
//		driver.switchTo().defaultContent();
		driver.switchTo().window(MainWindow);
		driver.close();
		driver.quit();
	}

}
