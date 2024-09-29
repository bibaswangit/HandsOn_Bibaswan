package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ChromeSettingsPage extends BasePage{
	private final WebDriver driver;
	
	public ChromeSettingsPage(WebDriver driver) {
        this.driver = driver;
	}
	
	private String chromeSettingsSearchBox = " return document.querySelector('settings-ui').shadowRoot"
			+ ".querySelector('cr-toolbar').shadowRoot.querySelector('div#centeredContent>cr-toolbar-search-field')."
			+ "shadowRoot.querySelector('input')";
	
	private String chromeSettingsDeleteBrowsingData = "return document.querySelector('settings-ui')."
			+ "shadowRoot.querySelector('div#container>settings-main').shadowRoot.querySelector('settings-basic-page')."
			+ "shadowRoot.querySelector('div#basicPage>settings-section>settings-privacy-page').shadowRoot."
			+ "querySelector('settings-animated-pages>div>cr-link-row')";
	
	private String chromeSettingsTimeRangeDropDown = "return document.querySelector('settings-ui').shadowRoot.querySelector('div#container>settings-main')."
			+ "shadowRoot.querySelector('settings-basic-page').shadowRoot.querySelector('div#basicPage>settings-section>settings-privacy-page')."
			+ "shadowRoot.querySelector('settings-clear-browsing-data-dialog').shadowRoot."
			+ "querySelector(\"cr-dialog>div[slot='body']>cr-page-selector>div>div>settings-dropdown-menu\").shadowRoot.querySelector('select')";
	
	private String  chromeSettingsSelectedTimeRange = "return document.querySelector('settings-ui').shadowRoot."
			+ "querySelector('div#container>settings-main').shadowRoot.querySelector('settings-basic-page')."
			+ "shadowRoot.querySelector('div#basicPage>settings-section>settings-privacy-page').shadowRoot."
			+ "querySelector('settings-clear-browsing-data-dialog').shadowRoot."
			+ "querySelector(\"cr-dialog>div[slot='body']>cr-page-selector>div>div>settings-dropdown-menu\").shadowRoot."
			+ "querySelector('select').selectedOptions[0].label";
	
	private String chromeSettingsBrowserHistoryCheckBox = "return document.querySelector('settings-ui').shadowRoot."
			+ "querySelector('div#container>settings-main').shadowRoot.querySelector('settings-basic-page').shadowRoot."
			+ "querySelector('div#basicPage>settings-section>settings-privacy-page').shadowRoot."
			+ "querySelector('settings-clear-browsing-data-dialog').shadowRoot."
			+ "querySelector(\"cr-dialog>div[slot='body']>cr-page-selector>div>settings-checkbox[label='Browsing history']\")";
	
	private String chromeSettingsCookiesCheckBox = "return document.querySelector('settings-ui').shadowRoot."
			+ "querySelector('div#container>settings-main').shadowRoot.querySelector('settings-basic-page').shadowRoot."
			+ "querySelector('div#basicPage>settings-section>settings-privacy-page').shadowRoot."
			+ "querySelector('settings-clear-browsing-data-dialog').shadowRoot."
			+ "querySelector(\"cr-dialog>div[slot='body']>cr-page-selector>div>settings-checkbox[label='Cookies and other site data']\")";
	
	private String chromeSettingsCachedImagesCheckBox = "return document.querySelector('settings-ui').shadowRoot."
			+ "querySelector('div#container>settings-main').shadowRoot.querySelector('settings-basic-page').shadowRoot."
			+ "querySelector('div#basicPage>settings-section>settings-privacy-page').shadowRoot."
			+ "querySelector('settings-clear-browsing-data-dialog').shadowRoot."
			+ "querySelector(\"cr-dialog>div[slot='body']>cr-page-selector>div>settings-checkbox[label='Cached images and files']\")";
    
    public ChromeSettingsPage clickOnChromeSettingsSearchBox() {
    	getElementUsingJSExecutor(driver,chromeSettingsSearchBox).click();
        return this;	
    }
    
    public ChromeSettingsPage enterInChromeSettingsSearchBox(String text) {
    	getElementUsingJSExecutor(driver,chromeSettingsSearchBox).sendKeys(text);
        return this;	
    }
    
    public ChromeSettingsPage clickOnChromeSettingsDeleteBrowsingData() {
    	getElementUsingJSExecutor(driver,chromeSettingsDeleteBrowsingData).click();
        return this;	
    }
    
    public String getSelectedChromeSettingsTimeRange() {
    	return getElementTextJSExecutor(driver,chromeSettingsSelectedTimeRange);	
    }
    
    public ChromeSettingsPage selectFromChromeSettingsTimeRangeDropDown(String option) {
    	Select select = new Select(getElementUsingJSExecutor(driver,chromeSettingsTimeRangeDropDown));
    	select.selectByValue(option);
        return this;	
    }
    
    public ChromeSettingsPage clickOnChromeSettingsBrowserHistoryCheckBox() {
    	getElementUsingJSExecutor(driver,chromeSettingsBrowserHistoryCheckBox).click();
        return this;	
    }
    
    public ChromeSettingsPage clickOnChromeSettingsCookiesCheckBox() {
    	getElementUsingJSExecutor(driver,chromeSettingsCookiesCheckBox).click();
        return this;	
    }
    
    public ChromeSettingsPage clickOnChromeSettingsCachedImagesCheckBox() {
    	getElementUsingJSExecutor(driver,chromeSettingsCachedImagesCheckBox).click();
        return this;	
    }
}
