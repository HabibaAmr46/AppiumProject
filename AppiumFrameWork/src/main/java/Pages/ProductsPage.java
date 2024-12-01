package Pages;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class ProductsPage extends BasePage {
	
	private By productName=By.id("com.androidsample.generalstore:id/productName");
	private By addToCart=By.id("com.androidsample.generalstore:id/productAddCart");
	private By cartButton=By.id("com.androidsample.generalstore:id/appbar_btn_cart");
	public ProductsPage(AndroidDriver driver) {
		super(driver);
		
	}

	
	@Step("Add the product to the cart")
	public ProductsPage addProductToCart(String productName)
	{
		scrollIntoText(productName);
		int productCount=driver.findElements(this.productName).size();
		for(int i=0;i<productCount;i++)
		{
			if(driver.findElements(this.productName).get(i).getText().contains(productName))
			{
				driver.findElements(addToCart).get(i).click();
			}
		}
		
		return this;
		
	}
	
	@Step("Click on add to cart button to go to the cart page")
	public ProductsPage clickOnCartButton()
	{
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(cartButton)));
		driver.findElement(cartButton).click();
		return this;
	}
	
	@Step("Add the first two products to the cart")
	public ProductsPage addFirstTwoProductsToCart() throws InterruptedException
	{
		driver.findElements(addToCart).get(0).click();
		Thread.sleep(400);
		driver.findElements(addToCart).get(1).click();
		return this;
	}
	
	
	
	
	

}
