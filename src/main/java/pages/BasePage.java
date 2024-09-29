package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	protected void waitForElementToDisappear(WebDriver driver,By by) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	    wait.until(ExpectedConditions.invisibilityOf(driver.findElement(by)));	
	}
	
	protected WebElement getElementUsingJSExecutor(WebDriver driver, String querySelector) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return (WebElement) js.executeScript(querySelector);
	}
	
	protected String getElementTextJSExecutor(WebDriver driver, String querySelector) {
		JavascriptExecutor js = (JavascriptExecutor)driver;
		return (String) js.executeScript(querySelector);
	}
}
