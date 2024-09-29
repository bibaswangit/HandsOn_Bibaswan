package tests;

import org.testng.annotations.Test;

import pages.ChromeSettingsPage;

public class ChromeSettingsTest extends BaseTest {
	
	@Test
	public void test() throws Exception {
		
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
