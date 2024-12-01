package Pages;
import org.openqa.selenium.By;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Step;

public class LoginPage extends BasePage {

	private By country = By.id("android:id/text1");
	private By name = By.id("com.androidsample.generalstore:id/nameField");
	private By femaleRadioButton=By.id("com.androidsample.generalstore:id/radioFemale");
	private By letsShopButton=By.id("com.androidsample.generalstore:id/btnLetsShop");
	private By toastErrorMessage=By.xpath("//android.widget.Toast");

	public LoginPage(AndroidDriver driver) {
		super(driver);
	}

	
	@Step("Selecting the country")
	public LoginPage selectCountry(String country)

	{
		
		driver.findElement(this.country).click();
		scrollIntoText(country);
		driver.findElement(
				By.xpath("//android.widget.TextView[@text=\"" + country + "\"]"))
				.click();
		return this;
	}

	@Step("Enter the Name")
	public LoginPage fillName(String name) throws InterruptedException

	{
		driver.findElement(this.name).sendKeys(name);
		return this;
	}
	
	@Step("Select female radio button")
	public LoginPage selectFemaleRadioButton()

	{
		driver.findElement(femaleRadioButton).click();
		return this;
	}
	
	@Step("Click on Shop button")
	public LoginPage clickOnLetsShopButton()

	{
		driver.findElement(letsShopButton).click();
		return this;
	}
	
	@Step("Get Actual Toast Message")
	public String getTextFromToastMessage()

	{
		return driver.findElement(toastErrorMessage).getAttribute("name");
	}

}
