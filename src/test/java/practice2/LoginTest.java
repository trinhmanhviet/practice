package practice2;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginTest {
	
	public static void main(String[] args) {
		
		System.setProperty("webdriver.chrome.driver", "E://_Autotest/chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.rahulshettyacademy.com/locatorspractice/");
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		errMsgTest(driver);
		
		findPass(driver, wait);
		
		

	}
	
	public static void errMsgTest(WebDriver driver) {
		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("manhviet");
    	driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("viet123");
    	driver.findElement(By.xpath("//input[@id='chkboxOne']")).click();
    	if(driver.findElement(By.xpath("//input[@id='chkboxOne']")).isSelected()==true) {
    		System.out.println("ID remembered");
    	}else {
    		System.out.println("ID not remembered");
    	}
    	driver.findElement(By.xpath("//button[@class='submit signInBtn']")).click();
    	String errMess = driver.findElement(By.xpath("//p[@class='error']")).getText();
    	System.out.println(errMess);
	}
	
	public static String findPass(WebDriver driver, WebDriverWait wait) {
		driver.findElement(By.linkText("Forgot your password?")).click();
		driver.findElement(By.xpath("//input[@placeholder='Name']")).sendKeys("rahul");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='reset-pwd-btn']")));
		driver.findElement(By.xpath("//button[@class='reset-pwd-btn']")).click();
		String passMsg = driver.findElement(By.xpath("//p[@class='infoMsg']")).getText();
		String[] a = passMsg.split("'");
		System.out.println(a[1]);
		return a[1];
	}

}
