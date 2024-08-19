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

public class WindowHandling {
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

	@Test
	public void createNewTab() throws InterruptedException {
		page.navigate(url);

		Page newWindow = context.newPage();
		newWindow.navigate("https://practice-automation.com/iframes/");
		Thread.sleep(5000);
		
		//switch to first window
		page.bringToFront();
		Thread.sleep(5000);
		
		//switch to new window
		newWindow.bringToFront();
		Thread.sleep(5000);
	}

	@AfterSuite
	public void closeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
