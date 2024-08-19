package me.ebrahimhossain;

import java.util.List;

import javax.lang.model.element.Element;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.ElementHandle;
import com.microsoft.playwright.Frame;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;

public class FrameHandle {
	protected String url = "https://practice-automation.com/iframes/";

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
		context = browser.newContext();
		page = context.newPage();
		System.out.println("Browser version: " + browser.version());
	}

	@Test(priority = 0)
	public void countIframe() throws InterruptedException {
		page.navigate(url);
		List<ElementHandle> iframes = page.querySelectorAll("iframe");
		System.out.println("Number of iframes: " + iframes.size());
	}

	@Test(priority = 1)
	public void switchToFrame() throws InterruptedException {
		page.navigate(url);
		Frame frame = page.frame("iframe-1");
		if (frame != null) {
			ElementHandle text = frame.querySelector("//b[@class='navbar__title text--truncate']");
			if (text != null) {
				text.click();
				System.out.println("Clicked on the text inside the iframe.");
			} else {
				System.out.println("Element not found inside the iframe.");
			}
		} else {
			System.out.println("Iframe not found.");
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
