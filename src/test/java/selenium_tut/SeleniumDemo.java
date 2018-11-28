package selenium_tut;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumDemo {

	@Test
	public void test() throws Exception {

		System.setProperty("webdriver.gecko.driver", "webdriver/geckodriver_0.19.1-win32.exe");
		FirefoxOptions options = new FirefoxOptions();
		options.setCapability("marionette", false);

		WebDriver driver = new FirefoxDriver(options);

		driver.get("https://www.bing.com/");
		setMaxWindow(driver);
		clickLink(driver, "Images");
		System.out.println(getCurrentUrl(driver));
		setWindowSize(driver, 650, 700);
		getDomainName(driver);
		// getCookies(driver);;
		savePageSourceHTML(driver);
		openNewWindow(driver, "https://facebook.com");
		refreshWebpage(driver);
		getBrowserVersion(driver);
		scrollToBottom(driver);

		Assert.assertTrue(getTitle(driver).contains("Facebook"));
		getParentWindow(driver);
		getAllWindows(driver);
		takeSnapShot(driver, "B://Selenium//Screenshot//test" + Math.random() + ".png");
		countIFrames(driver);
		switchToNewWindow(driver);
		clickByClass(driver, "sc_active");
		clickXPathLink(driver, "Privacy and Cookies");
		// findAllLinks(driver);
		driver.quit();
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

	public static void takeSnapShot(WebDriver webdriver, String fileWithPath) throws Exception {
		TakesScreenshot scrShot = ((TakesScreenshot) webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		try {
			File DestFile = new File(fileWithPath);
			FileUtils.copyFile(SrcFile, DestFile);

		} catch (IOException e) {
			System.out.println(e);
		}
	}

	public void countIFrames(WebDriver driver) {
		List<WebElement> totaliframes = driver.findElements(By.tagName("iframe"));
		System.out.println("Total iframes: " + totaliframes.size());
	}

	public void switchToNewWindow(WebDriver driver) {

		driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "n");
		driver.get("https://www.bing.com/");
		try {

			for (String winHandle : driver.getWindowHandles()) {
				driver.switchTo().window(winHandle);
			}
			driver.get("https://www.bing.com/");

		} catch (Exception e) {

		}
	}

	public void clickLink(WebDriver driver, String text) {
		driver.findElement(By.linkText(text)).click();
	}

	public void clickByClass(WebDriver driver, String text) {
		driver.findElement(By.className(text)).click();
	}

	public void clickXPathLink(WebDriver driver, String link) {
		driver.findElement(By.xpath("//a[text()='" + link + "']")).click();
	}

	public void findAllLinks(WebDriver driver) {

		List<WebElement> tags = driver.findElements(By.tagName("a"));
		System.out.println(tags.size());
		for (WebElement element : tags) {
			System.out.println(element.getAttribute("href"));
		}

	}

	public void getDomainName(WebDriver driver) throws URISyntaxException {
		URI uri = new URI(driver.getCurrentUrl());
		String domain = uri.getHost();
		System.out.println(domain);
	}

	public void getCookies(WebDriver driver) {
		Set<Cookie> cookies = driver.manage().getCookies();
		System.out.println("Sieze of Cookiers: " + cookies.size());

		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName() + " : " + cookie.getValue());
		}
	}

	public void savePageSourceHTML(WebDriver driver) {
		String source = driver.getPageSource();

		File newFile = new File("B://Selenium//Screenshot//selenium" + Math.random() + ".txt");
		try {
			FileWriter fw = new FileWriter(newFile);
			fw.write(source);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
