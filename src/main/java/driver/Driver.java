package driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Driver {
	
	private Driver() {}
	
	private static WebDriver driver = null;
	
	private static void initDriver() {
		ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
		driver= new ChromeDriver(options);
	}
	
	public static WebDriver getDriver() {
		if(driver==null) {
			initDriver();
			System.out.println("in getDriver if"+driver);
			return driver;
		}
		System.out.println("in getDriver else"+driver);
		return driver;
	}
	
	public static void quitDriver() {
		driver.quit();
	}
}
