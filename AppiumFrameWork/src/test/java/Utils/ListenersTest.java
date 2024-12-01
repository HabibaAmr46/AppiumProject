package Utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import io.appium.java_client.android.AndroidDriver;
import io.qameta.allure.Attachment;
import tests.BaseTest;

public class ListenersTest implements ITestListener {

	private static String getTestMethodName(ITestResult iTestResult) {
		return iTestResult.getMethod().getConstructorOrMethod().getName();
	}

	@Override
	public void onTestSuccess(ITestResult iTestResult) {
		AndroidDriver driver = ((BaseTest) iTestResult.getInstance()).getDriver();
		

		// Allure ScreenShotRobot and SaveTestLog
		if (driver != null) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(driver);
		}
    }
	
	
	@Override
	public void onTestFailure(ITestResult iTestResult) {

		// Get driver from BaseTest and assign to local webdriver variable.
		AndroidDriver driver = ((BaseTest) iTestResult.getInstance()).getDriver();

		// Allure ScreenShotRobot and SaveTestLog
		if (driver != null) {
			System.out.println("Screenshot captured for test case:" + getTestMethodName(iTestResult));
			saveScreenshotPNG(driver);
		}
	}

	// Text attachments for Allure
	@Attachment(value = "Page screenshot", type = "image/png")
	public byte[] saveScreenshotPNG(WebDriver driver) {
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

	// Text attachments for Allure
	@Attachment(value = "{0}", type = "text/plain")
	public static String saveTextLog(String message) {
		return message;
	}

}
