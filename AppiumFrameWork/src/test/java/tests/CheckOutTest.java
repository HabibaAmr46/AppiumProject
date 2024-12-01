package tests;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import Pages.CartPage;
import Pages.LoginPage;
import Pages.ProductsPage;

public class CheckOutTest extends BaseTest {

	@Test
	public void E2ETest() throws MalformedURLException, URISyntaxException, InterruptedException {

		new LoginPage(driver).
			selectCountry("Egypt").
			fillName("Habiba").
			selectFemaleRadioButton().
			clickOnLetsShopButton();

		new ProductsPage(driver).
			addFirstTwoProductsToCart().
			clickOnCartButton();

		new CartPage(driver)
			.checkSumOfProductsIsCorrect()
			.longPressOnTermsOfConditions()
			.closeTermsOfConditions()
			.clickOnCheckBox()
			.clickOnPurchaseButton();
	}

}
