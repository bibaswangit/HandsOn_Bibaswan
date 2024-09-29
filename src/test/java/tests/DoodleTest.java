package tests;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v129.network.Network;
import org.openqa.selenium.devtools.v129.network.model.Request;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.ChromeSettingsPage;
import pages.DoodlesPage;

public class DoodleTest extends BaseTest{
		
	@Test
	public void test() throws Exception {
		//1
		System.out.println("Test class"+driver);
		driver.get("https://doodles.google/search/");
		
		//2
		DoodlesPage doodlesPage = new DoodlesPage(driver);
		doodlesPage.clickOnDoodleSearchBox();

		//3
		doodlesPage.openDateDropDown();
		doodlesPage.selectYearFromDropDown("2024");
		doodlesPage.selectMonthFromDropDown("9");
		doodlesPage.selectDayFromDropDown("28");
		
		//4
		doodlesPage.clearYearFilter();
		doodlesPage.clearMonthFilter();
		doodlesPage.clearDayFilter();
		doodlesPage.selectYearFromDropDown("2024");
		doodlesPage.selectMonthFromDropDown("8");
		doodlesPage.enterInDoodleSearchBox("Independence Day");
		Thread.sleep(5000);
		doodlesPage.clickOnDoodleSearchButton();
		
		//5
	    doodlesPage.waitForDoodleLoaderToDisappear();
	    
//		List<WebElement> contentCards=driver.findElements(By.xpath("//div[@class='doodle-card-content']"));
		
		Map<String,Date> contentCardsMap = doodlesPage.getContentCardDetails();
		ArrayList<Date> valueList = new ArrayList<>();
		LinkedHashMap<String, Date> sortedMap = new LinkedHashMap<>();
		
		valueList.addAll(contentCardsMap.values());
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		
//		for(WebElement ele:contentCards) {
//			contentCardsMap.put(ele.findElement(By.xpath(".//p[2]")).getText().split(" Independence Day 2024")[0],
//					dateFormat.parse(ele.findElement(By.xpath(".//p[1]")).getText()));
//			
//		}

		System.out.println(contentCardsMap.toString());
		Assert.assertTrue(contentCardsMap.keySet().toString().contains("India"));
		
		Collections.sort(valueList);
		for(Date value:valueList) {
			for(Entry<String,Date> entry:contentCardsMap.entrySet()) {
				if(entry.getValue().equals(value))
					sortedMap.put(entry.getKey(), value);
			}
		}
		
		
		//6
		int rowNo = 0;
		String filename = System.getProperty("user.dir")+"\\test-output\\I-Day.xls";
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("August,24");
		XSSFRow row = sheet.createRow(rowNo++);
		row.createCell(0).setCellValue("Country");
		row.createCell(1).setCellValue("Independence Day");
		for(Entry<String, Date> entry: sortedMap.entrySet() ){
			row = sheet.createRow(rowNo++);
			row.createCell(0).setCellValue(entry.getKey());
			row.createCell(1).setCellValue(String.format(entry.getValue().toString(), "%1$tm %1$te,%1$tY"));
		}
		
		FileOutputStream fos = new FileOutputStream(filename);
		workbook.write(fos);
		workbook.close();
		fos.close();
		
		//7,8	
		DevTools devTools = ((ChromeDriver) driver).getDevTools();
		devTools.createSession();
		devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
		
		devTools.addListener(Network.requestWillBeSent(), request-> {
			Request req = request.getRequest();
			System.out.println(req.getUrl());
			System.out.println(req.getMethod());
			
		});
		
		doodlesPage.selectTheCountryContentCard("India");
		
		//9
//		DevTools devTools = ((ChromeDriver) driver).getDevTools();
//		devTools.createSession();
		//10
		//11
		driver.get("chrome://settings/");
		Thread.sleep(2000);
		
		//12
		ChromeSettingsPage chromeSettingsPage = new ChromeSettingsPage(driver);
		chromeSettingsPage.clickOnChromeSettingsSearchBox();
		chromeSettingsPage.enterInChromeSettingsSearchBox("cache");
		Thread.sleep(2000);
		
		//13
		chromeSettingsPage.clickOnChromeSettingsDeleteBrowsingData();
		Thread.sleep(2000);
		System.out.println(chromeSettingsPage.getSelectedChromeSettingsTimeRange());
		if(!chromeSettingsPage.getSelectedChromeSettingsTimeRange().equals("Last hour")) {
			chromeSettingsPage.selectFromChromeSettingsTimeRangeDropDown("Last hour");
		}
		Thread.sleep(2000);
		
		//14		
		chromeSettingsPage.clickOnChromeSettingsBrowserHistoryCheckBox();
		chromeSettingsPage.clickOnChromeSettingsCookiesCheckBox();
		chromeSettingsPage.clickOnChromeSettingsCachedImagesCheckBox();
		
	}

}
