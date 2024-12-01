package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.GooglePage;
import Pages.WebAppPage;
import Utils.PropertyFileReader;

public class BrowserTest extends BrowserBaseTest {
	
	
	
	@Test
	public void browserTest() throws InterruptedException
	{
		
		new GooglePage(driver)
		.navigateToGooglePage(PropertyFileReader.getProperty("googleURL"))
		.setTextToSearchText("Appium");
	}
	
	
	@Test
	public void browserTest2()
	{
		
		String title=new WebAppPage(driver)
						.navigateToWebApp(PropertyFileReader.getProperty("rahulShettyURL"))
						.clickOnTogglerIcon()
						.clickOnProductsLink()
						.getTitleOfProduct(3);
		
		Assert.assertEquals(title, "Devops");
	}

}
