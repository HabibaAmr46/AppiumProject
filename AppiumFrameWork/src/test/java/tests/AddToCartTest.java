package tests;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductsPage;
import io.appium.java_client.AppiumBy;
import io.qameta.allure.Allure;
import io.qameta.allure.Description;

public class AddToCartTest extends BaseTest {

	@Description("Test the filling of the shopping Cart")
	@Test
	public void validShoppingForm() throws MalformedURLException, URISyntaxException, InterruptedException {

		String productName="Jordan 6 Rings";
		
		new LoginPage(driver).
			selectCountry("Argentina").
			fillName("Habiba").
			selectFemaleRadioButton().
			clickOnLetsShopButton();
		
		new ProductsPage(driver)
			.addProductToCart(productName)
			.clickOnCartButton();
		
		new CartPage(driver)
		.checkProductExistInCart(productName);
		

	}
	
	
	@Test
	public void invalidShoppingForm() throws MalformedURLException, URISyntaxException, InterruptedException {

		String errorMessage=new LoginPage(driver).
							selectCountry("Argentina").
							selectFemaleRadioButton().
							clickOnLetsShopButton().
							getTextFromToastMessage();
		
		
		Allure.step("Check error message is correct");
		Assert.assertEquals(errorMessage,"Please enter your name");
	}


}
