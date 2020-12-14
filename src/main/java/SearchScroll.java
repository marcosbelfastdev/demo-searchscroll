
import io.github.bonigarcia.wdm.WebDriverManager;
import org.base.erbium.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.base.erbium.EDriver.*;

public class SearchScroll {


	private EDriver browser;
	private WebDriver driver;

	@BeforeClass(alwaysRun = true)
	public void setUp() throws Exception {

		// Core Selenium initialisations
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("disable-notifications");
		options.addArguments("--incognito");
		driver = new ChromeDriver();

		// Erbium base state definitions
		// Output directory will be: /home/<user>/Erbium Tests/...
		BaseState baseState = new BaseState();
		baseState.setProjectName("Page Object Tests");
		baseState.setEnvironment("DEV");
		baseState.setTestName(getClass().getSimpleName());
		baseState.setBaseStateUrl("https://www.ultimateqa.com/fake-landing-page");

		// Erbium driver initialisations
		browser = baseState.execute(driver);
		browser.manage().window().maximize();
		browser.setOption(Common.INTERACT_DELAY_AFTER, 1500);

		browser.manage().highlightElements(true);
		// or browser.setOption(Common.HIGHLIGHT_ELEMENTS);

	}


	@Test
	public void searchScroll() throws Exception {

		// Enable load on demand so to load an empty element until it is rendered and found.
		// This is enabled by default from version 1.2.6
		//browser.manage().loadOnDemand(true);

		// positions the element on screen if it scrolled too much
		//browser.setOption(Common.SCROLL_TO_ELEMENTS);

		// scrolls 3 times the browser height each time; defaults to 0.85
		// just comment out the next line to veriy
		//browser.setOption(Common.SEARCHSCROLL_FACTOR, 3.0);

		// waits 1 second for the element to be found before scrolling again
		// defaults to 2 seconds; just comment out this line to verify
		//browser.setOption(Common.SEARCHSCROLL_RESOLVE, 1);

		// scrolls until Free Courses is found or timeout is reached
		// setFocus() triggers the scroll into view defined in SCROLL_TO_ELEMENTS
		browser.find(By.linkText("Free Courses")).scrollDown().setFocus().highlight();

		Thread.sleep(5000); // gives you some time to see the element highlighted

	}

	@AfterClass(alwaysRun = true)
	public void tearDown() throws Exception {
		browser.quit();
	}

}


