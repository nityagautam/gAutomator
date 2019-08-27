package nng;

import org.openqa.selenium.By;

import nng.automator.ui.Main;
import nng.utils.Browser;

public class Runner{

	public static void main(String[] args) {
		// GUI AUTOMATION
		Main w = new Main();
		w.startFrame();
		
		// AUTOMATION SCRIPT TEST
		//=================
		//String pgUrl = "https://wikipedia.com"; 
		//int maxWaitTime = 10;
		//String browser = "CHROME";
		//String driverLocation = "C:\\_DevOpsTools\\WebDriver\\chromedriver.exe";
		//String driverLocation = "/Users/nityagautam/WorkSpace/WebDrivers/chrome/chromedriver";
		// Create a new browser object
		//Browser b = new Browser(pgUrl, maxWaitTime, browser, driverLocation);
		// Initialise and Open a browser
		//b.initialize().open();
		// Type the value and click on search button
		//b.type(By.xpath("//*[@id='searchInput']"), "Earth").click(By.xpath("//*[text()='Search']/.."));
		// Close driver
		//b.close();
		// Quit
		//b.getDriver().quit();
		// Exit the program
		//System.exit(0);
	}

}
