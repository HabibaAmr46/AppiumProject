package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

import io.appium.java_client.android.AndroidDriver;

public class WebAppPage extends BasePage {
	
	
	private By togglerIcon=By.cssSelector(".navbar-toggler-icon");
	private By productsLink=By.cssSelector("a[routerlink='/products']");
	
	public WebAppPage(AndroidDriver driver) {
		super(driver);
	}
	
	public WebAppPage navigateToWebApp(String url)
	{
		driver.get(url);
		return this;
	}
	
	public WebAppPage clickOnTogglerIcon()
	{
		driver.findElement(togglerIcon).click();
		return this;
	}
	public WebAppPage clickOnProductsLink()
	{
		driver.findElement(productsLink).click();
		return this;
	}
	
	public String getTitleOfProduct(int productNumber)
	{
		
	    By product=By.xpath("//a[contains(@href,'products/"+productNumber+"')]");
	    ((JavascriptExecutor)driver).executeScript("window.scrollBy(0,1000)", "");
		return driver.findElement(product).getText();
	}

}
