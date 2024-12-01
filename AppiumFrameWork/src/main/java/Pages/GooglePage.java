package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import io.appium.java_client.android.AndroidDriver;

public class GooglePage extends BasePage{
	
	private By searchBar=By.name("q");
	public GooglePage(AndroidDriver driver) {
		super(driver);
	}

	public GooglePage navigateToGooglePage(String url)
	{
		driver.get(url);
		return this;
	}
	
	
	public GooglePage switchToGoogleContext() throws InterruptedException
	{
		Thread.sleep(1000);
		driver.context("WEBVIEW_com.androidsample.generalstore");
		Thread.sleep(1000);
		return this;
	}
	
	public GooglePage setTextToSearchText(String text) throws InterruptedException
	{
		
		driver.findElement(searchBar).sendKeys(text+Keys.ENTER);
		Thread.sleep(1000);
		return this;
	}
	
	

}
