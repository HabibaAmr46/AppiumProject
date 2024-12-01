package tests;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import Pages.CartPage;
import Pages.GooglePage;
import Pages.LoginPage;
import Pages.ProductsPage;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class ContextsTest extends BaseTest {
	
	@Test
	public void contextTest() throws InterruptedException
	{
		new LoginPage(driver).
			selectCountry("Argentina").
			fillName("Habiba").
			selectFemaleRadioButton().
			clickOnLetsShopButton();
	
		new ProductsPage(driver).
			addFirstTwoProductsToCart().
			clickOnCartButton();
	
		new CartPage(driver)
			.clickOnCheckBox()
			.clickOnPurchaseButton();
		
		
		Thread.sleep(20000);
		
		
		
		
		
		new GooglePage(driver)
		.switchToGoogleContext()
		.setTextToSearchText("Appium")
		.pressBackKey();
		
		
		driver.context("NATIVE_APP");
	}

}
