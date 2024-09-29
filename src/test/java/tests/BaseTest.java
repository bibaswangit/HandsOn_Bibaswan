package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import driver.Driver;

public class BaseTest {

	protected static WebDriver driver;
	protected BaseTest() {}
	
	@BeforeTest
	protected void setUp() {
		driver = Driver.getDriver();
		System.out.println("in setUp"+driver);
	}

	@AfterTest
	protected void tearDown() {
		Driver.quitDriver();
	}
	
}
