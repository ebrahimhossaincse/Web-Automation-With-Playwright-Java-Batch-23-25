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

public class KeyboardEvents {
	protected String url = "https://www.tutorialspoint.com/selenium/practice/register.php";

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
	public void keyboardAction() throws InterruptedException {
		page.navigate(url);
		ElementHandle firstName = page.querySelector("#firstname");
		firstName.click();
		page.keyboard().type("Ebrahim");
		Thread.sleep(3000);

		// Select
		page.keyboard().down("Control");
		page.keyboard().press("KeyA");
		page.keyboard().up("Control");
		Thread.sleep(3000);
		// Copy
		page.keyboard().down("Control");
		page.keyboard().press("KeyC");
		page.keyboard().up("Control");
		Thread.sleep(3000);
		// tab
		page.keyboard().press("Tab");
		Thread.sleep(3000);
		// paste
		page.keyboard().down("Control");
		page.keyboard().press("KeyV");
		page.keyboard().up("Control");
		Thread.sleep(3000);
	}

	@AfterSuite
	public void closeBrowser() {
		page.close();
		browser.close();
		playwright.close();
	}
}
