package selenium_tut;

import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumDemo {

	@Test
	public void test() {

		System.setProperty("webdriver.gecko.driver", "webdriver/geckodriver_0.19.1-win32.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("marionette", false);

		WebDriver driver = new FirefoxDriver(options);

		driver.get("https://www.google.com");
		setMaxWindow(driver);

		System.out.println(getCurrentUrl(driver));
		setWindowSize(driver, 650, 700);

		openNewWindow(driver, "https://facebook.com");
		refreshWebpage(driver);
		getBrowserVersion(driver);
		scrollToBottom(driver);

		Assert.assertTrue(getTitle(driver).contains("Facebook"));
		getParentWindow(driver);
		getAllWindows(driver);
	}

	public void getAllWindows(WebDriver driver) {
		Set<String> allWindiows = driver.getWindowHandles();
		System.out.println(allWindiows);
	}
	
	public void getParentWindow(WebDriver driver) {
		System.out.println(driver.getWindowHandle());
	}

	public String getTitle(WebDriver driver) {
		String title = driver.getTitle();
		return title;
	}

	public void scrollToBottom(WebDriver driver) {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("scrollBy(0, 25)");
	}

	public void getBrowserVersion(WebDriver driver) {
		Capabilities capabilities = ((RemoteWebDriver) driver).getCapabilities();
		String browserName = capabilities.getBrowserName();
		String browserVersion = capabilities.getVersion();

		System.out.println(browserName + " " + browserVersion);
	}

	public void refreshWebpage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void openNewWindow(WebDriver driver, String url) {
		driver.findElement(By.cssSelector("Body")).sendKeys(Keys.CONTROL + "t");
		driver.get(url);
	}

	public void setWindowSize(WebDriver driver, int width, int height) {

		Dimension dimension = new Dimension(width, height);
		driver.manage().window().setSize(dimension);
		System.out.println(driver.manage().window().getSize());
	}

	public void setMaxWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSource(WebDriver driver) {

		return driver.getPageSource();
	}
}
