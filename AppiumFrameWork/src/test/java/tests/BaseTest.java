package tests;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

import com.google.common.collect.ImmutableMap;

import Utils.ListenersTest;
import Utils.PropertyFileReader;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

@Listeners(ListenersTest.class)
public class BaseTest {
	AndroidDriver driver;
	AppiumDriverLocalService service;
	UiAutomator2Options options;

	@BeforeClass
	public void intializeDriver() throws MalformedURLException, URISyntaxException {

		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\xx\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();

		service.start();
		options = new UiAutomator2Options().setDeviceName(PropertyFileReader.getProperty("deviceName")).setPlatformName(PropertyFileReader.getProperty("platformName"))
				.setPlatformVersion(PropertyFileReader.getProperty("platformVersion"))
				.setApp(System.getProperty("user.dir") + "/src/test/resources/General-Store.apk");

		driver = new AndroidDriver((new URI("http://127.0.0.1:4723")).toURL(), options);

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		 File folder = new File(System.getProperty("user.dir")+"/allure-results");
	     deleteFolder(folder);

	}

	public static void deleteFolder(File folder) {
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                   file.delete();
                }
            }
        }
       //folder.delete();
    }
	public void longPressAction(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));

	}

	public void scrollIntoText(String text) {
		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + text + "\"))"));
		
		 
	}

	public void scrollIntoEnd() {
		boolean canScrollMore;
		do {
			canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 1.0));
		} while (canScrollMore);
	}

	public void swipeAction(WebElement element, String direction) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of("elementId",
				((RemoteWebElement) element).getId(), "direction", direction, "percent", 0.25));
	}

	public void dragDrop(WebElement element, int x, int y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "endX", x, "endY", y));

	}

	@AfterClass
	public void quitDriver() throws IOException {
		driver.quit();
		service.stop();
		openAllureReportAutomatically();

	}
	
	public AndroidDriver getDriver()
	{
		return driver;
	}

	private void openAllureReportAutomatically() {
		try {
			String projectDir = System.getProperty("user.dir");
			String command = "cmd /c start cmd.exe /K \"allure.bat serve allure-results\"";

			// Change the working directory
			ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", command);
			processBuilder.directory(new File(projectDir));

			// Start the process
			Process process = processBuilder.start();
			
			// Wait for the process to complete
			process.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
