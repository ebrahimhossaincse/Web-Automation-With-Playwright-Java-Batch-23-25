package me.ebrahimhossain;

import java.util.List;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class CheckboxHandle {
	protected String url = "https://www.tutorialspoint.com/selenium/practice/selenium_automation_practice.php";

	Playwright playwright;
	BrowserType browserType;
	protected Browser browser;
	protected BrowserContext context;
	protected Page page;

	@BeforeSuite
	public void startChromeBrowser() {
		playwright = Playwright.create();
		browserType = playwright.chromium();
		browser = browserType.launch(new BrowserType.LaunchOptions().setHeadless(false));
		context = browser.newContext(new Browser.NewContextOptions());

		page = browser.newPage();
		System.out.println("Browser version: " + browser.version());
	}

	// @Test
	public void check() throws InterruptedException {
		page.navigate(url);
		ElementHandle checkbox = page.querySelector("#hobbies");
		checkbox.click();
		Thread.sleep(3000);
	}

	@Test
	public void checkAll() throws InterruptedException {
		page.navigate(url);
		List<ElementHandle> checkboxs = page.querySelectorAll("//input[@type='checkbox']");
		for (ElementHandle e1 : checkboxs) {
			if (!e1.isChecked()) {
				e1.click();
				Thread.sleep(2000);
			}
		}
		Thread.sleep(3000);
	}

	@AfterSuite
	public void closeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
