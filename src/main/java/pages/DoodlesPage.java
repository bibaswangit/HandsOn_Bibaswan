package pages;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DoodlesPage extends BasePage{
	private final WebDriver driver;
	
	public DoodlesPage(WebDriver driver) {
        this.driver = driver;
	}
	
	private By doodleSearchBox = By.className("search-doodle__box-input");
	private By dateDropDown = By.xpath("//span[contains(text(),'Date')]");
	private By selectYearDropDown = By.name("date_like_year");
	private By selectMonthDropDown = By.name("date_like_month");
	private By selectDayDropDown = By.name("date_like_day");
	private By removeYearFilter = By.xpath("//div[@aria-label='Remove year']");
	private By removeMonthFilter = By.xpath("//div[@aria-label='Remove month']");
	private By removeDayFilter = By.xpath("//div[@aria-label='Remove day']");
	private By doodleSearchButton = By.xpath("//button[@class='search-doodle__box-button_search active']");
	private By doodlePageLoader = By.xpath("//div[@class='loader']");
	private By doodleContentCards = By.xpath("//div[@class='doodle-card-content']");
    
    public DoodlesPage clickOnDoodleSearchBox() {
        driver.findElement(doodleSearchBox).click();
        return this;	
    }
    
    public DoodlesPage enterInDoodleSearchBox(String text) {
        driver.findElement(doodleSearchBox).sendKeys(text);
        return this;	
    }
    
    public DoodlesPage clickOnDoodleSearchButton() {
        driver.findElement(doodleSearchButton).click();
        return this;	
    }
    
    public DoodlesPage openDateDropDown() {
        driver.findElement(dateDropDown).click();
        return this;	
    }
    
    public DoodlesPage selectYearFromDropDown(String year) {
    	Select selectYear=new Select(driver.findElement(selectYearDropDown));
		selectYear.selectByValue(year);
        return this;	
    }
    
    public DoodlesPage selectMonthFromDropDown(String month) {
    	Select selectYear=new Select(driver.findElement(selectMonthDropDown));
		selectYear.selectByValue(month);
        return this;	
    }
    
    public DoodlesPage selectDayFromDropDown(String day) {
    	Select selectYear=new Select(driver.findElement(selectDayDropDown));
		selectYear.selectByValue(day);
        return this;	
    }
    
    public DoodlesPage clearYearFilter() {
        driver.findElement(removeYearFilter).click();
        return this;	
    }
    
    public DoodlesPage clearMonthFilter() {
        driver.findElement(removeMonthFilter).click();
        return this;	
    }
    
    public DoodlesPage clearDayFilter() {
        driver.findElement(removeDayFilter).click();
        return this;	
    }
    
    public DoodlesPage waitForDoodleLoaderToDisappear() {
    	waitForElementToDisappear(driver,doodlePageLoader);
        return this;	
    }
    
    public DoodlesPage selectTheCountryContentCard(String country) {
    	for(WebElement ele:driver.findElements(doodleContentCards)) {
			if(ele.findElement(By.xpath(".//p[2]")).getText().contains(country)) {
				ele.click();
				break;
			}
    	}
        return this;	
    }
    
    public Map<String, Date> getContentCardDetails() throws ParseException {
    	Map<String,Date> contentCardsMap = new TreeMap<>();
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		
		for(WebElement ele:driver.findElements(doodleContentCards)) {
			contentCardsMap.put(ele.findElement(By.xpath(".//p[2]")).getText().split(" Independence Day 2024")[0],
					dateFormat.parse(ele.findElement(By.xpath(".//p[1]")).getText()));
			
		}
        return contentCardsMap;	
    }
}
