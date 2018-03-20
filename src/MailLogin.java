import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import junit.framework.Assert;

public class MailLogin {

	private WebDriver wd;
	private String url;
	private String userName;
	private String userPass;

	@Before
	public void setUp() {
		wd = new FirefoxDriver();
		url = "https://www.onet.pl/";
		userName = "siemaneczko@gmail.com";
		userPass = "ziomeczku";
	}

	@Test
	public void loginNegative() throws InterruptedException {
		wd.get(url);
		wd.findElement(By.partialLinkText("E-MAIL")).click();

		WebElement loginName = wd.findElement(By.name("login"));
		loginName.clear();
		loginName.sendKeys(userName);

		WebElement loginPass = wd.findElement(By.id("f_password"));
		loginPass.clear();
		loginPass.sendKeys(userPass);

		wd.findElement(By.xpath("//input[@value='Zaloguj']")).click();

		Assert.assertTrue(wd.getPageSource().contains("Wprowadü poprawne dane.")
				|| wd.getPageSource().contains("Niepoprawny e-mail lub has≥o."));
	}

	@After
	public void tearDown() {
		wd.quit();
	}

}
