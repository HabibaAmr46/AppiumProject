package tests;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BrowserBaseTest {
	AndroidDriver driver;
	AppiumDriverLocalService service;
	UiAutomator2Options options;

	@BeforeClass
	public void intializeDriver() throws MalformedURLException, URISyntaxException {

		service = new AppiumServiceBuilder()
				.withAppiumJS(new File(
						"C:\\Users\\hmohammed25\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723)
				.withArgument(() -> "--allow-insecure","chromedriver_autodownload").build();
		
		
		service.start();
	    options = new UiAutomator2Options().setDeviceName("emulator-5554")
				.setPlatformName("Android").setPlatformVersion("15")
				.withBrowserName("Chrome");
			
				
				
		driver = new AndroidDriver((new URI("http://127.0.0.1:4723")).toURL(), options);
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}
	
	
	
	@AfterClass
	public void quitDriver() {
		driver.quit();

		service.stop();
	}

}
