package Pages;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class CartPage extends BasePage{

	
	private By product=By.id("com.androidsample.generalstore:id/productName");
	private By productPrice=By.id("com.androidsample.generalstore:id/productPrice");
	private By totalAmountPrice=By.id("com.androidsample.generalstore:id/totalAmountLbl");
	private By termsOfConditions=By.id("com.androidsample.generalstore:id/termsButton")	;
	private By closeButton=By.id("android:id/button1");
	private By checkBox=AppiumBy.className("android.widget.CheckBox");
	private By purchaseButton=By.id("com.androidsample.generalstore:id/btnProceed");
	
	public CartPage(AndroidDriver driver) {
		super(driver);
	}
	
	
	public CartPage waitForCartPageLoad() {
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.attributeContains(pageTitle, "text", "Cart")));
		
		return this;
	}
	
	@Step("Check product exist in the cart")
	public CartPage checkProductExistInCart(String productName)
	{
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfElementLocated(product)));
		Assert.assertEquals(driver.findElement(product).getText(), productName);
		return this;
		
	}
	
	@Step("Check the Expected Sum of the products is the same as Actual Sum")
	public CartPage checkSumOfProductsIsCorrect()
	{		
		wait.until(ExpectedConditions.refreshed(
		        ExpectedConditions.visibilityOfElementLocated(productPrice)));
		List<WebElement> productPrices= driver.findElements(productPrice);
		double expectedTotalAmount=productPrices.stream().mapToDouble(product ->Double.parseDouble(product.getText().substring(1))).sum();
		System.out.println("************"+expectedTotalAmount);
		Double actualTotalAmount=Double.parseDouble(driver.findElement(totalAmountPrice).getText().substring(1));
		System.out.println("************"+actualTotalAmount);
		Assert.assertTrue(actualTotalAmount.equals(expectedTotalAmount));
		
		return this;
	}
	
	@Step("Long Press on terms of conditions")
	public CartPage longPressOnTermsOfConditions()
	{
		longPressAction(driver.findElement(termsOfConditions));
		return this;
	}
	
	@Step("Close Terms of Conditions")
	public CartPage closeTermsOfConditions()
	{
		driver.findElement(closeButton).click();
		return this;
	}
	
	@Step("Click on checkbox")
	public CartPage clickOnCheckBox()
	{
		driver.findElement(checkBox).click();
		return this;
	}
	
	@Step("Click on Purchase")
	public CartPage clickOnPurchaseButton()
	{
		driver.findElement(purchaseButton).click();
		return this;
	}
	
	

}
