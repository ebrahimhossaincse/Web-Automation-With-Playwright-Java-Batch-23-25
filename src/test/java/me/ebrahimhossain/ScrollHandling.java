package me.ebrahimhossain;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class ScrollHandling {
	protected String url = "https://www.daraz.com.bd";

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

	// @Test(priority = 0)
	public void scrollDown() throws InterruptedException {
		page.navigate(url);
		String script = "window.scrollTo(0, document.body.scrollHeight)";
		page.evaluate(script);
		Thread.sleep(5000);
	}

	// @Test(priority = 1)
	public void scrollUp() throws InterruptedException {
		page.navigate(url);
		String script = "window.scrollTo(0,0)";
		page.evaluate(script);
		Thread.sleep(5000);
	}

	@Test(priority = 2)
	public void scrollToSpecificLocator() throws InterruptedException {
		page.navigate(url);
		ElementHandle locator = page.querySelector("//p[contains(text(),'Flash Sale')]");
		locator.scrollIntoViewIfNeeded();
		Thread.sleep(5000);
	}

	@AfterSuite
	public void closeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
