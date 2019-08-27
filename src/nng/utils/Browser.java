package nng.utils;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * @author Ashutosh Mishra
 * @Desc Each time whenever a new page object would be created then a new webdriver object would be created
 * @example Page wikiPage = new Page("URL_GOES_HERE") // By Default google chrome settings would be applicable 
 */
public class Browser {

	/*
	 * Globals: Page properties
	 * =========================
	 */
	private WebDriver driver 		= null;
	private WebDriverWait wait		= null;
	private String pageUrl 			= "";
	private int maxWaitInSeconds	= 10;
	private String logText			= "";
	private String browserName		= "CHROME";
	private String driverPath		= "";
	private boolean takeScreenShots = true;
	private boolean takeScreenShotsOnFailure = true;
	private ChromeOptions chromeOptions = null;
	private FirefoxOptions firefoxOptions = null;
	private Set<Cookie> cookies 	= null; /* Page Cookies and localstorage */
	
	/*
	 * Constructor
	 * =============
	 */
	//public Browser() {}
	public Browser(String pageUrl) { if(pageUrl != null) this.pageUrl = pageUrl; }
	public Browser(String pgUrl, int maxWaitTime, String browser, String driverLocation) {
		this.pageUrl = pgUrl;
		this.browserName = browser;
		this.maxWaitInSeconds = maxWaitTime;
		this.driverPath = driverLocation;
	}
	public Browser(String pgUrl, int maxWaitTime, String browser, String driverLocation, ChromeOptions chrmOptions) {
		this.pageUrl = pgUrl;
		this.browserName = browser;
		this.maxWaitInSeconds = maxWaitTime;
		this.driverPath = driverLocation;
		this.chromeOptions = chrmOptions;
	}
	public Browser(String pgUrl, int maxWaitTime, String browser, String driverLocation, FirefoxOptions frfxOptions) {
		this.pageUrl = pgUrl;
		this.browserName = browser;
		this.maxWaitInSeconds = maxWaitTime;
		this.driverPath = driverLocation;
		this.firefoxOptions = frfxOptions;
	}
	
	/*
	 * Init
	 */
	public Browser initialize() {
		switch(this.browserName.toUpperCase()) {
		case "FIREFOX":
			// set driver path
			System.setProperty("webdriver.gecko.driver", this.driverPath);
			// if options is provided
			if(this.firefoxOptions != null) {this.driver = new FirefoxDriver(this.firefoxOptions);} else {this.driver = new FirefoxDriver();}
			// Applying wait as explicit wait
			this.wait=new WebDriverWait(this.driver, Duration.ofSeconds(this.maxWaitInSeconds));
			break;
		
		case "CHROME":
		default:
			// set driver path
			System.setProperty("webdriver.chrome.driver", this.driverPath);
			// if options is provided
			if(this.chromeOptions != null) {this.driver = new ChromeDriver(this.chromeOptions);} else {this.driver = new ChromeDriver();}
			// Applying default wait as Explicit wait
			this.wait=new WebDriverWait(this.driver, Duration.ofSeconds(this.maxWaitInSeconds));
			//WebDriverWait wait=new WebDriverWait(this.driver, this.maxWaitInSeconds);
			break;
		
		}
		// return this as builder pattern
		return this;
	}
	
	/*
	 * ===================================================================================
	 * Getter functions
	 * ===================================================================================
	 */
	public String getPageUrl() { return this.pageUrl; }
	public WebDriver getPageDriver() {return this.driver; }
	public String getPageTitle() {return this.driver.getTitle(); }
	public String getText(By by) {return this.driver.findElement(by).getText(); }
	public String getElementAttribute(By by, String attribName) { return this.driver.findElement(by).getAttribute(attribName); }
	public int getTotalTabs() { return this.driver.getWindowHandles().size(); }
	// Get the browser cookies
	public Set<Cookie> getCookies() { return this.driver.manage().getCookies(); }
	
	/*
	 * ===================================================================================
	 * Setter Functions
	 * ===================================================================================
	 */
	public void setDefaultBroser(String browserName) { if(browserName != null) this.browserName = browserName; else this.browserName = "CHROME"; }
	public void setChromeOptions(ChromeOptions options) {}
	public void setFirefoxOptions(FirefoxOptions options) {}
	public void setEdgeOptions(EdgeOptions options) {}
	public void setPageUrls(String url) { if(url != null) this.pageUrl = url; }
	public void setMaxWaitTime(int maxWaitTime) { if(maxWaitTime > 0) this.maxWaitInSeconds = maxWaitTime; }
	
	
	
	/*
	 * ===================================================================================
	 * Other functions, make these as builder pattern
	 * ===================================================================================
	 */
	public Browser open() { this.driver.get(this.pageUrl); return this; }
	public Browser close() { this.driver.close(); return this; }
	
	public Browser maximize() { this.driver.manage().window().maximize(); return this; }
	public Browser setSize(int width, int height) { this.driver.manage().window().setSize(new Dimension(width, height)); return this; }
	public Browser fullscreen() { this.driver.manage().window().fullscreen(); return this; }
	public Browser click(By by) { /* Wait till its visibility is located */ this.wait.until( ExpectedConditions.visibilityOfElementLocated(by) ); /* Click once its got located */ this.driver.findElement(by).click(); /* return this for builder pattern */ return this; }
	public Browser clear(By by) { /* Wait till its visibility is located */ this.wait.until( ExpectedConditions.visibilityOfElementLocated(by) ); /* Do operation its got located */ this.driver.findElement(by).clear(); return this; }
	public Browser dblClick(By by) { return this; }
	public Browser type(By by, String keysToSend) { /* Wait till its visibility is located */ this.wait.until( ExpectedConditions.visibilityOfElementLocated(by) ); /* Do operation its got located */ this.driver.findElement(by).sendKeys(keysToSend); return this; }
	public Browser hover(By by) { return this; }
	public Browser scrollTo(By by) { return this; }
	public Browser dragAndDrop(By by) { return this; }
	public Browser acceptAlertBox() { this.driver.switchTo().alert().accept(); return this; }
	public Browser denieAlertBox() { this.driver.switchTo().alert().dismiss(); return this; }
	public Browser switchToPreviousTab(By by) { return this; }
	public Browser switchToNextTab(By by) { return this; }
	public Browser switchToFirstTab(By by) { return this; }
	public Browser switchToLastTab(By by) { return this; }
	public Browser extractCookies(By by) { this.cookies = this.driver.manage().getCookies(); return this; }
}
