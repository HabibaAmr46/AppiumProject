package Pages;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class BasePage {
	
	AndroidDriver driver;
	protected By pageTitle=By.id("com.androidsample.generalstore:id/toolbar_title");
	WebDriverWait wait;
	
	public BasePage(AndroidDriver driver) {
		this.driver = driver;
		wait=new WebDriverWait(driver, Duration.ofSeconds(8));
	}

	public void scrollIntoText(String text)
	{
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" +text +"\"))"));
	}

	public void scrollIntoEnd()
	{
		
		boolean canScrollMore;
		do{
			canScrollMore= (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 1.0
			));
		}while(canScrollMore);
	}
	public void swipeAction(WebElement element, String direction)
	{
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
				"elementId", ((RemoteWebElement) element).getId(),
			    "direction", direction,
			    "percent", 0.25
			));
	}
	
	public void dragDrop(WebElement element, int x, int y)
	{
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture", ImmutableMap.of(
			    "elementId", ((RemoteWebElement) element).getId(),
			    "endX", x,
			    "endY", y
			));
		
	}
	public void longPressAction(WebElement element)
	{
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", ImmutableMap.of(
		    "elementId", ((RemoteWebElement) element).getId(), "duration", 2000
			));
	}
	
	public void switchContext(String context)
	{
		/*for(String contextElement: driver.getContextHandles())
		{
			System.out.println(contextElement);
		}
		*/
		Set<String> contexts=driver.getContextHandles();
		
		if(contexts.contains(context))
		{
			driver.context(context);
		}
		else
			System.out.println("This context doesn't exist");
		
	}
	public void pressBackKey()
	{
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	
}
