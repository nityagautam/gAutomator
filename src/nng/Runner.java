package nng;

import org.openqa.selenium.By;

import nng.automator.ui.Main;
import nng.utils.Browser;

public class Runner{

	public static void main(String[] args) {
		// GUI AUTOMATION
		//Main w = new Main();
		//w.startFrame();
		
		// AUTOMATION
		String pgUrl = "https://wikipedia.com"; 
		int maxWaitTime = 10;
		String browser = "CHROME";
		String driverLocation = "C:\\_DevOpsTools\\WebDriver\\chromedriver.exe";
		Browser b = new Browser(pgUrl, maxWaitTime, browser, driverLocation);
		
		// Start
		b.initialize().
			open().
			type(By.xpath("//*[@id='searchInput']"), "Earth").
			click(By.xpath("//*[text()='Search']/.."));
		
		// Close driver
		b.close();
	}

}
