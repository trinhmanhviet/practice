package practice;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SetUpDriver {
	
	public static String chromepath = "E://_Autotest//chromedriver.exe";
	public WebDriver driver;
	public WebDriverWait wait;
	
	public WebDriver driverConfig()
	{
		System.setProperty("webdriver.chrome.driver", "E://_Autotest/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}
	
	public WebDriverWait explicitwait()
	{
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		return wait;
	}
	
	public void quitDriver() {
		driver.quit();
	}
	
}
